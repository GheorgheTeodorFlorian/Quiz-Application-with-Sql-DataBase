package com.example.granddevoir2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuestionScreen extends AppCompatActivity {

    private TextView textViewQuestion;
    private String quizName;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textviewCountDown;
    private ColorStateList textColorDefaultRb;
    private int answerNr;
    private Button buttonConfirmNext;
    private List<Integer> correctAnswerNr;
    private List<RadioButton> rbArray;
    private List<Integer> answernrArray;
    private List<QuestionStorage> questionList;
    private List<QuestionStorage> answerList;
    ArrayList<Object> arr;
    private int questionCounter;
    private int questionCountTotal;
        EditQuestions obj = new EditQuestions();

    private QuestionStorage currentQuestion;
    private LinearLayout layout;
    private RadioGroup rbgroup;
    private int score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        layout = findViewById(R.id.layout);

        correctAnswerNr= new ArrayList<Integer>();
        arr = new ArrayList<Object>();
        rbgroup = findViewById(R.id.radiogroup);
        textViewQuestion = findViewById(R.id.textView_question);
        textViewScore = findViewById(R.id.textView_score);
        textViewQuestionCount=findViewById(R.id.textView_currentQuestion);
        textviewCountDown=findViewById(R.id.textView_timer);
        buttonConfirmNext=findViewById(R.id.button_confirm);
        rbArray= new ArrayList<RadioButton>();
        answernrArray= new ArrayList<Integer>();

        DataBase db = new DataBase(this);
        Intent intent = getIntent();
        quizName = intent.getStringExtra("quizname");
        System.out.println(quizName);
        questionList = db.getAllQuestions(quizName);
        questionCountTotal = questionList.size();


        showNextQuestion();



        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    boolean correct=true;
                    for(int i=0;i<rbArray.size();i++){

                        if(rbArray.get(i).isChecked() != currentQuestion.getListAnswernr().contains(i)){
                            correct = false;
                        }

                        if(currentQuestion.getListAnswernr().contains(i)){

                            rbArray.get(i).setTextColor(Color.GREEN);

                        }else{
                            rbArray.get(i).setTextColor(Color.RED);
                        }



                    }

                    for(int i =0;i<currentQuestion.getListAnswernr().size();i++) {
                        System.out.println(currentQuestion.getListAnswernr().get(i));
                    }

                    answered=true;
                    buttonConfirmNext.setText("Next");

                    if(correct){
                        score++;
                        textViewScore.setText("Score: " + score);
                    }

                }else{
                    rbArray.clear();

                    //layout.removeAllViews();
                    rbgroup.removeAllViews();
                    showNextQuestion();
                }
            }
        });

    }








    private void showNextQuestion(){

        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());

            for(int i=0;i<currentQuestion.getListOptions().size();i++){


                RadioButton rb = new RadioButton(this);

                if(currentQuestion.getListOptions().get(i) != null) {

                    rb.setText(currentQuestion.getListOptions().get(i));
                    rb.setId(200 + i);

                    rb.setOnClickListener(getOnClick(rb));



                    rbArray.add(rb);
                    rbgroup.addView(rb);

                    //layout.addView(rb);

                }
            }

            questionCounter++;

            textViewQuestionCount.setText("Question" + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
        }else{
            finishQuiz();
        }

    }


    private View.OnClickListener getOnClick(RadioButton rb) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb.isSelected()){
                    rb.setSelected(false);
                    rb.setChecked(false);
                }else{
                    rb.setSelected(true);
                    rb.setChecked(true);
                }
            }
        };
    }






// how do i set both the answer numbers and the answer text to the generated questions is my current issue.






   /* private void showNextQuestion() {



        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);
            questionCounter++;
            textViewQuestion.setText(currentQuestion.getQuestion());

            textViewQuestionCount.setText("Question" + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
        }else{
            finishQuiz();
        }



        for(int i=0;i<currentQuestion.getListOptions().size();i++){
            currentQuestion = questionList.get(questionCounter);
            RadioButton rb = new RadioButton(this);

            rb.setText(currentQuestion.getListOptions().get(i));
            rb.setId(200+i);
            rbArray.add(rb);
            rbgroup.addView(rb);



        }



    } */

    /*private void checkAnswer(){
        answered = true;
        // multiple radio buttons this wont work
        for(int i=0;i<currentQuestion.getListAnswernr().size();i++){
            if(currentQuestion.getListAnswernr().get(i)==rbArray.get(i)){

            }
        }


        RadioButton rbSelected = findViewById(rbgroup.getCheckedRadioButtonId());
        answerNr = rbgroup.indexOfChild(rbSelected);
        //Find different solution to finding checked radiobutton


        for(int i=0;i<currentQuestion.getListAnswernr().size();i++) {
            if (answerNr == currentQuestion.getListAnswernr().get(i)){
                score++;
                textViewScore.setText("Score: " + score);
            }

        }
        showSolution();
    } */


   /* private void showSolution(){

            for(int i=0;i<rbArray.size();i++){
                rbArray.get(i).setTextColor(Color.RED);

            }

            for(int i=0;i<currentQuestion.getListAnswernr().size();i++) {

                if (answerNr == currentQuestion.getListAnswernr().get(i)) {
                   rbArray.get(i).setTextColor(Color.GREEN);
                   textViewQuestion.setText("Answer: " + i + "is correct");



                }
            }

            if(questionCounter < questionCountTotal){
                buttonConfirmNext.setText("Next");
            }else{
                buttonConfirmNext.setText("Finish");
            }
    } */



    /* private void deleteCurrentQuestion(){

        for(int i=0;i<currentQuestion.getListOptions().size();i++){


            rbArray.clear();
            rbgroup.removeAllViews();



        }

    } */

    private void finishQuiz(){
        finish();
    }


}