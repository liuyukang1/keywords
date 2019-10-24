package com.example.keywords.controller;

import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.DocumentInformation;
import com.example.keywords.model.KeyWords;
import com.example.keywords.model.Synonyms;
import com.example.keywords.service.CheckWordsService;
import com.example.keywords.service.CommonThesaurusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@RestController
public class First {

    @Autowired
    CommonThesaurusService commonThesaurusService;

    @Autowired
    CheckWordsService checkWordsService;

    Logger logger = Logger.getAnonymousLogger();

    @RequestMapping("get")
    public CommonThesaurus selectByPrimaryKey() {
        return commonThesaurusService.selectByPrimaryKey(1);
    }

    @RequestMapping("getWords")
    public List<String> getWords(String text){
        logger.info(text);
        return checkWordsService.getWords(text);
    }

    @RequestMapping("getDocuments")
    public List<DocumentInformation> getDocuments() {
        ArrayList<String> list = new ArrayList<>();
        list.add("学生");
        list.add("老师");
//        return checkWordsService.getDocuments(list);
        return null;
    }

    @RequestMapping("getKeywords")
    public KeyWords getKeywords(String txt) {
        logger.info(txt);
       return commonThesaurusService.getKeywords(txt);
    }

    @RequestMapping("getSynonyms")
    public Synonyms getSynonyms(String txt) {
        ArrayList list = new ArrayList();
        list.add("老师");
        list.add("学生");

        return checkWordsService.getRemoteSynonyWords(list);
    }
}
