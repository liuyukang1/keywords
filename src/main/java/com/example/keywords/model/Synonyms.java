package com.example.keywords.model;

import java.util.List;

/**
 * 远端所获关联词对象
 */
public class Synonyms {

    List<List<List<String>>> synonymsWords;

    public List<List<List<String>>> getSynonymsWords() {
        return synonymsWords;
    }

    public void setSynonymsWords(List<List<List<String>>> synonymsWords) {
        this.synonymsWords = synonymsWords;
    }
}
