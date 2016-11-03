/**
 * Created by rchew on 11/3/16.
 */
public class StringCalculator {
    public StringCalculator() {

    }

    public int calculate(String s) {
        if (!s.isEmpty()) {
            if (s.contains(",")) {
                String[] addends = s.split(",");
                int sum = 0;
                for (int i = 0; i < addends.length; i++) {
                    sum += this.calculate(addends[i]);
                }
                return sum;
            }
            return Integer.valueOf(s);
        }
        return 0;
    }
}
