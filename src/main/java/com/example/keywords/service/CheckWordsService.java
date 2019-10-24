package com.example.keywords.service;

import com.example.keywords.model.Synonyms;

import java.util.List;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
public interface CheckWordsService {

    /**
     * 根据用户输入查询文章
     * @param text
     */

    void searchForInput(String text,Integer number);
    /**
     * 根据用户传入文本，筛选关键字
     * @return
     */
    List<String> getWords(String txt);

    /**
     * 获得关联词 （远端）
     * @param wordList
     */
    Synonyms getRemoteSynonyWords(List<String> wordList);



    /**
     * 获得关联词 （本地）
     * @param wordList
     */
    void getLocalSynonyWords(List<String> wordList);

    /**
     * 根据返回的关键字获得相关文章
     * @param keywords
     * @return
     */
//    List<DocumentInformation> getDocuments(List<String> keywords);
}
