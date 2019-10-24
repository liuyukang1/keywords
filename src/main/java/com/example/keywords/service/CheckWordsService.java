package com.example.keywords.service;

import com.example.keywords.model.DocumentInformation;

import java.util.List;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
public interface CheckWordsService {


    // 根据返回的关键字获得相关文章
    List<DocumentInformation> getDocuments(List<String> keywords);
}
