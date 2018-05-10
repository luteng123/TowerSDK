package com.goyo.towermodule.widget.scale;

import android.view.ScaleGestureDetector;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

    private View targetView;

    private float scale;
    private float preScale = 1;// 默认前一次缩放比例为1

    private boolean singleUp = false;

    private ScrollGestureListener scrollGestureListener;

    public ScaleGestureListener(View targetView, ScrollGestureListener scrollGestureListener) {
        this.targetView = targetView;
        this.scrollGestureListener = scrollGestureListener;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {

        scale = detector.getScaleFactor();
        scale = preScale * scale;

        // 缩放view
        ViewHelper.setScaleX(targetView, scale);// x方向上缩小
        ViewHelper.setScaleY(targetView, scale);// y方向上缩小

        scrollGestureListener.setScale(scale);

        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        // 一定要返回true才会进入onScale()这个函数
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
    }

    public void onSingleUp() {
        preScale = scale;//记录本次缩放比例
        if (preScale < 1) {
            preScale = 1;
            ViewHelper.setScaleX(targetView, 1);// x方向上缩小
            ViewHelper.setScaleY(targetView, 1);// y方向上缩小
            ViewHelper.setTranslationX(targetView, 0);
            ViewHelper.setTranslationY(targetView, 0);
        }
        scrollGestureListener.setScale(preScale);
    }
}