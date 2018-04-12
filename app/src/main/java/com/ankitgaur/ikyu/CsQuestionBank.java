package com.ankitgaur.ikyu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CsQuestionBank {


    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    private CsMyDataHelper CsmyDataBaseHelper;

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
        CsmyDataBaseHelper = new CsMyDataHelper(context);
        list = CsmyDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            CsmyDataBaseHelper.addInitialQuestion(new Question("1.Protocol providing e-mail facility among different hosts",
                    new String[]{"FTP", "SMTP", "TELNET", "SNMP"}, "SMTP"));
            CsmyDataBaseHelper.addInitialQuestion(new Question("2. Basic architecture of computer was developed by?",
                    new String[]{"John Von Neumann ", "Charles Babbage", "Blaise Pascal", " Garden Moore"}, "ohn Von Neumann "));
            CsmyDataBaseHelper.addInitialQuestion(new Question("3.GUI stands for ?",
                    new String[]{"Graph Use Interface", "Graphical User Interface","Graphical Unique Interface","None of these"}, "Graphical User Interface"));
            CsmyDataBaseHelper.addInitialQuestion(new Question("4.Which network protocol is used to send e-mail ?",
                    new String[]{"FTP ", " SSH  ", "  POP3 ", " SMTP "}, " SMTP "));
            CsmyDataBaseHelper.addInitialQuestion(new Question("5.Which of the following memory is volatile ?",
                    new String[]{"  RAM ", " ROM  ", " EPROM  ", " PROM "}, " RAM "));

            list = CsmyDataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }
}
