package com.goyo.towermodule.widget.scale;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JarvisLau on 2018/5/9.
 * Description :
 */

public class PhotoGesture {

    private GestureDetector gestureDetector;
    private PhotoScaleGestureDetector scaleGestureListener;

    public PhotoGesture(Context context, View targetView) {
        ScrollGestureListener scrollGestureListener = new ScrollGestureListener(targetView);
        gestureDetector = new GestureDetector(context, scrollGestureListener);
        scaleGestureListener = new PhotoScaleGestureDetector(context, new ScaleGestureListener(targetView, scrollGestureListener));
    }

    public boolean onTouch(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return scaleGestureListener.onTouchEvent(event);
    }

}