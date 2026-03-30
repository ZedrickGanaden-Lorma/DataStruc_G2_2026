package com.zedrick.model.question;


public abstract class Question {
    static enum Difficulty {
        EASY, MEDIUM, HARD
    }
    static enum Type{
        CHOICE, FILL
    }
    private final String text;
    private final String correctAnswer;
    private final Difficulty difficulty;
    public Question(String text, String correctAnswer, Difficulty difficulty) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
    }
    public String getText() {
        return text;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }


}
