package com.goyo.towermodule.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goyo.towermodule.util.Toaster;
import com.goyo.towermodule.widget.ThemeProgressDialog;

/**
 * Created by JarvisLau on 2018/5/7.
 * Description :
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private View rootView;
    private ThemeProgressDialog themeProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initView();
        return rootView;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void showToast(String msg) {
        Toaster.make(getContext(), msg).show();
    }

    @Override
    public void showProgress() {
        if (themeProgressDialog == null) {
            themeProgressDialog = new ThemeProgressDialog(getContext());
        }
        themeProgressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (themeProgressDialog != null) {
            themeProgressDialog.dismiss();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

}