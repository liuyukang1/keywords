package com.example.keywords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lyk
 * @date: 10/25/2019
 */
@RestController
public class TextProcess {
    /**
     * 将传入的文本进行分词处理
     */
    public void participle() {

    }

    @RequestMapping("getWords")
    public List<String> getWords(String txt){
//        logger.info(txt);
        return null;
    }
}
