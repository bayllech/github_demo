package com.kaishengit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by bayllech on 2016/12/15.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String toUtf(String str) {
        try {
            return new String(str.getBytes("ISO8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串{}转换异常",str);
            throw new RuntimeException("转换字符串："+str+"异常");
        }
    }
}
