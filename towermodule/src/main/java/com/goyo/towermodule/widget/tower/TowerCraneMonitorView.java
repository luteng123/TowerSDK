package com.goyo.towermodule.widget.tower;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.goyo.towermodule.entitiy.TowerImageEntity;
import com.goyo.towermodule.listener.OnTowerCraneClickListener;
import com.goyo.towermodule.util.DimensionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public class TowerCraneMonitorView extends ViewGroup {

    private float webWidth = 984f;
    private float webHeight = 720f;
    private float windowsWidth;
    private Bitmap mBackgroundBitmap;
    private HashMap<Integer, TowerImageEntity.DataBean.AddressListBean> mapIdDevice;
    private HashMap<Integer, TowerCraneEquipmentView> mapIdViews;
    private OnTowerCraneClickListener onTowerCraneClickListener;

    public TowerCraneMonitorView(Context context) {
        this(context, null);
    }

    public TowerCraneMonitorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TowerCraneMonitorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mBackgroundBitmap != null) {
            setMeasuredDimension(mBackgroundBitmap.getWidth(), mBackgroundBitmap.getHeight());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void initData() {
        windowsWidth = DimensionUtils.getWindowsWidth(getContext());
        mapIdDevice = new HashMap<>();
        mapIdViews = new HashMap<>();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void setEquipment(TowerImageEntity.DataBean.PhototPathBean photoPathBean, final List<TowerImageEntity.DataBean.AddressListBean> equipments) {
        Collections.sort(equipments, new Comparator<TowerImageEntity.DataBean.AddressListBean>() {
            @Override
            public int compare(TowerImageEntity.DataBean.AddressListBean o1, TowerImageEntity.DataBean.AddressListBean o2) {
                return (int) Math.round(o2.getArmFor() - o1.getArmFor());
            }
        });
        for (final TowerImageEntity.DataBean.AddressListBean device : equipments) {
            if (!mapIdDevice.containsKey(device.getId())) {
                mapIdDevice.put(device.getId(), device);
                TowerCraneEquipmentView towerCraneEquipmentView = addEquipment(photoPathBean, device);
                mapIdViews.put(device.getId(), towerCraneEquipmentView);
                rotation(towerCraneEquipmentView, 0, (float) device.getAngle());
                towerCraneEquipmentView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onTowerCraneClickListener.onClick(v, device);
                    }
                });
            } else {
                TowerImageEntity.DataBean.AddressListBean equipment = mapIdDevice.get(device.getId());
                if (equipment.getX() != device.getX() || equipment.getY() != device.getY()) {
                    removeView(mapIdViews.get(device.getId()));
                    addEquipment(photoPathBean, equipment);
                } else if (equipment.getAngle() != device.getAngle()) {
                    TowerCraneEquipmentView towerCraneEquipmentView = mapIdViews.get(equipment.getId());
                    rotation(towerCraneEquipmentView, (float) equipment.getAngle(), (float) device.getAngle());
                    mapIdDevice.put(device.getId(), device);
                }
            }
        }
    }

    private void rotation(View view, float startAngle, float endAngle) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", (float) startAngle - 90, (float) endAngle - 90);
        objectAnimator.reverse();
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private TowerCraneEquipmentView addEquipment(TowerImageEntity.DataBean.PhototPathBean photoPathBean, TowerImageEntity.DataBean.AddressListBean equipment) {
        TowerCraneEquipmentView towerCraneEquipmentView = new TowerCraneEquipmentView(getContext(), (int) equipment.getArmFor(), equipment.getOnlineTime().equals("在线"));
        addView(towerCraneEquipmentView);
        double resultRadius = equipment.getArmFor();
        double realRadius = (resultRadius * (windowsWidth / Float.parseFloat(photoPathBean.getLength())));
        int radius = (int) Math.round(realRadius);
        double x = mBackgroundBitmap.getWidth() * (equipment.getX() / webWidth);
        int realX = (int) Math.round(x);
        double y = mBackgroundBitmap.getHeight() * (equipment.getY() / webHeight);
        int realY = (int) Math.round(y);
        int left = realX - radius / 2;
        int top = realY - radius / 2;
        int right = realX + radius / 2;
        int bottom = realY + radius / 2;
        towerCraneEquipmentView.layout(left, top, right, bottom);
        return towerCraneEquipmentView;
    }

    @Override
    public void setBackground(Drawable background) {
        BitmapDrawable backgroundDrawable = (BitmapDrawable) background;
        Bitmap bitmap = backgroundDrawable.getBitmap();
        float scale = windowsWidth / bitmap.getWidth();
        mBackgroundBitmap = scaleBitmap(bitmap, scale, false);
        setMeasuredDimension(mBackgroundBitmap.getWidth(), mBackgroundBitmap.getHeight());
        super.setBackground(new BitmapDrawable(getContext().getResources(), mBackgroundBitmap));
    }

    public Bitmap scaleBitmap(Bitmap bitmap, float scaleSize, boolean centerScale) {
        Matrix matrix = new Matrix();
        if (centerScale) {
            matrix.postScale(scaleSize, scaleSize, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        } else {
            matrix.postScale(scaleSize, scaleSize, 0, 0);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void setOnTowerCraneClickListener(OnTowerCraneClickListener onTowerCraneClickListener) {
        this.onTowerCraneClickListener = onTowerCraneClickListener;
    }

    public int getViewHeight() {
        return mBackgroundBitmap.getHeight();
    }

    public int getViewWidth() {
        return mBackgroundBitmap.getWidth();
    }
}