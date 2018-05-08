package com.goyo.towermodule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.goyo.towermodule.base.BaseFragment;
import com.goyo.towermodule.base.BaseRetrofitFragment;
import com.goyo.towermodule.entitiy.BaseEntity;
import com.goyo.towermodule.entitiy.TowerImageEntity;
import com.goyo.towermodule.net.HttpCallback;
import com.goyo.towermodule.net.RetrofitUtils;
import com.goyo.towermodule.util.CommUtils;
import com.goyo.towermodule.util.LogUtil;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by JarvisLau on 2018/5/7.
 * Description :
 */
public class TowerFragment extends BaseRetrofitFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tower_ji;
    }

    @Override
    protected void initView() {
        getTowerImage();
    }

    private void getTowerImage() {

        RetrofitUtils.getInstance().getTowerImage("6260").enqueue(new CallBack<TowerImageEntity>() {
            @Override
            public void onSuccess(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response, @NonNull TowerImageEntity data, @NonNull String json) {

            }
        });
        
    }
}