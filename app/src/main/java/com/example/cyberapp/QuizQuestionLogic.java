package com.example.cyberapp;

import androidx.appcompat.app.AppCompatActivity;


public class QuizQuestionLogic extends AppCompatActivity {

    private String questionText;
    private String[] answers;
    private int selectedAnswer = -1;
    private int correctAnswer;

    public QuizQuestionLogic(String questionText, String[] answers, int correctAnswer) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() { return questionText; }
    public String[] getAnswers() { return answers; }
    public int getSelectedAnswer() { return selectedAnswer; }
    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
    public int getCorrectAnswer() { return correctAnswer; }
}
