package com.example.keywords.dao;

import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.DocumentInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonThesaurusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonThesaurus record);

    int insertSelective(CommonThesaurus record);

    CommonThesaurus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonThesaurus record);

    int updateByPrimaryKey(CommonThesaurus record);

    List<DocumentInformation> getAllDocuments(@Param("keywords") List<String> keywords);
}