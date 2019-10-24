package com.example.keywords.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static long lastClickTime;

    public static boolean isEmpty(String data) {
        if (data != null && !data.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取指定对象类型的list方法
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String data, T t) {
        //转为json字符串
        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();

        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(data).getAsJsonArray();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            T userBean = (T) gson.fromJson(user, t.getClass());
            list.add(userBean);
        }
        return list;
    }


    /**
     * 去掉字符串小数点
     *
     * @param str
     * @return
     */
    public static String removeDecimalPoint(String str) {
        int index = str.indexOf(".");
        if (index > 0) {
            str = str.substring(0, index);
        }
        return str;
    }

    public static String getUrlQueryResult(String url, String key) {
        url += "&";
        String pattern = "(\\?|&){1}#{0,1}" + key + "=[a-zA-Z0-9]*(&{1})";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if (m.find()) {
            System.out.println(m.group(0));
            return m.group(0).split("=")[1].replace("&", "");
        } else {
            return null;
        }

    }
}
