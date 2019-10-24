package com.example.keywords.service.serviceImpl;

import com.example.keywords.config.APIConfig;
import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.dao.DocumentInformationMapper;
import com.example.keywords.dao.WordDocumentRelaMapper;
import com.example.keywords.model.*;
import com.example.keywords.service.CommonThesaurusService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.keywords.base.BaseModel;
import org.springframework.transaction.annotation.Transactional;


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

    @Autowired
    private WordDocumentRelaMapper wordDocumentRelaMapper;

    @Override
    @Transactional
    public void initText(String text) {
        // 将文本传入远端解析
        Map<String, String> map = new HashMap<>();
        map.put("txt", text);

        String result = baseModel.getWithParamtersWithoutToken(APIConfig.GET_KEYWORD, map);
        KeyWords keyWords = gson.fromJson(result, KeyWords.class);

        logger.info(result);
        logger.info(keyWords.getKeyWords().size()+"");

        DocumentInformation documentInformation = new DocumentInformation();

        Integer theTextId = Integer.valueOf(createId().substring(3));
        documentInformation.setId(theTextId);
        documentInformation.setText(text);

        // 将文本存入数据库
        if(documentInformationMapper.insertSelective(documentInformation)) {
            saveWords(keyWords, theTextId);
        }
    }

    /**
     * 生成随机ID
     * @return
     */
    private String createId() {
        return String.valueOf(System.currentTimeMillis());
    }

    private void saveWords(KeyWords keyWords, Integer theTextId) {
        CommonThesaurus commonThesaurus = new CommonThesaurus();
        for (int element = 0; element < keyWords.getKeyWords().get(0).size(); element++) {
            commonThesaurus.setId(theTextId + element);
            commonThesaurus.setCommonWords(keyWords.getKeyWords().get(0).get(element));
            commonThesaurus.setHandleWeight(0.5);
            commonThesaurusMapper.insertSelective(commonThesaurus);

            WordDocumentRela wordDocumentRela = new WordDocumentRela();
            wordDocumentRela.setId(theTextId + element);
            wordDocumentRela.setDocumentId(theTextId);
            wordDocumentRela.setKeywordId(theTextId + element);
            wordDocumentRela.setWeight(Double.valueOf(keyWords.getKeyWords().get(1).get(element)));
            wordDocumentRelaMapper.insertSelective(wordDocumentRela);
        }
    }

}
