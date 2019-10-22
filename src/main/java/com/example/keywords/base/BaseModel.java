package com.example.keywords.base;

import com.example.keywords.config.Config;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author: lyk
 * @date: 10/23/2019
 */
public class BaseModel {
    /**
     * get不带token带parameter获得数据
     * @param url
     */
    public String getWithParamtersWithoutToken(String url, Map<String, String> params) {

        //1 构造Request
        HttpUrl.Builder httpBuider = HttpUrl.parse(Config.BaseUrl + url).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                httpBuider.addQueryParameter(param.getKey(), param.getValue());
            }
        }
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(httpBuider.build())
                .build();

        //2 将Request提交执行
        OkHttpClient client;
        client = new OkHttpClient();
        try{
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * post不带token请求获得数据
     * @param url
     * @param bodyParams
     */
    public String postWithoutToken(String url, Map<String, String> bodyParams) {
        // 构造RequestBody
        RequestBody body = setRequestBody(bodyParams);

        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder
                .url(Config.BaseUrl + url)
                .post(body)
                .build();

        // 将Request提交执行
        OkHttpClient client;
        client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
        }
    }

    /**
     * post的请求参数，构造RequestBody
     * @param bodyParams
     * @return
     */
    private RequestBody setRequestBody(Map<String, String> bodyParams) {
        MediaType json  = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(json,gson.toJson(bodyParams));

        return body;
    }
}
