package com.lf.util.system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationManagerCompat;

public class AppSettingUtil {

    /**

     * 手机品牌

     */

    // 小米
    public static final String PHONE_XIAOMI = "xiaomi";

    // 华为
    public static final String PHONE_HUAWEI1 = "Huawei";

    // 华为
    public static final String PHONE_HUAWEI2 = "HUAWEI";

    // 华为
    public static final String PHONE_HUAWEI3 = "HONOR";

    // 魅族
    public static final String PHONE_MEIZU = "Meizu";

    // 索尼
    public static final String PHONE_SONY = "sony";

    // 三星
    public static final String PHONE_SAMSUNG = "samsung";

    // LG
    public static final String PHONE_LG = "lg";

    // HTC
    public static final String PHONE_HTC = "htc";

    // NOVA
    public static final String PHONE_NOVA = "nova";

    // OPPO
    public static final String PHONE_OPPO = "OPPO";

    // 乐视
    public static final String PHONE_LeMobile = "LeMobile";

    // 联想
    public static final String PHONE_LENOVO = "lenovo";

    /**
     * @param context
     * 跳转到通知设置界面
     */
    public static void jumpPush(Context context) {
        try {

            String manufacturer = Build.MANUFACTURER;
            if (manufacturer.equalsIgnoreCase(PHONE_HUAWEI2) || manufacturer.equalsIgnoreCase(PHONE_HUAWEI3)) {

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                return;
            }

            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //直接跳转到应用通知设置的代码：
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localIntent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                localIntent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                context.startActivity(localIntent);
                return;
            }

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                localIntent.putExtra("app_package", context.getPackageName());
                localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
                context.startActivity(localIntent);
                return;
            }

            if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                localIntent.addCategory(Intent.CATEGORY_DEFAULT);
                localIntent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(localIntent);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();

            // 出现异常则跳转到应用设置界面
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            intent.setData(uri);
            context.startActivity(intent);
        }
    }

    /**
     * 跳转到应用详情页
     * @param context
     */
    public static void jumpAppInfoDetail(Context context) {

        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);


    }


    /**
     * 检测是否开启了通知
     * @param context
     * @return
     */
    private static boolean checkNotifySetting(Context context) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        boolean isOpened = manager.areNotificationsEnabled();

        return isOpened;
    }

}
