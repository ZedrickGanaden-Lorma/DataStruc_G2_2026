package com.zedrick.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.zedrick.model.question.Question;


public class QuizService {
    public static RuntimeTypeAdapterFactory<Question> adapter = RuntimeTypeAdapterFactory
            .of(Question.class, "type", true) // "type" is the field name in JSON
            .registerSubtype(.class, .SALARIED.name())
            .registerSubtype(.class, .HOURLY.name());

    public static Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).create();

}