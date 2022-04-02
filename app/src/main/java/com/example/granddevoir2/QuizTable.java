package com.example.granddevoir2;

import android.os.Parcelable;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class QuizTable {



    public static class Table implements BaseColumns {
        public static EditQuestions obj = new EditQuestions();

        public static  String TABLE_NAME="Quiz_Table";
        public static String QUIZ = "quiz_name";
        public static String COLUMN_QUESTION="quiz_question";
        public static  String COLUMN_OPTION1="quiz_op1";
        public static  String COLUMN_OPTION2="quiz_op2";
        public static  String COLUMN_OPTION3="quiz_op3";
        public static  String COLUMN_OPTION4="quiz_op3";
        public static  String COLUMN_OPTION5="quiz_op3";
        public static  String COLUMN_OPTION6="quiz_op3";
        public static  String COLUMN_OPTION7="quiz_op3";
        public static  String COLUMN_OPTION8="quiz_op3";
        public static  String COLUMN_OPTION9="quiz_op3";
        public static  String COLUMN_OPTION10="quiz_op3";
        public static  String COLUMN_ANSWER="answer_op4";

        public List<String> list = new ArrayList<String>();

        Table(){
            doShit();
        }

        public void doShit() {
            for (int i = 0; i < 10; i++) {
                list.add("quiz_op" + Integer.toString(i));

            }
        }

    }
}
