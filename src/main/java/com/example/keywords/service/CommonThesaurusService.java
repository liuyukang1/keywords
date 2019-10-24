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

    KeyWords getKeywords(String txt);

    Synonyms getSynonyms(KeyWords keyWords, String url);
}
