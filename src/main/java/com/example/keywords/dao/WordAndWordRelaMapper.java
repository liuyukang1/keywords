package com.example.keywords.dao;

import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.WordAndWordRela;
import com.example.keywords.model.WordWeightAndRelaWeight;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;

@Repository
public interface WordAndWordRelaMapper {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(WordAndWordRela record);

    boolean insertSelective(WordAndWordRela record);

    WordAndWordRela selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(WordAndWordRela record);

    boolean updateByPrimaryKey(WordAndWordRela record);

    List<WordWeightAndRelaWeight> relatedWords(@Param("keywords")List<String> keywords);
}
