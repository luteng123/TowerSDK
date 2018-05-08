package com.goyo.towermodule.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.goyo.towermodule.R;
import com.goyo.towermodule.util.DimensionUtils;


/**
 * Created by JarvisLau on 2018/3/1.
 * Description :
 */

public class ThemeProgressDialog {

    private Context context;
    private ProgressDialog progressDialog;

    public ThemeProgressDialog(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        progressDialog = new ProgressDialog(context);
    }

    public void show() {
        progressDialog.show();
    }

    public void dismiss() {
        progressDialog.dismiss();
    }

    public ProgressDialog getDialog() {
        return progressDialog;
    }

    public class ProgressDialog extends Dialog {

        ProgressDialog(@NonNull Context context) {
            super(context);
            initView();
        }

        private void initView() {
            //隐藏背景和标题
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
            //设置view背景和圆角
            LinearLayout linearLayout = new LinearLayout(getContext());
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setColor(0xB1000000);
            drawable.setCornerRadius(10);
            linearLayout.setBackground(drawable);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DimensionUtils.dp2px(getContext(), 80)
                    , DimensionUtils.dp2px(getContext(), 80));
            params.gravity = Gravity.CENTER;
            linearLayout.setLayoutParams(params);

            SpinView spinView = new SpinView(getContext());
            linearLayout.addView(spinView);
            linearLayout.setGravity(Gravity.CENTER);

            setContentView(linearLayout);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) spinView.getLayoutParams();
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.setMargins(5, 5, 5, 5);
            layoutParams.height = DimensionUtils.dp2px(getContext(), 50);
            layoutParams.width = DimensionUtils.dp2px(getContext(), 50);
            spinView.setLayoutParams(layoutParams);


            //设置背景无遮罩
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.dimAmount = 0f;
            attributes.height = DimensionUtils.dp2px(getContext(), 70);
            attributes.width = DimensionUtils.dp2px(getContext(), 70);
            getWindow().setAttributes(attributes);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    class SpinView extends android.support.v7.widget.AppCompatImageView {

        private float mRotateDegrees;
        private int mFrameTime;
        private boolean mNeedToUpdateView;
        private Runnable mUpdateViewRunnable;

        public SpinView(Context context) {
            super(context);
            init();
        }

        public SpinView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            setImageResource(R.drawable.ic_progress_dialog_spin);
            mFrameTime = 1000 / 12;
            mUpdateViewRunnable = new Runnable() {
                @Override
                public void run() {
                    mRotateDegrees += 30;
                    mRotateDegrees = mRotateDegrees < 360 ? mRotateDegrees : mRotateDegrees - 360;
                    invalidate();
                    if (mNeedToUpdateView) {
                        postDelayed(this, mFrameTime);
                    }
                }
            };
        }

        public void setAnimationSpeed(float scale) {
            mFrameTime = (int) (1000 / 12 / scale);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                    getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.rotate(mRotateDegrees, getWidth() / 2, getHeight() / 2);
            super.onDraw(canvas);
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            mNeedToUpdateView = true;
            post(mUpdateViewRunnable);
        }

        @Override
        protected void onDetachedFromWindow() {
            mNeedToUpdateView = false;
            super.onDetachedFromWindow();
        }
    }
}
