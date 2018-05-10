package com.goyo.towermodule.widget.scale;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JarvisLau on 2018/4/19.
 * Description :
 */

public class ScrollGestureListener extends GestureDetector.SimpleOnGestureListener {

    private View targetView;
    private float scale = 1;
    private float distanceXCache = 0;
    private float distanceYCache = 0;
    private float centerX;
    private float centerY;

    private float viewWidth;
    private float viewHeight;
    private float maxTranslationDistanceX = 0;
    private float maxTranslationDistanceY = 0;

    private int scaleType = -1;
    private final static int SCALE_TYPE_LARGER = 1;
    private final static int SCALE_TYPE_SMALLER = 2;

    public ScrollGestureListener(View targetView) {
        this.targetView = targetView;
        viewWidth = targetView.getWidth();
        viewHeight = targetView.getHeight();
        centerX = targetView.getX() / 2;
        centerY = targetView.getY() / 2;
    }

    /**
     * 手指滑动回调
     *
     * @param e1        滑动前event
     * @param e2        滑动时event
     * @param distanceX x移动距离，不知道为什么和实际是相反的，右滑为负数，左滑为正数
     * @param distanceY y移动距离，不知道为什么和实际是相反的，下滑为负数，上滑为正数
     * @return 是否消费
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float distanceXValue = Math.abs(distanceXCache + distanceX);
        if (distanceXValue < maxTranslationDistanceX) {
            distanceXCache += distanceX;
            targetView.setTranslationX(-distanceXCache);
        }
        float distanceYValue = Math.abs(distanceYCache + distanceY);
        if (distanceYValue < maxTranslationDistanceY) {
            distanceYCache += distanceY;
            targetView.setTranslationY(-distanceYCache);
        }
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return super.onSingleTapUp(e);
    }

    public void setScale(float scale) {
        if (this.scale > scale) {
            scaleType = SCALE_TYPE_SMALLER;
        } else if (this.scale < scale) {
            scaleType = SCALE_TYPE_LARGER;
        } else {
            scaleType = -1;
        }
        this.scale = scale;
        viewWidth = targetView.getWidth() * scale;
        viewHeight = targetView.getHeight() * scale;
        centerX = targetView.getWidth() / 2 - distanceXCache;
        centerY = targetView.getHeight() / 2 - distanceYCache;
        if (scale == 1) {
            distanceXCache = 1;
            distanceYCache = 1;
            maxTranslationDistanceX = 0;
            maxTranslationDistanceY = 0;
        } else {
            maxTranslationDistanceX = (viewWidth - targetView.getWidth()) / 2;
            maxTranslationDistanceY = (viewHeight - targetView.getHeight()) / 2;
        }
        if (scaleType == SCALE_TYPE_SMALLER && scale > 1) {
            if (centerX - viewWidth / 2 > 0) {
                distanceXCache = distanceXCache + (centerX - viewWidth / 2);
                targetView.setTranslationX(-distanceXCache);
            }
            if (targetView.getWidth() - (viewWidth / 2 + centerX) > 0) {
                distanceXCache = distanceXCache - (targetView.getWidth() - (viewWidth / 2 + centerX));
                targetView.setTranslationX(-distanceXCache);
            }
            if (centerY - viewHeight / 2 > 0) {
                distanceYCache = distanceYCache + (centerY - viewHeight / 2);
                targetView.setTranslationY(-distanceYCache);
            }
            if (targetView.getHeight() - (viewHeight / 2 + centerY) > 0) {
                distanceYCache = distanceYCache - (targetView.getHeight() - (viewHeight / 2 + centerY));
                targetView.setTranslationY(-distanceYCache);
            }
        }
    }
}