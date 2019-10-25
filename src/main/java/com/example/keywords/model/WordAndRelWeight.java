package com.example.keywords.model;

/**
 * 所获关联词语以及关联权重
 * @author: lyk
 * @date: 10/25/2019
 */
public class WordAndRelWeight {

    private String relationWord;

    private Double RelWeight;

    public String getRelationWord() {
        return relationWord;
    }

    public void setRelationWord(String relationWord) {
        this.relationWord = relationWord;
    }

    public Double getRelWeight() {
        return RelWeight;
    }

    public void setRelWeight(Double relWeight) {
        RelWeight = relWeight;
    }
}
