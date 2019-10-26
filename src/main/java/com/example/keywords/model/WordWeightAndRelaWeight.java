package com.example.keywords.model;

/**
 * 词语信息
 * 其关联权重和权重
 *
 * @author: lyk
 * @date: 10/26/2019
 */
public class WordWeightAndRelaWeight {

    private String commonWords;

    private Double weight;

    private Double handleWeight;

    public String getCommonWords() {
        return commonWords;
    }

    public void setCommonWords(String commonWords) {
        this.commonWords = commonWords;
    }

    public Double getHandleWeight() {
        return handleWeight;
    }

    public void setHandleWeight(Double handleWeight) {
        this.handleWeight = handleWeight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
