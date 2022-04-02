package com.example.granddevoir2;

import java.util.List;

public class QuestionStorage {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String quizName;


    private List<String> listOptions;
    private List<Integer> listAnswernr;


    public QuestionStorage(){

    }

    public QuestionStorage(String question,List<String> listOptions,List<Integer> listAnswernr, String quizName) {



        this.quizName=quizName;
        this.question = question;
        this.listAnswernr=listAnswernr;
        this.listOptions=listOptions;
    }


    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getListOptions() {
        return listOptions;
    }

    public void setListOptions(List<String> listOptions) {
        this.listOptions = listOptions;
    }

    public List<Integer> getListAnswernr() {
        return listAnswernr;
    }

    public void setListAnswernr(List<Integer> listAnswernr) {
        this.listAnswernr = listAnswernr;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}




