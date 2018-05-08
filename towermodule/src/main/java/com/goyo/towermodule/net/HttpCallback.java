package com.goyo.towermodule.net;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;


/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public abstract class HttpCallback<T> implements Callback<ResponseBody> {

    private INetworkErrorListener errorListener;

    public HttpCallback(INetworkErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response == null || response.body() == null) {
            errorListener.onBusinessError(call, new BusinessThrowable(-1, "response body is null"));
            return;
        }
        try {
            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                errorListener.onBusinessError(call, new BusinessThrowable(-1, errorBody.string()));
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ResponseBody body = response.body();
            if (body != null) {
                String json = body.string();
                onJson(json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    int code = jsonObject.getInt("code");
                    String msg = jsonObject.getString("msg");
                    if (code == 1) {
                        ParameterizedType genType = (ParameterizedType) getClass().getGenericSuperclass();
                        Type[] actualTypeArguments = ((ParameterizedType) genType).getActualTypeArguments();
                        Type type = actualTypeArguments[0];
                        T t = new GsonBuilder().create().fromJson(json, type);
                        onSuccess(call, response, t, json);
                    } else {
                        errorListener.onBusinessError(call, new BusinessThrowable(code, msg));
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    errorListener.onBusinessError(call, new BusinessThrowable(-1, "json syntax exception"));
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                    errorListener.onBusinessError(call, new BusinessThrowable(-1, "json syntax exception"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        if (t instanceof HttpException) {
            errorListener.onServerError(call, t);
        } else if (t instanceof SocketTimeoutException) {
            errorListener.onResponseTimeOut(call, t);
        } else if (t instanceof ConnectException) {
            errorListener.onRequestTimeOut(call, t);
        } else if (t instanceof UnknownHostException) {
            errorListener.onHostError(call, t);
        } else {
            errorListener.onUnknownError(call, t);
        }
    }

    public abstract void onSuccess(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response, @NonNull T data, @NonNull String json);

    public void onJson(String json) {

    }

}