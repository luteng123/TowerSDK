package com.goyo.towermodule.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        initView();
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

    protected abstract void initView();

}