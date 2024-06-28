package com.lf.util;

import android.content.Context;
import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Set;

public class AppSharePreferenceUtil {

    private static MMKV mmkv;

    private static final String SHARE_ID = "zyb_sp";

    public static void init(Context context) {

        MMKV.initialize(context);

        mmkv = MMKV.mmkvWithID(SHARE_ID);
    }

    public static void saveString(String shareId, String key, String value) {
        MMKV mmkv = MMKV.mmkvWithID(shareId);
        mmkv.encode(key, value);
    }

    public static String getString(String shareId, String key) {
        MMKV mmkv = MMKV.mmkvWithID(shareId);
        return mmkv.decodeString(key);
    }

    public static void saveInt(String key, int value) {
        mmkv.encode(key, value);
    }

    public static void saveBoolean(String key, Boolean value) {
        mmkv.encode(key, value);
    }

    public static void saveFloat(String key, float value) {
        mmkv.encode(key, value);
    }

    public static void saveLong(String key, long value) {
        mmkv.encode(key, value);
    }

    public static void saveString(String key, String value) {
        mmkv.encode(key, value);
    }

    public static void saveDouble(String key, double value) {
        mmkv.encode(key, value);
    }

    public static void saveBytes(String key, byte[] value) {
        mmkv.encode(key, value);
    }

    public static void saveSet(String key, Set<String> defaultValue) {
        mmkv.encode(key, defaultValue);
    }

    public static void encodeParce(String key, Parcelable value) {
        mmkv.encode(key, value);
    }

    public static int getInt(String key) {
        return mmkv.decodeInt(key);
    }

    public static int getDefaultInt(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return mmkv.decodeBool(key);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    public static float getFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    public static long getLong(String key) {
        return mmkv.decodeLong(key);
    }

    public static String getString(String key) {
        return mmkv.decodeString(key);
    }

    public static String getStringDefault(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    public static double getDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    public static byte[] getByte(String key) {
        return mmkv.decodeBytes(key);
    }

    public static Set<String> getSet(String key) {
        return mmkv.decodeStringSet(key);
    }

    public static <T extends Parcelable> T getParce(String key, Class<T> tClass) {
        return mmkv.decodeParcelable(key, tClass);
    }

    public static void clean() {
        mmkv.clear();
    }

    public static void remove(String key) {
        mmkv.remove(key);
    }

    public static void removeValues(String[] keys) {
        mmkv.removeValuesForKeys(keys);
    }
}
