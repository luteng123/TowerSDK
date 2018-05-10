package com.goyo.towermodule.widget.tower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.goyo.towermodule.R;

/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public class TowerCraneEquipmentView extends android.support.v7.widget.AppCompatImageView {

    private Bitmap backgroundOnline;
    private Bitmap backgroundOffline;

    private int radius;
    private boolean isOnline;
    private Bitmap background;
    private Bitmap backgroundPress;

    private boolean selected;

    private BitmapDrawable mViewDrawable, mViewPressedDrawable;

    public TowerCraneEquipmentView(Context context, int radius, boolean isOnline) {
        this(context, null, radius, isOnline);
    }

    private TowerCraneEquipmentView(Context context, @Nullable AttributeSet attrs, int radius, boolean isOnline) {
        this(context, attrs, 0, radius, isOnline);
    }

    private TowerCraneEquipmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int radius, boolean isOnline) {
        super(context, attrs, defStyleAttr);
        this.radius = radius;
        this.isOnline = isOnline;
        initData();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(300, 300);
    }

    private void initData() {
        initViewDrawable();
        setRotation(-90);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        this.selected = selected;
        setBackgroundDrawable(selected ? mViewPressedDrawable : mViewDrawable);
    }

    public void initViewDrawable() {
        /*
         * 初始化选中和未选中的画布
         */
        Bitmap container = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Bitmap containerPress = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(container);
        Canvas canvasPress = new Canvas(containerPress);

        Paint paint = new Paint();

        if (isOnline) {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.bg_tower_crane_online);
        } else {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.bg_tower_crane_offline);
        }
        backgroundPress = BitmapFactory.decodeResource(getResources(), R.drawable.bg_tower_crane_selected);

        /*
         * 绘制未选中状态图片
         */
        canvas.rotate(-90, canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.drawBitmap(background
                , new Rect(0, 0, background.getWidth(), background.getHeight())
                , new Rect(0, 0, radius, radius)
                , paint);
        //canvas.drawBitmap(mIcon, width / 2 - mIcon.getWidth() / 2, height / 2 - mIcon.getHeight() / 2, paint);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        /*
         * 绘制选中状态图片
         */
        canvas.rotate(-90, canvasPress.getWidth() / 2, canvasPress.getHeight() / 2);
        canvasPress.drawBitmap(backgroundPress
                , new Rect(0, 0, backgroundPress.getWidth(), backgroundPress.getHeight())
                , new Rect(0, 0, radius, radius)
                , paint);
        //canvasPress.drawBitmap(mIcon, width / 2 - mIcon.getWidth() / 2, height / 2 - mIcon.getHeight() / 2, paint);
        canvasPress.save(Canvas.ALL_SAVE_FLAG);
        canvasPress.restore();

        /*
         * 初始化selector图片
         */
        mViewDrawable = new BitmapDrawable(getResources(), container);
        mViewPressedDrawable = new BitmapDrawable(getResources(), containerPress);

        setBackgroundDrawable(new BitmapDrawable(background));
    }

}