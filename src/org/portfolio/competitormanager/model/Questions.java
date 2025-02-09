package org.portfolio.competitormanager.model;

public class Questions {
    private int question_id;
    private String question_text;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correct_option;
    private String difficulty;

    public Questions(int question_id, String question_text, String option1, String option2, String option3, String option4, int correct_option, String difficulty) {
        this.question_id = question_id;
        this.question_text = question_text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct_option = correct_option;
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectOption() {
        return correct_option;
    }

    public void setCorrectOption(int correct_option) {
        this.correct_option = correct_option;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getQuestionText() {
        return question_text;
    }

    public void setQuestionText(String question_text) {
        this.question_text = question_text;
    }

    public int getQuestionId() {
        return question_id;
    }

    public void setQuestionId(int question_id) {
        this.question_id = question_id;
    }
}
