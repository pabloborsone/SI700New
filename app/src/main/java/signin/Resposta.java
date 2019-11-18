package signin;

public class Resposta {
    private String question;
    private String answer;
    private String chosen;

    public Resposta() {
    }

    public Resposta(String question, String answer, String chosen) {
        this.answer = answer;
        this.chosen = chosen;
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setChosen(String chosen) {
        this.chosen = chosen;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getAnswer() {
        return answer;
    }

    public String getChosen() {
        return chosen;
    }

    public String getQuestion() {
        return question;
    }
}
