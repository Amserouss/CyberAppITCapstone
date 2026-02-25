package com.example.cyberapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdvDevQuiz extends AppCompatActivity {
    private TextView questionText, questionCounter;
    private RadioGroup radioGroup;
    private RadioButton answer1, answer2, answer3;
    private Button nextButton;

    private List<QuizQuestionLogic> questions;
    private int currentIndex = 0;
    //4
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_quiz2);

        questionText = findViewById(R.id.questionText);
        questionCounter = findViewById(R.id.questionCounter);
        radioGroup = findViewById(R.id.radioGroup);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        nextButton = findViewById(R.id.nextButton);

        questions = new ArrayList<>();

        // determine order for correct answer in each question
        questions.add(new QuizQuestionLogic(
                getString(R.string.advWeb_question_1),
                new String[]{
                        getString(R.string.advWebQ1A1Answer),
                        getString(R.string.advWebQ1A2Answer),
                        getString(R.string.advWebQ1A3Answer),
                },
                0 // correct answer index
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.advWeb_question_2),
                new String[]{
                        getString(R.string.advWebQ2A1Answer),
                        getString(R.string.advWebQ2A2Answer),
                        getString(R.string.advWebQ2A3Answer)
                },
                1
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.advWeb_question_3),
                new String[]{
                        getString(R.string.advWebQ3A1Answer),
                        getString(R.string.advWebQ3A2Answer),
                        getString(R.string.advWebQ3A3Answer)
                },
                1
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.advWeb_question_4),
                new String[]{
                        getString(R.string.advWebQ4A1Answer),
                        getString(R.string.advWebQ4A2Answer),
                        getString(R.string.advWebQ4A3Answer)
                },
                1
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.advWeb_question_5),
                new String[]{
                        getString(R.string.advWebQ5A2Answer),
                        getString(R.string.advWebQ5A3Answer),
                        getString(R.string.advWebQ5A1Answer),
                },
                1
        ));

        loadQuestion(currentIndex);//determine where this goes and update
//6
        nextButton.setOnClickListener(v -> {

            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedIndex = radioGroup.indexOfChild(
                    findViewById(selectedId));

            questions.get(currentIndex).setSelectedAnswer(selectedIndex);

            currentIndex++;

            if (currentIndex < questions.size()) {
                loadQuestion(currentIndex);
            } else {
                calculateScore();
            }
        });

    }

    //7
    private void calculateScore() {

        int score = 0;

        for (QuizQuestionLogic q : questions) {
            if (q.getSelectedAnswer() == q.getCorrectAnswer()) {
                score++;
            }
        }

        Toast.makeText(this,
                "You scored " + score + " out of " + questions.size(),
                Toast.LENGTH_LONG).show();
    }


    //5
    private void loadQuestion(int index) {

        QuizQuestionLogic question = questions.get(index);

        questionCounter.setText("Question " + (index + 1) + " of " + questions.size());

        questionText.setText(question.getQuestionText());

        answer1.setText(question.getAnswers()[0]);
        answer2.setText(question.getAnswers()[1]);
        answer3.setText(question.getAnswers()[2]);

        radioGroup.clearCheck();

        if (question.getSelectedAnswer() != -1) {
            radioGroup.check(radioGroup.getChildAt(
                    question.getSelectedAnswer()).getId());
        }
    }

}
