package com.example.keywords.dao;

import com.example.keywords.model.CommonThesaurus;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonThesaurusMapper {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(CommonThesaurus record);

    boolean insertSelective(CommonThesaurus record);

    Integer ifInTheTable(String commonWords);

    CommonThesaurus selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(CommonThesaurus record);

    boolean updateByPrimaryKey(CommonThesaurus record);
}