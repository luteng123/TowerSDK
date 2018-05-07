package com.goyo.towermodule.util;

import android.util.Log;

public class LogUtil {
	private static boolean flag = false;
    private static String sTag = "TTT";

    /*public static void i(String str) {
		if (flag) {
			try {
				Log.i("FactoryInfo", str);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}*/

	public static void i(String tag, String str) {
		if (flag) {
			try {
				Log.i(tag, str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void setFlag(boolean flag) {
		LogUtil.flag = flag;
	}

    public static void i( Object msg){
        if(msg == null){
            msg = "";
        }
        String string = msg.toString();
        if(string.length() > 3800){
            for (int i = 0; i < string.length(); i += 3800) {
                if(i+3800<string.length())
                    Log.i(sTag,string.substring(i, i+3800));
                else
                    Log.i(sTag,string.substring(i, string.length()));
            }
        }else {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.i(sTag, "(" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")"+msg.toString());
        }
    }

    private static StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtil.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

}
