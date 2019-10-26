package com.example.keywords.service;

import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.model.DocumentInformation;
import com.example.keywords.model.Synonyms;
import com.example.keywords.model.WordWeightAndRelaWeight;

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

    List<DocumentInformation> searchForInput(String text, Integer number);

    /**
     * 根据用户传入文本，筛选关键字
     * @return
     */
    List<String> getWords(String txt);

    /**
     * 获得关联词集 （远端）
     * @param wordList
     */
    Synonyms getRemoteSynonyWords(List<String> wordList);

    /**
     * 判断单词是否在数据库中存在
     * @param word
     * @return
     */
    CommonThesaurus checkWord(String word);

    /**
     * 对筛选后的待查询词集进行算法处理
     * @param preList
     * @return
     */
    List<WordWeightAndRelaWeight> dealTheResultList(List<WordWeightAndRelaWeight> preList);

    /**
     * 获得关联词集 （本地）
     * @param wordList
     */
    List<WordWeightAndRelaWeight>  getLocalSynonyWords(List<String> wordList);

    /**
     * 根据返回的关键字获得相关文章
     * @param keywords
     * @return
     */
    List<DocumentInformation> getDocuments(List<String> keywords);
}
