package com.example.keywords.service.serviceImpl;

import com.example.keywords.base.BaseModel;
import com.example.keywords.config.APIConfig;
import com.example.keywords.dao.DocumentInformationMapper;
import com.example.keywords.dao.WordAndWordRelaMapper;
import com.example.keywords.model.*;
import com.example.keywords.service.CheckWordsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    WordAndWordRelaMapper wordAndWordRelaMapper;

    static final Gson gson = new Gson();                       // Json解析包
    static final BaseModel baseModel = new BaseModel();        // 网络请求封装
    static Logger logger = Logger.getAnonymousLogger();        // 日志类

    /**
     * 获取分词
     * 遍历得到分词词集
     * 通过本地和远端查找关联词
     * 最后返回文本信息
     *
     * @param text
     * @param number
     */
    @Override
    public void searchForInput(String text, Integer number) {

        // 远端获取文本分词
        ArrayList<String> searchWords = (ArrayList<String>) getWords(text);

        // 远端获取词集关联词集
        Synonyms synonyms = getRemoteSynonyWords(searchWords);

        // 本地获取词集关联词集
        List<CommonThesaurus> relatedWords = getLocalSynonyWords(searchWords);

        // 判断远端获取的词语是否存在本地词库中， 如果不存在，则将其加入
        for (int ssize = 0; ssize < synonyms.getSynonymsWords().size(); ssize++) {
            for (int wsize = 0; wsize < synonyms.getSynonymsWords().get(ssize).get(0).size(); wsize++) {
                synonyms.getSynonymsWords().get(ssize).get(0).get(wsize);
                boolean flag = false;
                for (int q = 0; q < relatedWords.size(); q++) {
                    if (synonyms.getSynonymsWords().get(ssize).get(0).get(wsize).equals(relatedWords.get(q).getCommonWords())) {
                        flag = true;
                    }
                }
                if (flag)
                    continue;
                CommonThesaurus commonThesaurus = new CommonThesaurus();
                commonThesaurus.setHandleWeight(new Double(synonyms.getSynonymsWords().get(ssize).get(1).get(wsize)));
                commonThesaurus.setCommonWords(synonyms.getSynonymsWords().get(ssize).get(0).get(wsize));
                relatedWords.add(commonThesaurus);
            }
        }

        // 清空原词集
//        searchWords.clear();

        // 将远端本地词集全部转入查询队列
        for (int t = 0; t < relatedWords.size(); t++) {
            searchWords.add(relatedWords.get(t).getCommonWords());
        }

        // 本地获取词集对应文本
        List<DocumentInformation> documentInformation = getDocuments(searchWords);
    }

    /**
     * 得到远端分词
     * @param text
     * @return
     */
    @Override
    public List<String> getWords(String text) {
        Map<String, String> map = new HashMap();
        map.put("text", text);

        String wordsStr = baseModel.getWithParamtersWithoutToken(APIConfig.GET_WORDS, map);
        HashMap<String, List<String>> result = gson.fromJson(wordsStr, HashMap.class);

        List<String> resultWords = result.get("words");
        return resultWords;
    }

    /**
     * 得到远端关联词
     * @param wordList
     * @return
     */
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

        map.put("keyWords", str);

        String result = baseModel.getWithParamtersWithoutToken(APIConfig.GET_SYNONYMS, map);
        Synonyms synonyms = gson.fromJson(result, Synonyms.class);

        return synonyms;
    }

    /**
     * 得到本地关联词
     * @param wordList
     * @return
     */
    @Override
    public List<CommonThesaurus> getLocalSynonyWords(List<String> wordList) {
        return wordAndWordRelaMapper.relatedWords(wordList);
    }

    /**
     * 获得词集对应文本集合
     * @param keywords
     * @return
     */
    @Override
    public List<DocumentInformation> getDocuments(List<String> keywords) {
        return documentInformationMapper.getDocumentByKeyWord(keywords);
    }
}