package com.goyo.towermodule.widget.scale;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.goyo.towermodule.widget.scale.ScaleGestureListener;
import com.goyo.towermodule.widget.scale.ScrollGestureListener;

/**
 * Created by JarvisLau on 2018/4/19.
 * Description :
 */

public class PhotoScaleGestureDetector extends ScaleGestureDetector {

    private ScaleGestureListener listener;

    public PhotoScaleGestureDetector(Context context, ScaleGestureListener listener) {
        super(context, listener);
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                listener.onSingleUp();
                break;
        }
        return super.onTouchEvent(event);
    }

}