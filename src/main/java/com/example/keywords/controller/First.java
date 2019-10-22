package com.example.keywords.controller;

import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.DocumentInformation;
import com.example.keywords.service.CheckWordsService;
import com.example.keywords.service.CommonThesaurusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("get")
    public CommonThesaurus selectByPrimaryKey() {
        return commonThesaurusService.selectByPrimaryKey(1);
    }

    @RequestMapping("getDocuments")
    public List<DocumentInformation> getDocuments() {
        ArrayList<String> list = new ArrayList<>();
        list.add("学生");
        list.add("老师");
        return checkWordsService.getDocuments(list);
    }
}
