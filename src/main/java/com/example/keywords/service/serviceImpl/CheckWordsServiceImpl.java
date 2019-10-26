package com.example.keywords.service.serviceImpl;

import com.example.keywords.base.BaseModel;
import com.example.keywords.config.APIConfig;
import com.example.keywords.dao.CommonThesaurusMapper;
import com.example.keywords.dao.DocumentInformationMapper;
import com.example.keywords.dao.WordAndWordRelaMapper;
import com.example.keywords.model.*;
import com.example.keywords.service.CheckWordsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.logging.Logger;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
@Service
public class CheckWordsServiceImpl implements CheckWordsService {

    @Autowired
    private DocumentInformationMapper documentInformationMapper;

    @Autowired
    private CommonThesaurusMapper commonThesaurusMapper;

    @Autowired
    private WordAndWordRelaMapper wordAndWordRelaMapper;

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
    public List<DocumentInformation> searchForInput(String text, Integer number) {

        // 远端获取文本分词 (预查询列表)
        ArrayList<String> searchWords = (ArrayList<String>) getWords(text);

        // 如果没有词，则直接返回
        if (searchWords.size() == 0)
            return new ArrayList<>();

        // 远端获取词集关联词集
        // 属性：词体 关联权重
        Synonyms synonyms = getRemoteSynonyWords(searchWords);

        // 本地获取词集关联词集
        // 属性：词体 自身权重
        List<WordWeightAndRelaWeight> relatedWords = getLocalSynonyWords(searchWords);

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
                WordWeightAndRelaWeight word = new WordWeightAndRelaWeight();
                word.setWeight(new Double(synonyms.getSynonymsWords().get(ssize).get(1).get(wsize)));
                word.setCommonWords(synonyms.getSynonymsWords().get(ssize).get(0).get(wsize));
                relatedWords.add(word);
            }
        }

        // 词语筛选结果词集
        // 属性：词体 关联权重 自身权重
        List<WordWeightAndRelaWeight> resultList = new ArrayList<>();

        // 将预查询的队列进行处理
        // 将远端查询到的词对比本地数据库
        // 如果存在，则将其放入词语筛选结果集
        for (int rsize = 0; rsize < relatedWords.size(); rsize++) {
            WordWeightAndRelaWeight relatedWord = relatedWords.get(rsize);
            if (null != relatedWord.getHandleWeight())
                resultList.add(relatedWord);
            else {
                CommonThesaurus commonThesaurus = checkWord(relatedWord.getCommonWords());
                if (null != commonThesaurus) {
                    relatedWord.setHandleWeight(commonThesaurus.getHandleWeight());
                    resultList.add(relatedWord);
                }
            }
        }

        // 将列表进行权重计算处理
        resultList = dealTheResultList(resultList);

        // 将词集全部转入查询队列
        for (int t = 0; t < resultList.size(); t++) {
            searchWords.add(resultList.get(t).getCommonWords());
        }

        // 本地获取词集对应文本
        List<DocumentInformation> documentInformation = getDocuments(searchWords);

        return documentInformation;
    }

    /**
     * 将筛选结果
     * 进行进一步计算处理
     * @param preList
     * @return
     */
    private List<WordWeightAndRelaWeight> dealTheResultList(List<WordWeightAndRelaWeight> preList) {
        Collections.sort(preList, new SortByCalculateWeight());
        return preList;
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
     * 判断该词是否在本地存储
     *
     * @param word
     * @return
     */
    @Override
    public CommonThesaurus checkWord(String word) {
        return commonThesaurusMapper.selectByWord(word);
    }

    /**
     * 得到本地关联词集
     * @param wordList
     * @return
     */
    @Override
    public List<WordWeightAndRelaWeight> getLocalSynonyWords(List<String> wordList) {
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

class SortByCalculateWeight implements Comparator {
    public int compare(Object o1, Object o2) {
        WordWeightAndRelaWeight s1 = (WordWeightAndRelaWeight) o1;
        WordWeightAndRelaWeight s2 = (WordWeightAndRelaWeight) o2;
        if ((s1.getHandleWeight() * s1.getWeight()) > (s2.getHandleWeight() * s2.getWeight()))
            return 1;
        else if((s1.getHandleWeight() * s1.getWeight()) == (s2.getHandleWeight() * s2.getWeight()))
            return 0;
        else
            return -1;
    }
}

