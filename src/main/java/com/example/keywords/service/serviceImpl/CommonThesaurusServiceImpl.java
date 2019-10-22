package com.example.keywords.service.serviceImpl;

import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.KeyWords;
import com.example.keywords.model.Synonyms;
import com.example.keywords.service.CommonThesaurusService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.keywords.base.BaseModel;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service
public class CommonThesaurusServiceImpl implements CommonThesaurusService {
   static final BaseModel baseModel = new BaseModel();
    @Autowired
    CommonThesaurusMapper commonThesaurusMapper;

    @Override
    public CommonThesaurus selectByPrimaryKey(Integer id) {
        return commonThesaurusMapper.selectByPrimaryKey(id);
    }

    static  public List<String> getKeywords(String txt){
        Map<String, String> map = new HashMap<>();
        map.put("txt",txt);
        String result  =  baseModel.getWithParamtersWithoutToken("getKeyWord",map);
        Gson gson = new Gson();
        KeyWords keyWords = gson.fromJson(result,  KeyWords.class);
        return keyWords.getKeyWords();
    }
    static  public Synonyms getSynonyms(KeyWords keyWords,String url){
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("keyWords",gson.toJson(keyWords.getKeyWords()));
        String result = baseModel.getWithParamtersWithoutToken("getSynonyms",map);
        Synonyms synonyms = gson.fromJson(result,Synonyms.class);
        return synonyms;


    }

    }
