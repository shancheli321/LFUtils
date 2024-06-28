package com.lf.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @date: 2023/3/10
 */
public class AppResourcesUtil {

    /**
     * 将本地图片转换为 Drawable
     * @param context 上下文
     * @param file 文件路径
     * @return
     */
    public static Drawable path2Drawable(Context context, String file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        Drawable drawable = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            drawable = new BitmapDrawable(context.getResources(), bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return drawable;
    }

    /**
     * 把资源图片转换成Bitmap
     * @param drawable
     * 资源图片
     * @return 位图
     */
    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
                .getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //drawable.setBounds(-4, -4, width + 4, height + 4);
        drawable.draw(canvas);
        return bitmap;
    }
}
