package com.goyo.towermodule.net;

import com.google.gson.JsonSyntaxException;
import com.goyo.towermodule.util.LogUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.HttpException;

/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public interface INetworkErrorListener {

    void onServerError(Call<ResponseBody> call, Throwable t);

    void onResponseTimeOut(Call<ResponseBody> call, Throwable t);

    void onRequestTimeOut(Call<ResponseBody> call, Throwable t);

    void onHostError(Call<ResponseBody> call, Throwable t);

    void onUnknownError(Call<ResponseBody> call, Throwable t);

    void onBusinessError(Call<ResponseBody> call, Throwable t);

}