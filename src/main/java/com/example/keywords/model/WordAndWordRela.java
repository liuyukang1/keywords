package com.example.keywords.model;

import java.util.Date;

public class WordAndWordRela {
    private Integer id;

    private Integer baseWordId;

    private Integer extendWordId;

    private Double weight;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseWordId() {
        return baseWordId;
    }

    public void setBaseWordId(Integer baseWordId) {
        this.baseWordId = baseWordId;
    }

    public Integer getExtendWordId() {
        return extendWordId;
    }

    public void setExtendWordId(Integer extendWordId) {
        this.extendWordId = extendWordId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
}