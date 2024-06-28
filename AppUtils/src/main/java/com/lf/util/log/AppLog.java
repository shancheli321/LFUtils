package com.lf.util.log;

import android.content.Context;

import com.apkfuns.log2file.LogFileEngineFactory;
import com.apkfuns.logutils.LogUtils;

/**
 * @date: 2024/1/11
 *
 * https://github.com/pengwei1024/LogUtils
 *
 * 支持直接打印数据集合, 如List、Set、Map、数组等
 * 全局配置log输出, 个性化设置Tag
 * 准确显示调用方法、行，快速定位日志所在文件位置
 * 支持android系统复杂对象Intent、Bundle、Message等打印
 * 提供空实现 release-no-op版本
 * 支持高性能日志写入文件(基于mmap)
 *
 * mmap 日志原理
 *      [高性能]日志优先写到内存映射文件，和写内存一样高效
 *      [保证日志不丢失]日志超过4k立即写入文件，每次程序重新启动立即写入，或者端上调用LogUtils.getLog2FileConfig().flushAsync()立即写入
 */
public class AppLog {

    /**
     * @param context
     */
    public static void init(Context context) {

        /*
            configAllowLog	是否允许日志输出	boolean	true
            configTagPrefix	日志log的前缀	String	"LogUtils"
            configShowBorders	是否显示边界	boolean	false
            configLevel	日志显示等级	LogLevelType	LogLevel.TYPE_VERBOSE
            addParserClass	自定义对象打印	Parser	无
            configFormatTag	个性化设置Tag	String	%c{-5}
            configMethodOffset	方法偏移量	int	0
         */
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("AppLog--")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");

        // 支持写入日志到文件
        // targetSdkVersion >= 23 需要确保有写sdcard权限
        /*
            configLog2FileEnable	是否支持写入文件	boolean	false
            configLog2FilePath	写入日志路径	String	无
            configLog2FileNameFormat	写入日志文件名	string	%d{yyyyMMdd}.txt
            configLog2FileLevel	写入日志等级	LogLevelType	LogLevel.TYPE_VERBOSE
            configLogFileEngine	写入日志实现	LogFileEngine	无
            configLogFileFilter	写入日志过滤	LogFileFilter	无
         */

        String logPath = context.getExternalFilesDir("logs").getPath();
        LogUtils.getLog2FileConfig()
                .configLog2FileEnable(true)
                .configLog2FilePath(logPath)
                .configLog2FileNameFormat("%d{yyyyMMdd}.txt")
                .configLogFileEngine(new LogFileEngineFactory(context));
    }


    public static void d(Object object) {
        LogUtils.d(object);
    }

    public static void d(String msg, Object... args) {
        LogUtils.d(msg, args);
    }

    public static void json(String json) {
        LogUtils.json(json);
    }

    public static void i(Object object) {
        LogUtils.i(object);
    }

    public static void i(String msg, Object... args) {
        LogUtils.i(msg, args);
    }

    public static void e(Object object) {
        LogUtils.e(object);
    }

    public static void e(String msg, Object... args) {
        LogUtils.e(msg, args);
    }

}
