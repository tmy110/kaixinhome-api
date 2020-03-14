package com.kaixin8848.home.utility.result;


import com.kaixin8848.home.utility.StringUtils;


public class ParameterErrorResultUtil {

    public static Boolean forMobile(String mobile) {
        if (mobile != null && mobile.length() == 11) {
            return true;
        }
        return false;
    }

    public static Boolean forString(String str) {
        if (StringUtils.isEmptyOrNull(str))
            return false;
        return true;
    }

    public static Boolean forByteNotZero(Byte bt) {
        if (null != bt && 0 != bt)
            return true;
        return false;
    }

    public static Boolean forByte(Byte bt) {
        if (null != bt)
            return true;
        return false;
    }

    public static Boolean forLong(Long lg) {
        if (null != lg && 0 != lg.longValue())
            return true;
        return false;
    }

}
