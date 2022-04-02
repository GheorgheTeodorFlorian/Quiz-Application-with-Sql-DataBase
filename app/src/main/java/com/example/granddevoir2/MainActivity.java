package com.example.granddevoir2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button startQuiz;
    Button customizeQuiz;
    Spinner spinner;
    String quizName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase db = new DataBase(this);
        ArrayList<String> quiznames = db.getQuizNames();

        spinner = findViewById(R.id.spinner_category);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,quiznames);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                quizName=text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       startQuiz = findViewById(R.id.button_Start_quiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionScreen.class).putExtra("quizname",quizName);
                startActivity(intent);
            }
        });

       customizeQuiz = findViewById(R.id.button_Customize_Quiz);

        customizeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditQuestions.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        DataBase db = new DataBase(this);
        ArrayList<String> quiznames = db.getQuizNames();
        spinner = findViewById(R.id.spinner_category);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,quiznames);
        spinner.setAdapter(adapter);

    }

}