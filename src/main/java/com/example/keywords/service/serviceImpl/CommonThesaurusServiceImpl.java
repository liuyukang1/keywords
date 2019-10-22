package com.example.keywords.service.serviceImpl;

import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.model.CommonThesaurus;
import com.example.keywords.service.CommonThesaurusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service
public class CommonThesaurusServiceImpl implements CommonThesaurusService {

    @Autowired
    CommonThesaurusMapper commonThesaurusMapper;

    @Override
    public CommonThesaurus selectByPrimaryKey(Integer id) {
        return commonThesaurusMapper.selectByPrimaryKey(id);
    }
}
