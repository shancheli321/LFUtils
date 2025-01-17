package com.lf.util.audio;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理录音文件的类
 *
 * @author chenmy0709
 * @version V001R001C01B001
 */
public class AudioFileUtil {

    private static String rootPath = "audio";

    //原始文件(不能播放)
    private final static String AUDIO_PCM_BASEPATH = "/" + rootPath + "/pcm/";

    //可播放的高质量音频文件
    private final static String AUDIO_WAV_BASEPATH = "/" + rootPath + "/wav/";

    public static String getPcmFileAbsolutePath(Context context, String fileName) {

        if (TextUtils.isEmpty(fileName)) {
            throw new NullPointerException("fileName isEmpty");
        }
        if (!isSdcardExit()) {
            throw new IllegalStateException("sd card no found");
        }

        String mAudioRawPath = "";

        if (isSdcardExit()) {
            if (!fileName.endsWith(".pcm")) {
                fileName = fileName + ".pcm";
            }

            String fileBasePath = context.getExternalFilesDir(null).getPath() + AUDIO_PCM_BASEPATH;
            File file = new File(fileBasePath);

            //创建目录
            if (!file.exists()) {
                file.mkdirs();
            }

            mAudioRawPath = fileBasePath + fileName;
        }

        return mAudioRawPath;
    }

    public static String getWavFileAbsolutePath(Context context, String fileName) {
        if (fileName == null) {
            throw new NullPointerException("fileName can't be null");
        }
        if (!isSdcardExit()) {
            throw new IllegalStateException("sd card no found");
        }

        String mAudioWavPath = "";
        if (isSdcardExit()) {
            if (!fileName.endsWith(".wav")) {
                fileName = fileName + ".wav";
            }

            String fileBasePath = context.getExternalFilesDir(null).getPath() + AUDIO_WAV_BASEPATH;
            File file = new File(fileBasePath);

            //创建目录
            if (!file.exists()) {
                file.mkdirs();
            }

            mAudioWavPath = fileBasePath + fileName;
        }
        return mAudioWavPath;
    }

    /**
     * 判断是否有外部存储设备sdcard
     *
     * @return true | false
     */
    public static boolean isSdcardExit() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 获取全部pcm文件列表
     *
     * @return
     */
    public static List<File> getPcmFiles(Context context) {
        List<File> list = new ArrayList<>();
        String fileBasePath = context.getExternalFilesDir(null).getPath() + AUDIO_PCM_BASEPATH;

        File rootFile = new File(fileBasePath);
        if (!rootFile.exists()) {
        } else {

            File[] files = rootFile.listFiles();
            for (File file : files) {
                list.add(file);
            }

        }
        return list;

    }


    /**
     * 获取pcm文件
     * @return
     */
    public static String getPcmFilePath(Context context) {
        List<File> list = new ArrayList<>();
        String fileBasePath = context.getExternalFilesDir(null).getPath() + AUDIO_PCM_BASEPATH;

        File rootFile = new File(fileBasePath);
        if (!rootFile.exists()) {

            return "";

        } else {

            File[] files = rootFile.listFiles();

            File firstFile = files[0];

            return firstFile.getPath();
        }
    }



    /**
     * 获取全部wav文件列表
     *
     * @return
     */
    public static List<File> getWavFiles(Context context) {
        List<File> list = new ArrayList<>();
        String fileBasePath = context.getExternalFilesDir(null).getPath() + AUDIO_WAV_BASEPATH;

        File rootFile = new File(fileBasePath);
        if (!rootFile.exists()) {
        } else {
            File[] files = rootFile.listFiles();
            for (File file : files) {
                list.add(file);
            }

        }
        return list;
    }
}
