package com.goyo.towermodule.listener;

import android.view.View;

import com.goyo.towermodule.entitiy.TowerImageEntity;

/**
 * Created by JarvisLau on 2018/5/10.
 * Description :
 */

public interface OnTowerCraneClickListener {
    void onClick(View v, TowerImageEntity.DataBean.AddressListBean bean);
}
