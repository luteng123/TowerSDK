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
        toaster = new Toaster();
    }

    @SuppressLint("ShowToast")
    public static Toaster make(Context context, String msg) {
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        return toaster;
    }

    public void show() {
        if(toast!=null) {
            toast.cancel();
            toast.show();
        }
    }

}