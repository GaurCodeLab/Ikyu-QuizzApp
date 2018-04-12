package com.ankitgaur.ikyu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ScienceQuestionBank {
    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    ScienceDataHelper mScienceDataHelper;

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
        mScienceDataHelper = new ScienceDataHelper(context);
        list = mScienceDataHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            mScienceDataHelper.addInitialQuestion(new Question("1. Hardest substance available on earth",
                    new String[]{"Gold", "Iron", "Diamond", "Platinum"}, "Diamond"));
            mScienceDataHelper.addInitialQuestion(new Question("2. Motor  NOT suitable for use as a DC machine?",
                    new String[]{"Permanent magnet motor ", "Series motor", "Squirrel cage motor", " Synchronous motor"}, "Squirrel cage motor "));
            mScienceDataHelper.addInitialQuestion(new Question("3.Washing soda is the common name for ?",
                    new String[]{"Sodium Carbonate", "Calcium Bicarbonate","Sodium Bicarbonate","Calcium Carbonate"}, "Sodium Carbonate"));
            mScienceDataHelper.addInitialQuestion(new Question("4.Tetraethyl lead is used as ?",
                    new String[]{"Pain Killer ", " Fire Extinguisher  ", "  Mosquito Repellent ", " Petrol Additive "}, " Petrol Additive "));
           mScienceDataHelper.addInitialQuestion(new Question("5.What is laughing gas ?",
                    new String[]{"  Nitrous Oxide ", " Carbon monoxide  ", " Sulphur dioxide  ", " Hydrogen peroxide "}, " Nitrous Oxide "));

            list = mScienceDataHelper.getAllQuestionsList();//get list from database again

        }
    }
}
