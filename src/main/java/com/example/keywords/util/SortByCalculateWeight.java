package com.example.keywords.util;

import com.example.keywords.model.WordWeightAndRelaWeight;

import java.util.Comparator;

/**
 * @author: lyk
 * @date: 10/26/2019
 */
public class SortByCalculateWeight implements Comparator {
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
