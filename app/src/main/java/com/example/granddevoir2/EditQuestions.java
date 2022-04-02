package com.example.granddevoir2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EditQuestions extends AppCompatActivity {


    public int numberOfPresses;

    public TextView textView;

    EditText question;
    EditText quizName;
    LinearLayout layout;
    public Button save;
    public Button button_newEditbox;
    public Button button_removeEditbox;

    private Button nextQuestion;
    private Button previousQuestion;

    public List<String> myStringArray;
    public List <EditText> myEditTextArray;
    public List <CheckBox> myCheckboxArray;
    public List <Integer> answerArray;
    DataBase mDatabaseHelper;
    private String x;

    private List<String>  listQuestions;
    private List<String> listquizName;
    private List<Integer> numberofpresses;
    private boolean nextOrPrev;
    private int numberQuestions;
    public EditQuestions(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_questions);


        question = findViewById(R.id.editText_question);
        quizName = findViewById(R.id.editText_QuizName);
        myEditTextArray = new ArrayList<EditText>();
        myStringArray = new ArrayList<String>();
        myCheckboxArray = new ArrayList<CheckBox>();
        answerArray = new ArrayList<Integer>();

        numberofpresses= new ArrayList<>();
        listQuestions = new ArrayList<>();
        listquizName = new ArrayList<>();

        textView = findViewById(R.id.textView);

        mDatabaseHelper = new DataBase(this);




        //add editbox answerers
        button_newEditbox =(Button)findViewById(R.id.button_add);
        button_newEditbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfPresses++;
                tryout();
            }
        });


        nextQuestion=findViewById(R.id.button_next);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextNewQuestion();
            }
        });



        save=findViewById(R.id.button_saveQuiz);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                x = quizName.getText().toString();



                if(numberOfPresses<=0){

                }else {
                    int nrAnswer=0;
                    for(int j=0;j<myEditTextArray.size();j++){
                        myStringArray.add(myEditTextArray.get(j).getText().toString());
                        if(!myEditTextArray.get(j).getText().toString().equals("")) {
                            if (myCheckboxArray.get(j).isChecked()) {
                                answerArray.add(nrAnswer);

                            }
                            nrAnswer++;
                        }
                    }
                    QuestionStorage stor = new QuestionStorage(question.getText().toString(),myStringArray,answerArray,x);
                    mDatabaseHelper.addQuestion(stor);
                    myStringArray.clear();

                    answerArray.clear();

                }

            }
        });


            //remove editbox answer
        button_removeEditbox=(Button)findViewById(R.id.button_less);
        button_removeEditbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if(numberOfPresses<=0){

                    }else{
                        layout.removeView(findViewById(100+numberOfPresses));
                        layout.removeView(findViewById(200+numberOfPresses));
                        myEditTextArray.remove(findViewById(200+numberOfPresses));
                        myCheckboxArray.remove(findViewById(100+numberOfPresses));
                        numberOfPresses--;
                    }
                textView.setText("Number: " +numberOfPresses);
            }
        });

    }

    public String getQuizName() {
        return x;
    }

    private void tryout() {
        CheckBox but = new CheckBox(this);
        EditText editText = new EditText(this);
        layout = (LinearLayout) findViewById(R.id.rootlayout);


        if (numberOfPresses > 0) {
            for (int i = numberOfPresses; i < numberOfPresses + 1; i++) {

                but.setId(100 + numberOfPresses);
                editText.setId(200 + numberOfPresses);

                but.setOnClickListener(getOnClick());
                but.setBackgroundColor(Color.parseColor("#ED7373"));
                editText.setBackgroundColor(Color.parseColor("#ED7373"));

                layout.addView(but);
                layout.addView(editText);

                myEditTextArray.add(editText);
                myCheckboxArray.add(but);


                textView.setText("Number:" + numberOfPresses);
            }
        }



    }

    private void nextNewQuestion(){
           numberOfPresses=0;
           textView.setText("Number: " +numberOfPresses);
            question.setText("");

            layout.removeAllViews();


    }

    private void SaveQuestionState(){
        listQuestions.add(question.getText().toString());
        listquizName.add(quizName.getText().toString());
        numberofpresses.add(numberOfPresses);

    }


    private void returnTOquestion(){

        question.setText(listQuestions.get(numberQuestions));
        quizName.setText(listquizName.get(numberQuestions));
        for(int i =0;i<myEditTextArray.size();i++) {
            layout.addView(myEditTextArray.get(i));
            layout.addView(myCheckboxArray.get(i));

        }
        numberQuestions--;
    }


    private View.OnClickListener getOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        };
    }

    public int getNumber(){
        return numberOfPresses;
    }

}