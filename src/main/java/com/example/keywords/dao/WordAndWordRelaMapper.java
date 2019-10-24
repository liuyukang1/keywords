package com.example.keywords.dao;

import com.example.keywords.model.RelatedWord;
import com.example.keywords.model.WordAndWordRela;
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

    List<RelatedWord> relatedWords(List<String> keyWord);

}
