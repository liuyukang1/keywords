package com.example.keywords.dao;

import com.example.keywords.model.DocumentInformation;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentInformationMapper {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(DocumentInformation record);

    boolean insertSelective(DocumentInformation record);

    DocumentInformation selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(DocumentInformation record);

    boolean updateByPrimaryKeyWithBLOBs(DocumentInformation record);

    boolean updateByPrimaryKey(DocumentInformation record);
}