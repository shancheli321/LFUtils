package com.lf.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppForeBackgroundUtil {

    private volatile static AppForeBackgroundUtil util;

    // 是否初始化了
    private boolean isInited;

    // 是否在后台
    private boolean isBackground;

    // 监听列表
    List<CallBack> mCallBackList;

    // 活跃的activity数量
    private int mActiveActivityCount = 0;

    public static AppForeBackgroundUtil getInstance() {
        if (util == null) {
            synchronized (AppForeBackgroundUtil.class) {
                if (util == null) {
                    util = new AppForeBackgroundUtil();
                }
            }
        }
        return util;
    }


    public void init(Application application) {
        if (isInited) {
            return;
        }

        mCallBackList = new CopyOnWriteArrayList<>();
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        isInited = true;
    }

    /**
     * 是否在前台
     * @return
     */
    public static boolean isAppForeground() {
        return getInstance().isBackground;
    }


    /**
     * 是否在后台
     * @return
     */
    public static boolean isAppBackground() {
        return getInstance().isBackground;
    }


    /**
     * 注册监听
     * @param callBack
     */
    public void register(CallBack callBack) {
        if (mCallBackList.contains(callBack)) {
            return;
        }
        mCallBackList.add(callBack);
    }


    // 生命周期
    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            if (mActiveActivityCount == 0) {
                isBackground = false;
                for (CallBack callBack : mCallBackList) {
                    callBack.onAppForeground(activity);
                }
            }
            mActiveActivityCount++;
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            mActiveActivityCount--;
            if (mActiveActivityCount == 0) {
                isBackground = true;
                for (CallBack callBack : mCallBackList) {
                    callBack.onAppBackground(activity);
                }
            }
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    };

    public interface CallBack {

        void onAppForeground(Activity activity);

        void onAppBackground(Activity activity);
    }

}
