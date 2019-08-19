package com.face.utils;

import org.apache.log4j.Logger;

public class LoggerInfo {
    private static Logger logger;

    // 提供一个单例实例
    public Logger getLoggerInstance() {
        return logger;
    }

    //  记录 fatal(致命错误) 级别的信息
    public static void fatal(Object object) {
        logger.fatal(object);
    }

    public static void fatal(Object object, Throwable e) {
        logger.fatal(object, e);
    }

    //  记录 debug(调试) 级别的信息
    public static void debug(Object object) {
        logger.debug(object);
    }

    public static void debug(Object object, Throwable e) {
        logger.debug(object, e);
    }

    //  记录 info(一般) 级别的信息
    public static void info(Object object) {
        logger.info(object);
    }

    public static void info(Object object, Throwable e) {
        logger.info(object, e);
    }

    //  记录 warn(警告) 级别的信息
    public static void warn(Object object) {
        logger.warn(object);
    }

    public static void warn(Object object, Throwable e) {
        logger.info(object, e);
    }

    //  记录 error(错误) 级别的信息
    public static void error(Object object) {
        logger.error(object);
    }

    public static void error(Object object, Throwable e) {
        logger.error(object, e);
    }
}
