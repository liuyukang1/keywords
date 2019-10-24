package com.example.keywords.service.serviceImpl;

import com.example.keywords.config.APIConfig;
import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.dao.DocumentInformationMapper;
import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.DocumentInformation;
import com.example.keywords.model.KeyWords;
import com.example.keywords.model.Synonyms;
import com.example.keywords.service.CommonThesaurusService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.keywords.base.BaseModel;


import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service
public class CommonThesaurusServiceImpl implements CommonThesaurusService {

    static final Gson gson = new Gson();                // Json解析包
    Logger logger = Logger.getAnonymousLogger();        // 日志类
    static final BaseModel baseModel = new BaseModel(); // 网络请求封装

    @Autowired
    private CommonThesaurusMapper commonThesaurusMapper;

    @Autowired
    private DocumentInformationMapper documentInformationMapper;


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

    @Override
    public Synonyms getLocalSynonyms(KeyWords keyWords) {
        return null;
    }

    @Override
    public void initText(String text) {
        // 将文本传入远端解析
        Map<String, String> map = new HashMap<>();
        map.put("txt", text);

        String result = baseModel.getWithParamtersWithoutToken(APIConfig.GET_KEYWORD, map);
        KeyWords keyWords = gson.fromJson(result, KeyWords.class);

        logger.info(result);
        logger.info(keyWords.getKeyWords().size()+"");

        DocumentInformation documentInformation = new DocumentInformation();

        Integer theTextId = Integer.valueOf(createId());
        documentInformation.setId(theTextId);
        documentInformation.setText(text);

        // 将文本存入数据库
        if(documentInformationMapper.insertSelective(documentInformation)) {
            saveWords(keyWords, theTextId);
        }
    }

    private String createId() {
        return String.valueOf(System.currentTimeMillis());
    }

    private void saveWords(KeyWords keyWords, Integer theTextId) {
        for (int temp = 0; temp < keyWords.getKeyWords().size(); temp++) {
            for (int element = 0; element < keyWords.getKeyWords().get(0).size(); element++) {

            }
        }
    }

}
