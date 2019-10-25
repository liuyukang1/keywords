package com.example.keywords.model;

public class CommonThesaurus {
    private Integer id;

    private String commonWords;

    private Double handleWeight;

    private Byte isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}