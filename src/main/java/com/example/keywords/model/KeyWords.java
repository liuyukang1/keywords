package com.example.keywords.model;

import java.util.HashMap;
import java.util.List;

public class KeyWords {
    List<String> keyWords;
    List<Double> score;

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public List<Double> getScore() {
        return score;
    }

    public void setScore(List<Double> score) {
        this.score = score;
    }
}
