/*
 * Copyright 2016 Palantir Technologies, Inc. All rights reserved.
 *
 * THIS SOFTWARE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION OWNED BY PALANTIR TECHNOLOGIES INC.
 * UNAUTHORIZED DISCLOSURE TO ANY THIRD PARTY IS STRICTLY PROHIBITED
 *
 * For good and valuable consideration, the receipt and adequacy of which is acknowledged by Palantir and recipient
 * of this file ("Recipient"), the parties agree as follows:
 *
 * This file is being provided subject to the non-disclosure terms by and between Palantir and the Recipient.
 *
 * Palantir solely shall own and hereby retains all rights, title and interest in and to this software (including,
 * without limitation, all patent, copyright, trademark, trade secret and other intellectual property rights) and
 * all copies, modifications and derivative works thereof.  Recipient shall and hereby does irrevocably transfer and
 * assign to Palantir all right, title and interest it may have in the foregoing to Palantir and Palantir hereby
 * accepts such transfer. In using this software, Recipient acknowledges that no ownership rights are being conveyed
 * to Recipient.  This software shall only be used in conjunction with properly licensed Palantir products or
 * services.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import can.touch.ContactDetail;
import can.touch.CustomerRepository;
import can.touch.TargettedCustomerReport;
import com.google.common.collect.ImmutableList;
import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.logging.LogDirectory;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import static com.google.common.base.Optional.absent;
import static com.palantir.docker.compose.configuration.ShutdownStrategy.AGGRESSIVE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TargettedCustomerReportShould {
    private static final ContactDetail ANNAS_NUMBER = new ContactDetail("12356", "Anna");
    private static final ContactDetail BOBS_NUMBER = new ContactDetail("32165", "Bob");
    private static final ContactDetail JEFFS_NUMBER = new ContactDetail("11111", "Jeff");


    CustomerRepository repository = CustomerRepository.createDefault();
    TargettedCustomerReport report = new TargettedCustomerReport(repository);

    @BeforeClass


    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
            .file("docker-compose.yml")
            .saveLogsTo(LogDirectory.circleAwareLogDirectory(RealCustomerRepositoryShould.class))
            .shutdownStrategy(AGGRESSIVE)
            .build();

    @Test public void
    dont_find_jeffs_number() {
        //when(repository.getAllContactDetails()).thenReturn(ImmutableList.of(JEFFS_NUMBER));

        assertThat(report.getAllImportantNumbers(), not(hasItem(JEFFS_NUMBER.getPhoneNumber())));
    }

    @Test public void
    find_bobs_number() {
        // when(repository.getAllContactDetails()).thenReturn(ImmutableList.of(BOBS_NUMBER));

        assertThat(report.getAllImportantNumbers(), contains(BOBS_NUMBER.getPhoneNumber()));
    }

    @Test public void
    find_annas_number() {
        // when(repository.getAllContactDetails()).thenReturn(ImmutableList.of(ANNAS_NUMBER));

        assertThat(report.getAllImportantNumbers(), contains(ANNAS_NUMBER.getPhoneNumber()));
    }
}
