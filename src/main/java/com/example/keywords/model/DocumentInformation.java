package com.example.keywords.model;

public class DocumentInformation {
    private Integer id;

    private Byte isDelete;

    private String text;

    private  Integer realWeight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRealWeight() {
        return realWeight;
    }
}
