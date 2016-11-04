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
            }
        } else {
            out = String.format("Unknown query: '%s'", query);
        }

        System.out.println(out);
        return out;
    }
}
