package com.example.keywords.dao;

import com.example.keywords.model.CommonThesaurus;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonThesaurusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonThesaurus record);

    int insertSelective(CommonThesaurus record);

    CommonThesaurus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonThesaurus record);

    int updateByPrimaryKey(CommonThesaurus record);
}