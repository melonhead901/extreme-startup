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
            } else if (question.contains("multiplied by")) {
                out = doMultiplication(question);
            }


                ///// KELLEN

            else if (question.contains("cube") && question.contains("square")) {
                return squareAndCube(question.concat(splitQuery[2]));
            }


            ///// END KELLEN
        } else {
            out = String.format("Unknown query: '%s'", query);
        }

        System.out.println(out);
        return out;
    }

    /// KELLEN


    public String squareAndCube(String line) {
        String[] split = line.split(" ");
        for (String str : split) {
            try {
                int num = Integer.valueOf(str);
                int sqet = (int)Math.sqrt(num);
                int cuberoot = (int)Math.cbrt(num);
                if (((sqet * sqet) == num) && ((cuberoot * cuberoot * cuberoot) == num)) {
                    return num + "";
                }
            } catch (NumberFormatException expected) {

            }
        }
        return "none";

    }

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

    public String doMultiplication(String line) {
        String[] split = line.split(" ");
        int prod = 1;
        for (String str : split) {
            try {
                prod *= Integer.valueOf(str);
            } catch (NumberFormatException expected) {

            }
        }
        return prod + "";
    }
}
