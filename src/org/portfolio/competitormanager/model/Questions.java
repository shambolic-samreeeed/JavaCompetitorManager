package org.portfolio.competitormanager.model;

/**
 * This class represents a question in the quiz
 * stores the details of the question
 */
public class Questions {
    private int questionId;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctOption;
    private String difficulty;

    /**
     * Constructs a new question
     * @param questionId the question id of the question
     * @param questionText the actual question
     * @param option1 option 1
     * @param option2 option2
     * @param option3 option3
     * @param option4 option 4
     * @param correctOption the correct answer to the question
     * @param difficulty the difficulty of the question.
     */
    public Questions(int questionId, String questionText, String option1, String option2, String option3, String option4, int correctOption, String difficulty) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.difficulty = difficulty;
    }

    //getter methods. no setter methods to make it immutable and to avoid local modification since data is to come from the database
    public int getQuestionId() {
        return questionId; }

    public String getQuestionText() {
        return questionText; }

    public String getOption1() {
        return option1; }

    public String getOption2() {
        return option2; }

    public String getOption3() {
        return option3; }

    public String getOption4() {
        return option4; }

    public int getCorrectOption() {
        return correctOption; }

    public String getDifficulty() {
        return difficulty; }
}