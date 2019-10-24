package com.example.keywords.service;

import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.KeyWords;
import com.example.keywords.model.Synonyms;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
public interface CommonThesaurusService {
    CommonThesaurus selectByPrimaryKey(Integer id);

    /**
     * 将传入文本进行关键词获取
     * @param txt
     * @return
     */
    KeyWords getKeywords(String txt);

    /**
     * 根据传入关键词进行关联词查询
     * @param keyWords
     * @param url
     * @return
     */
    Synonyms getSynonyms(KeyWords keyWords, String url);

    /**
     * 获取本地数据库中的关联词
     * @return
     */
    Synonyms getLocalSynonyms(KeyWords keyWords);

    /**
     * 初始化传入文本
     */
    void initText(String text);
}
