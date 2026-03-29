package com.example.cyberapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BegWebQuiz extends AppCompatActivity {
    private TextView questionText, questionCounter;
    private RadioGroup radioGroup;
    private RadioButton answer1, answer2, answer3;
    private Button nextButton;
    private Button submitButton;
    private String currentQuizTitle = "Beginner Web Security Quiz";

    private List<QuizQuestionLogic> questions;
    private int currentIndex = 0;
    //4
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beginner_quiz1);

        questionText = findViewById(R.id.questionText);
        questionCounter = findViewById(R.id.questionCounter);
        radioGroup = findViewById(R.id.radioGroup);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        nextButton = findViewById(R.id.nextButton);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setVisibility(View.GONE);

        questions = new ArrayList<>();

        // determine order for correct answer in each question
        questions.add(new QuizQuestionLogic(
                getString(R.string.begWeb_question_1),
                new String[]{
                        getString(R.string.begWebQ1A1Answer),
                        getString(R.string.begWebQ1A2Answer),
                        getString(R.string.begWebQ1A3Answer),
                },
                1 // correct answer index
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.begWeb_question_2),
                new String[]{
                        getString(R.string.begWebQ2A1Answer),
                        getString(R.string.begWebQ2A2Answer),
                        getString(R.string.begWebQ2A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.begWeb_question_3),
                new String[]{
                        getString(R.string.begWebQ3A1Answer),
                        getString(R.string.begWebQ3A2Answer),
                        getString(R.string.begWebQ3A3Answer)
                },
                2
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.begWeb_question_4),
                new String[]{
                        getString(R.string.begWebQ4A1Answer),
                        getString(R.string.begWebQ4A2Answer),
                        getString(R.string.begWebQ4A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.begWeb_question_5),
                new String[]{
                        getString(R.string.begWebQ5A2Answer),
                        getString(R.string.begWebQ5A3Answer),
                        getString(R.string.begWebQ5A1Answer),
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

            int selectedIndex = radioGroup.indexOfChild(findViewById(selectedId));
            questions.get(currentIndex).setSelectedAnswer(selectedIndex);

           // currentIndex++;

            if (currentIndex == questions.size() - 1) {

                nextButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int score = calculateScore();
                        int total = questions.size();

                        goToResults(score, total, currentQuizTitle);

                    }
                });
                //calculateScore();

            } else {

                currentIndex++;
                loadQuestion(currentIndex);
            }
        });

    }

    //7
    private int calculateScore() {

        int score = 0;

        for (QuizQuestionLogic q : questions) {
            if (q.getSelectedAnswer() == q.getCorrectAnswer()) {
                score++;
            }
        }

//        Toast.makeText(this,
//                "You scored " + score + " out of " + questions.size(),
//                Toast.LENGTH_LONG).show();
        return score;
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


    private void goToResults(int score, int totalQuestions, String quizTitle) {

        Intent intent = new Intent(BegWebQuiz.this, QuizResults.class);

        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", totalQuestions);
        intent.putExtra("QUIZ_TITLE", quizTitle);

        startActivity(intent);
        finish();
    }

}
