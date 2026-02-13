package com.example.cyberapp;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

public class PreTestHome extends AppCompatActivity {
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
        setContentView(R.layout.pre_test_home);

        questionText = findViewById(R.id.questionText);
        questionCounter = findViewById(R.id.questionCounter);
        radioGroup = findViewById(R.id.radioGroup);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        nextButton = findViewById(R.id.nextButton);

        questions = new ArrayList<>();

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_1),
                new String[]{
                        getString(R.string.Q1A1Answer),
                        getString(R.string.Q1A2Answer),
                        getString(R.string.Q1A3Answer)
                },
                0 // correct answer index
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_2),
                new String[]{
                        getString(R.string.Q2A1Answer),
                        getString(R.string.Q2A2Answer),
                        getString(R.string.Q2A3Answer)
                },
                1
        ));

// add remaining questions
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
