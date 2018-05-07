package com.goyo.towermodule.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Author: wangshuang
 * Time: 2017/8/8
 * Email:xiaoshuang990@sina.com
 */

public class CommUtils {


    public static int getNonce() {
        return (int) ((Math.random() * 9 + 1) * 10000);
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    private static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & aByteArray));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
        }
        return md5StrBuff.toString().toLowerCase();
    }


    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }

    public static String getSign(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        String sign;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append(entry.getValue());
        }
        String ss = stringBuilder.toString();
        sign = CommUtils.getMD5(ss);
        return sign;
    }
}
