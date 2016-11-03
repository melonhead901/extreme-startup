/**
 * Created by rchew on 11/3/16.
 */
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class StringCalculatorShould {

    private StringCalculator calculator = new StringCalculator();

    @Test
    public void return_zero_for_empty_string() {
        assertThat(calculator.calculate(""), is(0));
    }

    @Test
    public void return_one_for_string_one() {
        assertThat(calculator.calculate("1"), is(1));
    }

    @Test
    public void return_two_for_string_two() {
        assertThat(calculator.calculate("2"), is(2));
    }

    @Test
    public void return_sum_for_two_nums() {
        assertThat(calculator.calculate("1,2"), is(3));
    }


}
