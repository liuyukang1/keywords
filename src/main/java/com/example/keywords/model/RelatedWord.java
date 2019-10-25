package com.example.keywords.model;

public class RelatedWord {

    private String commonWords;

    private Double handleWeight;

    private Integer ID;

    private Byte isDelete;

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

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
