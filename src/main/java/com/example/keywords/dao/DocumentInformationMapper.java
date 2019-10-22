package com.example.keywords.dao;

import com.example.keywords.model.DocumentInformation;

public interface DocumentInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DocumentInformation record);

    int insertSelective(DocumentInformation record);

    DocumentInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DocumentInformation record);

    int updateByPrimaryKeyWithBLOBs(DocumentInformation record);

    int updateByPrimaryKey(DocumentInformation record);
}