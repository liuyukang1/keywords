package com.example.keywords.dao;

import com.example.keywords.model.WordDocumentRela;
import org.springframework.stereotype.Repository;

@Repository
public interface WordDocumentRelaMapper {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(WordDocumentRela record);

    boolean insertSelective(WordDocumentRela record);

    WordDocumentRela selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(WordDocumentRela record);

    boolean updateByPrimaryKey(WordDocumentRela record);
}