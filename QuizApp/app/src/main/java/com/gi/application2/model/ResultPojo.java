package com.gi.application2.model;

public class ResultPojo {
    String result_id;
    String result_score;
    String attempt;
    String nonattempt;

    public String getResult_score() {
        return result_score;
    }

    public void setResult_score(String result_score) {
        this.result_score = result_score;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public String getNonattempt() {
        return nonattempt;
    }

    public ResultPojo(String result_id, String result_score, String attempt, String nonattempt) {
        this.result_id = result_id;
        this.result_score = result_score;
        this.attempt = attempt;
        this.nonattempt = nonattempt;
    }

    public ResultPojo(String result_score, String attempt, String nonattempt) {
        this.result_score = result_score;
        this.attempt = attempt;
        this.nonattempt = nonattempt;
    }

    public void setNonattempt(String nonattempt) {
        this.nonattempt = nonattempt;
    }

    public String getResult_id() {
        return result_id;
    }

    public void setResult_id(String result_id) {
        this.result_id = result_id;
    }

    @Override
    public String toString() {
        return "ResultPojo{" +
                "result_id='" + result_id + '\'' +
                ", result_score='" + result_score + '\'' +
                ", attempt='" + attempt + '\'' +
                ", nonattempt='" + nonattempt + '\'' +
                '}';
    }
}
