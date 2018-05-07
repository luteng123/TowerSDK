package com.goyo.towermodule.net;

import com.goyo.towermodule.bean.TowerDetailBean;
import com.goyo.towermodule.entitiy.Test;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by JarvisLau on 2018/5/7.
 * Description :
 */

public interface RetrofitParams {
    @FormUrlEncoded
    @POST("ew/test")
    Call<Test> test();

    @FormUrlEncoded
    @POST("tc/queryList")
    Call<TowerDetailBean> requestTowerDetail(@Field("proId")String proId, @Field("craneNo")String craneNo);
}