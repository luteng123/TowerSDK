package com.goyo.towermodule.base;

import android.support.annotation.NonNull;

import com.goyo.towermodule.net.HttpCallback;
import com.goyo.towermodule.net.INetworkErrorListener;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public abstract class BaseRetrofitFragment extends BaseFragment implements INetworkErrorListener {

    @Override
    public void onServerError(Call<ResponseBody> call, Throwable t) {
        dismissProgress();
        showToast("服务器发生错误!");
    }

    @Override
    public void onResponseTimeOut(Call<ResponseBody> call, Throwable t) {
        dismissProgress();
        showToast("网络错误，请检查网络设置!");
    }

    @Override
    public void onRequestTimeOut(Call<ResponseBody> call, Throwable t) {
        dismissProgress();
        showToast("网络错误，请检查网络设置!");
    }

    @Override
    public void onHostError(Call<ResponseBody> call, Throwable t) {
        dismissProgress();
        showToast("网络错误，请检查网络设置!");
    }

    @Override
    public void onUnknownError(Call<ResponseBody> call, Throwable t) {
        dismissProgress();
        showToast("网络错误!错误信息:" + t.getMessage());
    }

    @Override
    public void onBusinessError(Call<ResponseBody> call, Throwable t) {
        dismissProgress();
        showToast(t.getMessage());
    }

    public abstract class CallBack<T> extends HttpCallback<T> {

        protected CallBack() {
            super(BaseRetrofitFragment.this);
        }

    }
}