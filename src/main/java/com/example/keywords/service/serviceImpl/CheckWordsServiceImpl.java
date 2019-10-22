package com.example.keywords.service.serviceImpl;

import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.model.DocumentInformation;
import com.example.keywords.service.CheckWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service
public class CheckWordsServiceImpl implements CheckWordsService {

    @Autowired
    CommonThesaurusMapper commonThesaurusMapper;

    @Override
    public List<DocumentInformation> getDocuments(List<String> keywords) {
        return commonThesaurusMapper.getAllDocuments(keywords);
    }
}
