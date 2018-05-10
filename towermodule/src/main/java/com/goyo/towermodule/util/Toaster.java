package com.goyo.towermodule.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by JarvisLau on 2018/5/8.
 * Description :
 */

public class Toaster {

    private static Toaster toaster;
    private static Toast toast;

    private Toaster() {

    }

    @SuppressLint("ShowToast")
    public static Toaster make(Context context, String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        return toaster == null ? toaster = new Toaster() : toaster;
    }

    public void show() {
        if (toast != null) {
            toast.show();
        }
    }

}