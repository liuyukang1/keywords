package com.example.keywords.dao;

import com.example.keywords.model.DocumentInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentInformationMapper {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(DocumentInformation record);

    boolean insertSelective(DocumentInformation record);

    List<DocumentInformation> getDocumentByKeyWord(@Param("keywords")List<String> keywords);

    boolean updateByPrimaryKeySelective(DocumentInformation record);

    boolean updateByPrimaryKeyWithBLOBs(DocumentInformation record);

    boolean updateByPrimaryKey(DocumentInformation record);
}
