package com.goyo.towermodule.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goyo.towermodule.base.BaseView;
import com.goyo.towermodule.net.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by JarvisLau on 2018/5/7.
 * Description :
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    protected abstract int getLayoutId();

}