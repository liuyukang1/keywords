package com.example.keywords.dao;

import com.example.keywords.model.CommonThesaurus;

public interface CommonThesaurusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonThesaurus record);

    int insertSelective(CommonThesaurus record);

    CommonThesaurus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonThesaurus record);

    int updateByPrimaryKey(CommonThesaurus record);
}