package com.goyo.towermodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.goyo.towermodule.base.BaseFragment;

/**
 * Created by JarvisLau on 2018/5/7.
 * Description :
 */
public class TowerFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getRetrofit().test().enqueue(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}
