package com.goyo.towermodule.net;

import android.support.annotation.NonNull;

import com.goyo.towermodule.util.CommUtils;
import com.goyo.towermodule.util.retrofit.JsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by JarvisLau on 2018/5/7.
 * Description :
 */

public class RetrofitUtils {

    private RetrofitParams retrofitParams;
    private static RetrofitUtils retrofitUtils;
    public static final String BASE_URL = "https://app.igongdi.cn/project/";

    private RetrofitUtils() {
        if (retrofitParams == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitParams == null) {
                    OkHttpClient.Builder client = new OkHttpClient.Builder();
                    client.addInterceptor(new HeaderInterceptor());
                    retrofitParams = new Retrofit.Builder().
                            baseUrl(BASE_URL).
                            addConverterFactory(JsonConverterFactory.create()).
                            client(client.build()).
                            build().create(RetrofitParams.class);
                }
            }
        }
    }

    public static RetrofitParams getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils.retrofitParams;
    }


    private class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            String nonceKey = URLEncoder.encode("nonce", "utf-8");
            String nonceValue = URLEncoder.encode(String.valueOf(CommUtils.getNonce()), "utf-8");
            String timestampKey = URLEncoder.encode("timestamp", "utf-8");
            String timestampValue = URLEncoder.encode(String.valueOf(CommUtils.getTime()), "utf-8");
            if (request.method().equals("GET")) {
                HashMap<String, String> queryMap = new HashMap<>();
                HttpUrl.Builder queryParameterBuilder = request.url().newBuilder();
                queryParameterBuilder.addQueryParameter(nonceKey, nonceValue);
                queryParameterBuilder.addQueryParameter(timestampKey, timestampValue);
                queryMap.put(nonceKey, nonceValue);
                queryMap.put(timestampKey, timestampValue);
                for (int i = 0; i < request.url().querySize(); i++) {
                    String key = request.url().queryParameterName(i);
                    String value = request.url().queryParameterValue(i);
                    queryParameterBuilder.removeAllQueryParameters(key);
                    queryParameterBuilder.addQueryParameter(key, value);
                    queryMap.put(key, value);
                }
                queryParameterBuilder.addQueryParameter("sign", CommUtils.getSign(CommUtils.sortMapByKey(queryMap)));
                try {
                    builder.url(queryParameterBuilder.build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (request.method().equals("POST")) {
                if (request.body() instanceof FormBody) {
                    HashMap<String, String> queryMap = new HashMap<>();
                    FormBody formBody = (FormBody) request.body();
                    FormBody.Builder bodyBuilder = new FormBody.Builder();
                    bodyBuilder.add(nonceKey, nonceValue);
                    bodyBuilder.add(timestampKey, timestampValue);
                    queryMap.put(nonceKey, nonceValue);
                    queryMap.put(timestampKey, timestampValue);
                    if (formBody != null) {
                        for (int i = 0; i < formBody.size(); i++) {
                            String key = formBody.name(i) + "";
                            String value = formBody.value(i) + "";
                            bodyBuilder.add(key, value);
                            queryMap.put(key, value);
                        }
                    }
                    bodyBuilder.add("sign", CommUtils.getSign(CommUtils.sortMapByKey(queryMap)));
                    builder.post(bodyBuilder.build());
                } else if (request.body() instanceof MultipartBody) {

                }
            }
            return chain.proceed(builder.build());
        }
    }

    public static MultipartBody.Part createFile(String key, File file) {
        if (file != null) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            return MultipartBody.Part.createFormData(key, file.getName(), requestBody);
        } else {
            return null;
        }
    }

    public static MultipartBody.Part createForm(String key, String value) {
        return MultipartBody.Part.createFormData(key, value);
    }
}
