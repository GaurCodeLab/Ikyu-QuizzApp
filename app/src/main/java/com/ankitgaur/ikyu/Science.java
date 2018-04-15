package com.ankitgaur.ikyu;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Science extends AppCompatActivity {

    private ScienceQuestionBank mQuestionBank = new ScienceQuestionBank();
    private static final long COUNTDOWN_IN_MILLIS = 20000;
    public static final String EXTRA_SCORE = "extraScore";
    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ColorStateList textColorDefaultCd;
    private TextView textViewCountDown;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);

        // setup screen for the first question with four alternative to answer
        mScoreView = findViewById(R.id.score);
        mQuestionView = findViewById(R.id.question);
        mButtonChoice1 = findViewById(R.id.choice1);
        mButtonChoice2 = findViewById(R.id.choice2);
        mButtonChoice3 = findViewById(R.id.choice3);
        mButtonChoice4 = findViewById(R.id.choice4);
        textViewCountDown= findViewById(R.id.text_view_countdown);

        textColorDefaultCd = textViewCountDown.getTextColors();

        mQuestionBank.initQuestions(getApplicationContext());
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);
    }

    private void updateQuestion(){
        // check if we are not outside array bounds for questions
        if(mQuestionNumber < mQuestionBank.getLength() ){
            // set the text for new question,
            // and new 4 alternative to answer on four buttons
            //
            mQuestionView.setText(mQuestionBank.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionBank.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionBank.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionBank.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionBank.getChoice(mQuestionNumber,4));
            mAnswer = mQuestionBank.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else {
            Toast.makeText(Science.this, "It was the last question!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Science.this, Highest_Score.class);
            intent.putExtra("score", mScore); // pass the current score to the second screen
            startActivity(intent);


        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateQuestion();
                updateCountDownText();
                updateScore(mScore);
                countDownTimer.cancel();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }


    // show current total score for the user
    private void updateScore(int point) {
        mScoreView.setText(""+mScore+"/"+mQuestionBank.getLength());
    }

    public void onClick(View view) {
        //all logic for all answers buttons in one method
        Button answer = (Button) view;
        // if the answer is correct, increase the score
        if (answer.getText().equals(mAnswer)){
            countDownTimer.cancel();
            mScore = mScore + 1;
            Toast.makeText(Science.this, "Correct!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(Science.this, "Wrong!", Toast.LENGTH_SHORT).show();
        // show current total score for the user
        updateScore(mScore);
        // once user answer the question, we move on to the next one, if any
        updateQuestion();
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, mScore);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
