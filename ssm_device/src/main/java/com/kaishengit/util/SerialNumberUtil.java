package com.kaishengit.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

public class SerialNumberUtil {
    public static String getSerialNumber() {
        DateTime now = new DateTime();
        return now.toString("YYYYMMddHHmmss") + RandomStringUtils.randomNumeric(2);
    }
}
