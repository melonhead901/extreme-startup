import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerer {
    public String answer(String query) {
        String out = "";
        if (query == null) {
            out = "received null query";
        } else if (query.contains(":")) {

            List<Integer> intArgs = new ArrayList<>();
            for (String word : query.split(" |,|:")) {
                try {
                    intArgs.add(Integer.valueOf(word));

                } catch (Exception e) {

                }
            }

            String[] splitQuery = query.split(":");
            String queryHash = splitQuery[0];
            String question = splitQuery[1].trim();
            if (query.contains("Fibonacci")) {
                out = fibo(query);
            }


            ///// KELLEN

            else if (question.contains("cube") && question.contains("square")) {
                out = squareAndCube(question.concat(splitQuery[2]));
            } else if (question.contains("prime")) {
                out = isPrime(question.concat(splitQuery[2]));
            } else if (question.contains("power")) {
                out = power(query);
            }


            ///// END KELLEN
            else if (question.contains("who")) {
                if (question.contains("James Bond")) {
                    out = doJamesBond(query);
                }
            } else if (question.contains("what")) {
                if (question.contains("Spain")) {
                    if (question.contains("Euro")) {
                        out = "peseta";
                    }
                } else if (question.equals("what is your name")) {
                    out = "The Great North";
                } else  if (question.contains("minus")) {
                    out = doSubtraction(query);
                } else if (question.contains("plus")) {
                    out = doAddition(query);
                } else if (query.contains("multiplied by")) {
                    out = doMultiplication(query);
                }
            } else if (question.contains("which")) {
                if (question.contains("Eiffel")) {
                    out = "Paris";
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
                }
            }
        } else {
            out = String.format("Unknown query: '%s'", query);
        }

        System.out.println(out);
        return out;
    }

    private String power(String line) {
        String[] split = line.split(" |,");
        List<Integer> lst = new ArrayList<>();
        for (String str : split) {
            try {
                lst.add(Integer.valueOf(str));
            } catch (Exception expected) {

            }
        }
        if(lst.size() !=2){

            return"none";
        } else {
            return Math.pow(lst.get(0), lst.get(1)) + "";
        }

}

    private String doSubtraction(String query) {
        return "none";
    }


    public String isPrime(String line) {
        System.out.println(line);
        String[] split = line.split(" |,");
        for (String str : split) {
            try {
                if (new BigInteger(str).isProbablePrime(10)) {
                    return str;
                }
            } catch (Exception expected) {

            }
        }
        return "none";
    }

    /// KELLEN


    public String squareAndCube(String line) {
        String[] split = line.split(" |,");
        for (String str : split) {
            try {
                int num = Integer.valueOf(str);
                int sqet = (int) Math.sqrt(num);
                int cuberoot = (int) Math.cbrt(num);
                if (((sqet * sqet) == num) && ((cuberoot * cuberoot * cuberoot) == num)) {
                    return num + "";
                }
            } catch (NumberFormatException expected) {

            }
        }
        return "none";

    }

    public String fibo(String line) {
        String[] str = line.split("[a-zA-Z, :]");
        int fiboNum = 0;
        for (String s : str) {
            if (s.length() > 0) {
                fiboNum = Integer.valueOf(s);
            }
        }

        int first = 1;
        int second = 1;
        for (int i = 2; i < fiboNum; i++) {
            int temp = first;
            first = second;
            second = first + temp;
        }
        return second + "";

    }

    /// END KELLEN
    public String doJamesBond(String line) {
        if (line.contains("Dr No")) {
            return "Sean Connery";
        }
        return "foobar";
    }

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
