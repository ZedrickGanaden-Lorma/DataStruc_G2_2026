package com.zedrick.model.question;

import java.util.ArrayList;

public class ChoiceQuestion extends Question {
    private ArrayList<String> choices;
    public ChoiceQuestion(String text, String correctAnswer, Difficulty difficulty) {
        super(text, correctAnswer, difficulty);
    }

}
