package com.example.keywords.model;

import java.util.List;

/**
 * 远端获取分词对象
 */
public class KeyWords {

    List<List<String>> keyWords;

    public List<List<String>> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<List<String>> keyWords) {
        this.keyWords = keyWords;
    }
}
