package com.ankitgaur.ikyu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class AptitudeQuestionBank {

    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    AptitudeDatabaseHelper mAptitudeDatabaseHelper;

    // method returns number of questions in list
    public int getLength(){
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }



    public void initQuestions(Context context) {
        mAptitudeDatabaseHelper = new AptitudeDatabaseHelper(context);
        list = mAptitudeDatabaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            mAptitudeDatabaseHelper.addInitialQuestion(new Question("1. The average of first 50 natural numbers is",
                    new String[]{" 25.30", "25.5", "25.00", "12.25"}, "25.5"));
            mAptitudeDatabaseHelper.addInitialQuestion(new Question("2.45% of 640 + 64% of 450 = _____ % of 1440",
                    new String[]{"54 ", "40", "45", " 50"}, "40 "));
            mAptitudeDatabaseHelper.addInitialQuestion(new Question("3.If 6 is 50% of a number, what is the number ?",
                    new String[]{"10", "11","12","13"}, "13"));
            mAptitudeDatabaseHelper .addInitialQuestion(new Question("4.45% of 640 + 64% of 450 = _____ % of 1440 ?",
                    new String[]{"54 ", " 40 ", " 45 ", " 50"}, " 40 "));
            mAptitudeDatabaseHelper.addInitialQuestion(new Question("5.The out one in following :4, 5, 10, 18, 34, 59, 95?",
                    new String[]{"  5 ", "10  ", " 18  ", "  34 "}, "10 "));

            list = mAptitudeDatabaseHelper.getAllQuestionsList();//get list from database again

        }
    }
}
