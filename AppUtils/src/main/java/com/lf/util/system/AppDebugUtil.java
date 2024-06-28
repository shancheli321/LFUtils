package com.lf.util.system;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

/**
 * @date: 2023/3/9
 */
public class AppDebugUtil {

    // 打印app和手机内存占用情况
    public static void getAppMemory(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

        // 最大分配内存
        int memory = activityManager.getMemoryClass();

        Log.d("", "application------appmemory: " + memory);

        // 系统可为APP分配的最大内存
        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0/ (1024 * 1024));

        // APP当前所分配的内存heap空间大小
        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0/ (1024 * 1024));

        //剩余内存
        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0/ (1024 * 1024));

        Log.d("", "application------maxMemory: " + maxMemory + "M");
        Log.d("", "application------totalMemory: " + totalMemory + "M");
        Log.d("", "application------freeMemory: " + freeMemory + "M");

    }
}
