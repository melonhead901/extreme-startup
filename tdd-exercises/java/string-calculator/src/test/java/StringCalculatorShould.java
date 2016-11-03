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

    @Test
    public void return_sum_for_unknown_num_of_nums() {
        String s = "0";
        int sum = 0;
        for(int i = 1; i < 101; i++) {
            s = s.concat("," + Integer.toString(i));
            sum += i;
            assertThat(calculator.calculate(s), is(sum));
        }
    }


}
