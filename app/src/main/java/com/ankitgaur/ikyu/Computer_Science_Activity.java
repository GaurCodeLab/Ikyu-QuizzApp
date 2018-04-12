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

public class Computer_Science_Activity extends AppCompatActivity {


    private CsQuestionBank mCsQuestionBank = new CsQuestionBank();
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    public static final String EXTRA_SCORE = "extraScore";

    private TextView mCsScoreView;   // view for current total score
    private TextView mCsQuestionView;  //current question to answer
    private Button mCsButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mCsButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mCsButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mCsButtonChoice4; // multiple choice 4 for mQuestionView

    private String mCsAnswer;  // correct answer for question in mQuestionView
    private int mCsScore = 0;  // current total score
    private int mCsQuestionNumber = 0; // current question number
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private ColorStateList textColorDefaultCd;
    private TextView textViewCountDown;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer__science_);

        mCsScoreView = (TextView)findViewById(R.id.Csscore);
        mCsQuestionView = (TextView)findViewById(R.id.Csquestion);
        mCsButtonChoice1 = (Button)findViewById(R.id.Cschoice1);
        mCsButtonChoice2 = (Button)findViewById(R.id.Cschoice2);
        mCsButtonChoice3 = (Button)findViewById(R.id.Cschoice3);
        mCsButtonChoice4 = (Button)findViewById(R.id.Cschoice4);
        textViewCountDown= findViewById(R.id.text_view_countdown);

        textColorDefaultCd = textViewCountDown.getTextColors();


        mCsQuestionBank.initQuestions(getApplicationContext());

        updateQuestion();
        // show current total score for the user
        updateScore(mCsScore);
    }

    private void updateQuestion(){
        // check if we are not outside array bounds for questions
        if(mCsQuestionNumber < mCsQuestionBank.getLength() ){
            // set the text for new question,
            // and new 4 alternative to answer on four buttons
            //
            mCsQuestionView.setText(mCsQuestionBank.getQuestion(mCsQuestionNumber));
            mCsButtonChoice1.setText(mCsQuestionBank.getChoice(mCsQuestionNumber, 1));
            mCsButtonChoice2.setText(mCsQuestionBank.getChoice(mCsQuestionNumber, 2));
            mCsButtonChoice3.setText(mCsQuestionBank.getChoice(mCsQuestionNumber, 3));
            mCsButtonChoice4.setText(mCsQuestionBank.getChoice(mCsQuestionNumber,4));
            mCsAnswer = mCsQuestionBank.getCorrectAnswer(mCsQuestionNumber);
            mCsQuestionNumber++;
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else {
            Toast.makeText(Computer_Science_Activity.this, "It was the last question!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Computer_Science_Activity.this, Highest_Score.class);
            intent.putExtra("score", mCsScore); // pass the current score to the second screen
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
                updateCountDownText();
                updateScore(mCsScore);
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
        mCsScoreView.setText(""+mCsScore+"/"+mCsQuestionBank.getLength());
    }

    public void onClick(View view) {
        //all logic for all answers buttons in one method
        Button answer = (Button) view;
        // if the answer is correct, increase the score
        if (answer.getText().equals(mCsAnswer)){
            countDownTimer.cancel();
            mCsScore = mCsScore + 1;
            Toast.makeText(Computer_Science_Activity.this, "Correct!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(Computer_Science_Activity.this, "Wrong!", Toast.LENGTH_SHORT).show();
        // show current total score for the user
        updateScore(mCsScore);
        // once user answer the question, we move on to the next one, if any
        updateQuestion();
    }


    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, mCsScore);
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
