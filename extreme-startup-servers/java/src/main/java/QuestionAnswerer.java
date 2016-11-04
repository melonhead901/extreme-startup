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
            } if (question.contains("which of the following numbers is")) {
                String numbers = splitQuery[2].trim();
                String[] numberArgs = splitQuery[2].split(",");

                List<Integer> numberArgsList = new ArrayList<>();
                for (String num : numberArgs) {
                    numberArgsList.add(Integer.valueOf(num));
                }

                if (question.contains("largest")) {
                    int max = 0;
                    for (Integer num : numberArgsList) {
                        max = (num > max) ? num : max;
                    }
                    out = Integer.toString(max);
                }
            }
        } else {
            out = String.format("Unknown query: '%s'", query);
        }

        System.out.println(out);
        return out;
    }
}
