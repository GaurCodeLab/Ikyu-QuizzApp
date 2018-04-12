
package com.ankitgaur.ikyu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class GKQuestionBank {

    // declare list of Question objects
    List <Question> list = new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;

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
        myDataBaseHelper = new MyDataBaseHelper(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialQuestion(new Question("1.Who is the father of Geometry?",
                    new String[]{"Aristotle", "Euclid", "Pythagoras", "Kepler"}, "Euclid"));
            myDataBaseHelper.addInitialQuestion(new Question("2. Sarojini Naidu was elected Congress President at ?",
                    new String[]{"Haripura, 1938", "Bombay, 1934", "Madras Session, 1927", "Kanpur Session ,1925"}, "Kanpur Session ,1925"));
            myDataBaseHelper.addInitialQuestion(new Question("3. Office of the UN General Assembly is in?",
                    new String[]{"Vienna ", "New York", "Paris ", "Zurich"}, "New York"));
            myDataBaseHelper.addInitialQuestion(new Question("4.Who was known as Iron man of India  ?",
                    new String[]{"Ballabh Pant  ", "Jawaharlal Nehru  ", "Subhash Chandra Bose ", "Sardar Vallabhbhai Patel"}, "Sardar Vallabhbhai Patel"));

            list = myDataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }

}