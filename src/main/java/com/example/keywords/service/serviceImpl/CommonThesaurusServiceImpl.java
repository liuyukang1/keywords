package com.example.keywords.service.serviceImpl;

import com.example.keywords.config.APIConfig;
import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.KeyWords;
import com.example.keywords.model.Synonyms;
import com.example.keywords.service.CommonThesaurusService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.keywords.base.BaseModel;


import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service
public class CommonThesaurusServiceImpl implements CommonThesaurusService {

    static final BaseModel baseModel = new BaseModel();

    Logger logger = Logger.getAnonymousLogger();

    @Autowired
    CommonThesaurusMapper commonThesaurusMapper;


    @Override
    public CommonThesaurus selectByPrimaryKey(Integer id) {
        return commonThesaurusMapper.selectByPrimaryKey(id);
    }

    @Override
    public KeyWords getKeywords(String txt){
        Map<String, String> map = new HashMap<>();
        try{
            map.put("txt",txt);
        }catch (Exception e) {
            e.printStackTrace();
        }

        String result = baseModel.getWithParamtersWithoutToken(APIConfig.GET_KEYWORD, map);
        Gson gson = new Gson();

        KeyWords keyWords = gson.fromJson(result,KeyWords.class);
        logger.info(keyWords.getKeyWords().size()+"");
        return keyWords;
    }

    @Override
    public Synonyms getSynonyms(KeyWords keyWords, String url) {
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();

        String str = new String();
        for (int i = 0; i < keyWords.getKeyWords().size(); i++ ) {
            if(i != keyWords.getKeyWords().size() - 1)
                str += keyWords.getKeyWords().get(i) + ",";
            else
                str += keyWords.getKeyWords().get(i);
        }
//        map.put("keyWords", str);

        map.put("keyWords",gson.toJson(keyWords.getKeyWords()));
        String result = baseModel.getWithParamtersWithoutToken(APIConfig.GET_SYNONYMS, map);
        Synonyms synonyms = gson.fromJson(result, Synonyms.class);
        return synonyms;
    }

//    获取用户搜索关键字

    public List<String> getWords(String txt){
        Map<String,String> map = new HashMap<>();
        map.put("txt",txt);
        String wordsStr = baseModel.getWithParamtersWithoutToken(APIConfig.GET_WORDS,map);
        logger.info(wordsStr);
        Gson gson = new Gson();
        HashMap<String,List<String>> result = gson.fromJson(wordsStr, HashMap.class);
        List<String> resultWords = result.get("words");

        return resultWords;
    }
}
