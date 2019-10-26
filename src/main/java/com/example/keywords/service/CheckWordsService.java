package com.example.keywords.service;

import com.example.keywords.model.DocumentInformation;
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
     * 对筛选后的待查询词集进行算法处理
     * @param preList
     * @return
     */
    List<WordWeightAndRelaWeight> dealTheResultList(List<WordWeightAndRelaWeight> preList);
}
