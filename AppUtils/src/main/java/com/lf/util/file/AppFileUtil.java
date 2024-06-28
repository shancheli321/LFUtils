package com.lf.util.file;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AppFileUtil {



    /**
     * 创建文件夹
     *
     * @param DirPath 文件夹路径
     */
    public static void makedir(String DirPath) {
        File file = new File(DirPath);
        if (!(file.exists() && file.isDirectory())) {
            file.mkdirs();
        }
    }


    //****删除文件**********************************************************************************************

    /**
     * 根据给出路径自动选择删除文件或整个文件夹
     * @param file :文件或文件夹路径
     * */
    public static void deleteFile(File file) {

        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
        file.delete();
    }

    /**
     * 递归删除文件或子文件夹
     * @param path 路径
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if(!file.exists()) {
            return;
        }

        if(file.isFile()) {
            file.delete();
            return;
        }

        if(file.isDirectory()) {
            for(File f : file.listFiles()) {
                deleteFile(f.getAbsolutePath());
            }
            file.delete();
        } else{
            file.delete();
        }
    }

    /**
     * 递归取得文件夹大小
     * @param file
     * @return long
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file != null && file.exists() && file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()){
                    size = size + getFileSize(files[i]);
                }else {
                    size = size + files[i].length();
                }
            }
        }
        return size;
    }

    /**
     * 获取SD卡剩余空间的大小(SD卡剩余空间的大小（单位：byte）)
     * @return long
     */
    @SuppressWarnings("deprecation")
    public static long getSdCardSize() {
        String str = Environment.getExternalStorageDirectory().getPath();
        StatFs localStatFs = new StatFs(str);
        long blockSize = localStatFs.getBlockSize();
        return localStatFs.getAvailableBlocks() * blockSize;
    }


    /**
     * 检查SDCard是否可用，是否存在
     * @return boolean
     */
    public static boolean getSdCardIsEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }




    //****文件内容读写**********************************************************************************************


    //往SD卡写入文件的方法
    public void savaFileToSD(String filename, String filecontent) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            FileOutputStream output = new FileOutputStream(filename);
            output.write(filecontent.getBytes());
            //将String字符串以字节流的形式写入到输出流中
            output.close();
            //关闭输出流
        } else {
            Log.d("----", "SD卡不存在或者不可读写");
        }
    }

    //读取SD卡中文件的方法
    //定义读取文件的方法:
    public String readFromSD(String filename) throws IOException {
        StringBuilder sb = new StringBuilder("");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //打开文件输入流
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[1024];

            int len = 0;
            //读取文件内容:
            while ((len = input.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            input.close();
        }
        return sb.toString();
    }


}
