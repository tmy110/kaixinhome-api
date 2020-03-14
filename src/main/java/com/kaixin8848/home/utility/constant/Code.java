package com.kaixin8848.home.utility.constant;

import java.math.BigDecimal;

public interface Code {
    /*系统配置*/
    int SESSION_TIMEOUT = 24 * 60 * 60;//SESSION过期时间（秒）

    String NULL_STR = "";
    String ZERO_STR = "0";
    long ZERO_LONG = 0L;


    String SUPER_PWD = "24904629C54BDDECECCCB3435E3C829A";

    BigDecimal ZERO_DCM = BigDecimal.ZERO;

    /*时间格式*/
    String MM = "MM";
    String YYYY_MM = "yyyy-MM";
    String YYYY_MM_DD = "yyyy-MM-dd";
    String YYYY_MM_DD_SLASH = "yyyy/MM/dd";
    String YYYY_MM_DD_CHINESE = "yyyy年MM月dd日";
    String YYYY_MM_DD_HH_MM_SLASH = "yyyy/MM/dd HH:mm";
    String YYYYMMDD = "yyyyMMdd";
    String YYYYMM = "yyyyMM";
    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    String DD_HH_MM = "HH:mm:ss";

    Byte ZERO = 0;
    Byte ONE = 1;

}
