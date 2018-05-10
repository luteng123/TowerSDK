package com.goyo.towermodule.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goyo.towermodule.R;
import com.goyo.towermodule.entitiy.TowerImageEntity;

import java.util.List;

/**
 * Created by JarvisLau on 2018/5/10.
 * Description :
 */

public class TowerCraneAdapter extends BaseQuickAdapter<TowerImageEntity.DataBean.ListTodayBean, BaseViewHolder> {

    private Context context;
    private Bitmap online, offline;


    public TowerCraneAdapter(Context context, @Nullable List<TowerImageEntity.DataBean.ListTodayBean> data) {
        super(R.layout.item_tower_crane, data);
        this.context = context;
        online = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_online);
        offline = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_offline);
    }

    @Override
    protected void convert(BaseViewHolder helper, TowerImageEntity.DataBean.ListTodayBean item) {
        TextView tvTowerCraneName = helper.getView(R.id.tvTowerCraneName);
        TextView tvTowerCraneNo = helper.getView(R.id.tvTowerCraneNo);
        TextView tvTowerCraneWorkCount = helper.getView(R.id.tvTowerCraneWorkCount);
        TextView tvTowerCraneOperator = helper.getView(R.id.tvTowerCraneOperator);
        TextView tvTowerCraneWarnCount = helper.getView(R.id.tvTowerCraneWarnCount);
        ImageView ivTowerCraneStatus = helper.getView(R.id.ivTowerCraneStatus);
        String name = TextUtils.isEmpty(item.getName()) ? "--" : item.getName();
        String no = TextUtils.isEmpty(item.getCraneNoName()) ? "--" : item.getCraneNoName();
        String workCount = String.valueOf(item.getCountHistory());
        String driver = TextUtils.isEmpty(item.getConName()) ? "--" : item.getConName();
        String warnCount = String.valueOf(item.getCountArm());
        tvTowerCraneName.setText(name);
        tvTowerCraneNo.setText(String.format(context.getString(R.string.unit_no), no));
        tvTowerCraneWorkCount.setText(String.format(context.getString(R.string.unit_work_count), workCount));
        tvTowerCraneOperator.setText(String.format(context.getString(R.string.unit_driver), driver));
        tvTowerCraneWarnCount.setText(String.format(context.getString(R.string.unit_warn_count), warnCount));
        ivTowerCraneStatus.setImageBitmap(item.getOnlineTime().equals("在线") ? online : offline);
    }

}