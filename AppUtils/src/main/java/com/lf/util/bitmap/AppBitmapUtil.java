package com.lf.util.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaMetadataRetriever;
import android.view.View;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



/**
 * @date: 2023/12/21
 */
public class AppBitmapUtil {

    /**
     * 文件是否是是视频
     * @param filePath
     * @return
     */
    public static boolean isVideoFile(String filePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            String mimeType = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            return mimeType != null && mimeType.startsWith("video/");
        } catch (Exception e) {

        } finally {
        }

        return false;
    }


    /**
     * 判断一个文件是否是图片
     * @param filePath
     * @return
     */
    public static boolean isImageFile(String filePath) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(filePath);
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
        return mimeType != null && mimeType.startsWith("image/");
    }

    /**
     * 获取视频第一贞
     * @param filePath
     * @return
     */
    public static Bitmap getFirstFrame(String filePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        Bitmap firstFrame = null;
        try {
            retriever.setDataSource(filePath);
            firstFrame = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        } catch (Exception e) {

        } finally {
        }

        return firstFrame;
    }

    /**
     * 获取第一贞 图片地址
     * @param filePath
     * @return
     */
    public static String getFirstFramePath(Context context, String filePath) {
        Bitmap bitmap = getFirstFrame(filePath);

        String scalePath = context.getExternalFilesDir("scaleImage").getPath() + "/" + System.currentTimeMillis();

        if (bitmap != null) {
            saveBitmap(bitmap, scalePath);
        }
        return scalePath;
    }


    /**
     * 获取缩略图
     * @param context
     * @param imagePath
     * @return
     */
    public static String getScaleImage(Context context, String imagePath) {

        FileInputStream fis = null;
        Bitmap bitmap = null;
        try {
            fis = new FileInputStream(imagePath);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (Exception e) {

        }

        if (bitmap == null) {
            return "";
        }


        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        int length = 200;

        if (imageWidth > length || imageHeight > length) {
            int scaleFactor = Math.max(imageWidth / length, imageHeight / length);

            int scaleWidth = Math.round(imageWidth / scaleFactor);
            int scaleHeight = Math.round(imageHeight / scaleFactor);

            String scalePath = context.getExternalFilesDir("scaleImage").getPath() + "/" + System.currentTimeMillis();
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaleWidth, scaleHeight, true);
            saveBitmap(scaledBitmap, scalePath);

            return scalePath;

        }

        return imagePath;
    }


    /**
     * 保存图片到本地
     *
     * @param bm
     * @param filePath
     * @return
     */
    public static boolean saveBitmap(Bitmap bm, String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 把View画成Bitmap
     *
     * @param view 要处理的View
     * @return Bitmap
     */
    public static Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}
