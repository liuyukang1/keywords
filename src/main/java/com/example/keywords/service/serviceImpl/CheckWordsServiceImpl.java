package com.example.keywords.service.serviceImpl;

import com.example.keywords.base.BaseModel;
import com.example.keywords.config.APIConfig;
import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.dao.DocumentInformationMapper;
import com.example.keywords.model.DocumentInformation;
import com.example.keywords.model.KeyWords;
import com.example.keywords.service.CheckWordsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service
public class CheckWordsServiceImpl implements CheckWordsService {

    static final Gson gson = new Gson();                // Json解析包
    Logger logger = Logger.getAnonymousLogger();        // 日志类
    static final BaseModel baseModel = new BaseModel(); // 网络请求封装

    @Autowired
    DocumentInformationMapper documentInformationMapper;

    /**
     * 获取分词
     * 遍历得到分词词集
     * 1. 若数据库存在该词，则调用关联词查询
     * 2. 若数据库不存在该词，则调用远端关联词查询
     * @param txt
     */
    @Override
    public void getKeyWords(String txt) {

        // 将用户文本传入远端解析
        Map<String, String> map = new HashMap<>();
        map.put("txt", txt);

        String result = baseModel.getWithParamtersWithoutToken(APIConfig.GET_WORDS, map);
    }

    public void saveWords() {

    }

    @Override
    public void getRemoteSynonyWords(List<String> wordList) {

    }

    @Override
    public void getLocalSynonyWords(List<String> wordList) {

    }

//    @Override
//    public List<DocumentInformation> getDocuments(List<String> keywords) {
//        return null;
//    }
}
