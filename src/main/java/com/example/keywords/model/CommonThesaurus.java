package com.example.keywords.model;

import java.util.Date;

public class CommonThesaurus {
    private Integer id;

    private String commonWords;

    private Double weight;

    private Double handleWeight;

    private Integer documentId;

    private Date createTime;

    private Date updateTime;

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHandleWeight() {
        return handleWeight;
    }

    public void setHandleWeight(Double handleWeight) {
        this.handleWeight = handleWeight;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}