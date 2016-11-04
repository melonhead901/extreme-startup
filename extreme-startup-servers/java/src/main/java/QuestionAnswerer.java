import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerer {
    public String answer(String query) {
        String out = "";
        if (query == null) {
            out = "received null query";
        } else if (query.contains(":")) {
            String[] splitQuery = query.split(":");
            String queryHash = splitQuery[0];
            String question = splitQuery[1].trim();
            if (question.equals("what is your name")) {
                out = "The Great North";
            } else if (question.contains("which of the following numbers is")) {
                String[] numberArgs = splitQuery[2].trim().split(",");

                List<Integer> numberArgsList = new ArrayList<>();
                for (String num : numberArgs) {
                    numberArgsList.add(Integer.valueOf(num.trim()));
                }

                if (question.contains("largest")) {
                    int max = 0;
                    for (Integer num : numberArgsList) {
                        max = (num > max) ? num : max;
                    }
                    out = Integer.toString(max);
                }
            } else if (question.contains("plus")) {
                out = doAddition(question);
            }

            ///// KELLEN


            ///// END KELLEN
        } else {
            out = String.format("Unknown query: '%s'", query);
        }

        System.out.println(out);
        return out;
    }

    /// KELLEN

    /// END KELLEN

    public String doAddition(String line) {
        String[] split = line.split(" ");
        int sum = 0;
        for (String str : split) {
            try {
                sum += Integer.valueOf(str);
            } catch (NumberFormatException expected) {

            }
        }
        return sum + "";
    }
}
