package com.example.granddevoir2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {


    private static EditQuestions obj = new EditQuestions();
    private static String DATABASE_NAME="DataBase";
    private static int DATABASE_VERSION=1;

    private static SQLiteDatabase db;
    private static final String TAG = "DataBaseHelper";
    QuizTable.Table table = new QuizTable.Table();


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        String firsthalf = "CREATE TABLE " +
                QuizTable.Table.TABLE_NAME + " ( " +
                QuizTable.Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizTable.Table.QUIZ + " TEXT, " +
                QuizTable.Table.COLUMN_QUESTION + " TEXT, " ;

        for(int i = 0; i<table.list.size();i++){
            firsthalf+=table.list.get(i) + " TEXT, " ;
        }

        firsthalf+=QuizTable.Table.COLUMN_ANSWER + " TEXT" +
                ")";



        db.execSQL(firsthalf);

        fillQuestionsTable();

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizTable.Table.TABLE_NAME);
        onCreate(db);
    }

    public void fillQuestionsTable(){

    }


    public void addQuestion(QuestionStorage question) {
        db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(QuizTable.Table.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizTable.Table.QUIZ,question.getQuizName());

      for(int i =0;i<question.getListOptions().size();i++) {
          if (!question.getListOptions().get(i).equals("")) {
              cv.put(table.list.get(i),question.getListOptions().get(i));
          }
      }
        String answers = "";
        for(int i =0;i<question.getListAnswernr().size();i++){

            answers+=Integer.toString(question.getListAnswernr().get(i))+ ',';

        }


        cv.put(QuizTable.Table.COLUMN_ANSWER, answers);


        db.insert(QuizTable.Table.TABLE_NAME, null, cv);




    }

    public List<QuestionStorage> getAllQuestions(String quizName){

        List<QuestionStorage> questionList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + QuizTable.Table.TABLE_NAME+" WHERE " + QuizTable.Table.QUIZ + " = '" + quizName + "'",null);

        if(c.moveToFirst()){
            do {
                QuestionStorage question = new QuestionStorage();

                question.setQuestion(c.getString(c.getColumnIndex(QuizTable.Table.COLUMN_QUESTION)));

                List<String> options = new ArrayList<String>();


                for(int i = 0;i<table.list.size();i++){

                    if(c.getString(c.getColumnIndex(table.list.get(i))) != null) {
                        options.add(c.getString(c.getColumnIndex(table.list.get(i))));
                    }
                }

                question.setListOptions(options);
                List<Integer>answersNr = new ArrayList<Integer>();
                String[] answers = c.getString(c.getColumnIndex(QuizTable.Table.COLUMN_ANSWER)).split(",");

                for(int i=0;i< answers.length;i++){
                    answersNr.add(Integer.parseInt(answers[i]));
                }

                question.setListAnswernr(answersNr);
                questionList.add(question);



            }while(c.moveToNext());
        }

        c.close();
        return questionList;
    }



    public ArrayList<String> getQuizNames(){
        ArrayList<String> questionList = new ArrayList<>();

        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT "+ QuizTable.Table.QUIZ +" FROM " + QuizTable.Table.TABLE_NAME,null);

        if(c.moveToFirst()){
            do {
                String question;
                question = c.getString(c.getColumnIndex(QuizTable.Table.QUIZ));



                if(!questionList.contains(question)){
                    questionList.add(question);
                }


            }while(c.moveToNext());
        }

        c.close();
        return questionList;

    }





}
