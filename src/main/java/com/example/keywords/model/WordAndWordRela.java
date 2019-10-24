package com.example.keywords.model;

public class WordAndWordRela {
    private Integer id;

    private Integer baseWordId;

    private Integer extendWordId;

    private Double weight;

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
}