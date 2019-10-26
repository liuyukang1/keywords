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

    /**
     * 根据 词体 查询词语信息
     * @param word
     * @return
     */
    CommonThesaurus selectByWord(String word);

    boolean updateByPrimaryKeySelective(CommonThesaurus record);

    boolean updateByPrimaryKey(CommonThesaurus record);
}