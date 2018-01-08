package com.fun.guptas.littlequiz;

/**
 * Created by guptas on 12/20/2017.
 */

public class LoadData {

    public String question;
    public String answer;
    public String hint;
    public String wiki;

    public LoadData(String question, String answer, String hint, String wiki) {
        this.question = question;
        this.answer = answer;
        this.hint = hint;
        this.wiki = wiki;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getHint() {
        return hint;
    }

    public String getWiki() {
        return wiki;
    }
}