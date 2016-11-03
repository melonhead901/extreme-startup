/**
 * Created by rchew on 11/3/16.
 */
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class StringCalculatorShould {

    @Test
    public void return_zero_for_empty_string() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate(""), is(0));
    }

    @Test
    public void return_one_for_string_one() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1"), is(1));
    }

}
