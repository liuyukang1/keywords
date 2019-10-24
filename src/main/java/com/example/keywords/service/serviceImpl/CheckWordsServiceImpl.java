package com.example.keywords.service.serviceImpl;

import com.example.keywords.base.BaseModel;
import com.example.keywords.config.APIConfig;
import com.example.keywords.dao.DocumentInformationMapper;
import com.example.keywords.model.DocumentInformation;
import com.example.keywords.model.Synonyms;
import com.example.keywords.service.CheckWordsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service

public class CheckWordsServiceImpl implements CheckWordsService {
    @Autowired
    DocumentInformationMapper documentInformationMapper;
    static final Gson gson = new Gson();                // Json解析包
    Logger logger = Logger.getAnonymousLogger();        // 日志类
    static final BaseModel baseModel = new BaseModel(); // 网络请求封装
    static final HashMap<String,String> map = new HashMap();




    @Override
    public void searchForInput(String text, Integer number) {
       List<String> searchWords =  getWords(text);

       List<DocumentInformation> documentInformation = documentInformationMapper.getDocumenForKeyWord(searchWords,number);

       if (documentInformation.size() > number){
//          List<DocumentInformation> documentInformationsFRW  = documentInformationMapper.getDOcumentForRelatedWord(searchWords,number);
//                  for (int i = 0;i<documentInformationsFRW.size();i++){
//                      for (int j = 0 ;j<documentInformation.size() ;j++){
//                          if (documentInformation.get(i).getId() != documentInformationsFRW.get(j).getId()){
//                              documentInformation.add(documentInformationsFRW.get(j));
//                          }
//                      }
//                  }
        

       }
    }


    /**
     * 获取分词
     * 遍历得到分词词集
     * 1. 若数据库存在该词，则调用关联词查询
     * 2. 若数据库不存在该词，则调用远端关联词查询
     *
     * @param text
     * @return
     */
    @Override
    public List<String> getWords(String text) {
        map.put("text", text);
        String wordsStr = baseModel.getWithParamtersWithoutToken(APIConfig.GET_WORDS, map);
        HashMap<String, List<String>> result = gson.fromJson(wordsStr, HashMap.class);
        List<String> resultWords = result.get("words");
        return resultWords;

    }

    @Override
    public Synonyms getRemoteSynonyWords(List<String> wordList) {
        Map<String, String> map = new HashMap<>();
        Gson gson = new Gson();

        String str = new String();
        for (int i = 0; i < wordList.size(); i++) {
            if (i != wordList.size() - 1)
                str += wordList.get(i) + ",";
            else
                str += wordList.get(i);
        }
//        map.put("keyWords", str);

        map.put("keyWords", str);
        String result = baseModel.getWithParamtersWithoutToken(APIConfig.GET_SYNONYMS, map);
        Synonyms synonyms = gson.fromJson(result, Synonyms.class);
        return synonyms;
    }

//    public void saveWords() {
//
//    }




    @Override
    public void getLocalSynonyWords(List<String> wordList) {

    }

//    @Override
//    public List<DocumentInformation> getDocuments(List<String> keywords) {
//        return null;
//    }
}
