package com.example.cyberapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

public class PreTestHome extends AppCompatActivity {
    private TextView questionText, questionCounter;
    private RadioGroup radioGroup;
    private RadioButton answer1, answer2, answer3;
    private Button nextButton;
    private Button submitButton;
    private String currentQuizTitle = "Pre-Test Quiz";


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
        submitButton = findViewById(R.id.submitButton);
        submitButton.setVisibility(View.GONE);

        questions = new ArrayList<>();

        // determine order for correct answer in each question
        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_1),
                new String[]{
                        getString(R.string.preQ1A1Answer),
                        getString(R.string.preQ1A2Answer),
                        getString(R.string.preQ1A3Answer)
                },
                2 // correct answer index
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_2),
                new String[]{
                        getString(R.string.preQ2A1Answer),
                        getString(R.string.preQ2A2Answer),
                        getString(R.string.preQ2A3Answer)
                },
                1
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_3),
                new String[]{
                        getString(R.string.preQ3A1Answer),
                        getString(R.string.preQ3A2Answer),
                        getString(R.string.preQ3A3Answer)
                },
                1
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_4),
                new String[]{
                        getString(R.string.preQ4A1Answer),
                        getString(R.string.preQ4A2Answer),
                        getString(R.string.preQ4A3Answer)
                },
                1
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_5),
                new String[]{
                        getString(R.string.preQ5A2Answer),
                        getString(R.string.preQ5A3Answer),
                        getString(R.string.preQ5A1Answer),
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_6),
                new String[]{
                        getString(R.string.preQ6A1Answer),
                        getString(R.string.preQ6A2Answer),
                        getString(R.string.preQ6A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_7),
                new String[]{
                        getString(R.string.preQ7A1Answer),
                        getString(R.string.preQ7A2Answer),
                        getString(R.string.preQ7A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_8),
                new String[]{
                        getString(R.string.preQ8A1Answer),
                        getString(R.string.preQ8A2Answer),
                        getString(R.string.preQ8A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_9),
                new String[]{
                        getString(R.string.preQ9A1Answer),
                        getString(R.string.preQ9A2Answer),
                        getString(R.string.preQ9A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_10),
                new String[]{
                        getString(R.string.preQ10A1Answer),
                        getString(R.string.preQ10A2Answer),
                        getString(R.string.preQ10A3Answer)
                },
                1
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_11),
                new String[]{
                        getString(R.string.preQ11A1Answer),
                        getString(R.string.preQ11A2Answer),
                        getString(R.string.preQ11A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_12),
                new String[]{
                        getString(R.string.preQ12A1Answer),
                        getString(R.string.preQ12A2Answer),
                        getString(R.string.preQ12A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_13),
                new String[]{
                        getString(R.string.preQ13A1Answer),
                        getString(R.string.preQ13A2Answer),
                        getString(R.string.preQ13A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_14),
                new String[]{
                        getString(R.string.preQ14A1Answer),
                        getString(R.string.preQ14A2Answer),
                        getString(R.string.preQ14A3Answer)
                },
                0
        ));

        questions.add(new QuizQuestionLogic(
                getString(R.string.pretest_question_15),
                new String[]{
                        getString(R.string.preQ15A1Answer),
                        getString(R.string.preQ15A2Answer),
                        getString(R.string.preQ15A3Answer)
                },
                2
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

            int selectedIndex = radioGroup.indexOfChild(findViewById(selectedId));

            questions.get(currentIndex).setSelectedAnswer(selectedIndex);

            //currentIndex++;

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

        Intent intent = new Intent(PreTestHome.this, QuizResults.class);

        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", totalQuestions);
        intent.putExtra("QUIZ_TITLE", quizTitle);

        startActivity(intent);
        finish();
    }


}
