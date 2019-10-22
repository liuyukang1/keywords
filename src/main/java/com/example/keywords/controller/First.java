package com.example.keywords.controller;

import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.service.CommonThesaurusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@RestController
public class First {

//    @Autowired
    CommonThesaurusService commonThesaurusService;

    @RequestMapping("get")
    public CommonThesaurus selectByPrimaryKey() {
        return commonThesaurusService.selectByPrimaryKey(1);
    }
}
