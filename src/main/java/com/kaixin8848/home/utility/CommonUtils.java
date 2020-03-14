package com.kaixin8848.home.utility;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaixin8848.home.utility.constant.Code;
import net.sf.json.JSONArray;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 马路遥
 * @Description: 公共方法
 */
public class CommonUtils {
    private final static String ENCODE = "UTF-8";

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 加密方法
     *
     * @param password 明文密码
     * @return 加密后的大写字符串
     */
    public static String encryptPassword(String password) {
        return DigestUtils.md5Hex(password).toUpperCase();
    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param str
     * @return
     */
    public static String getStr(String str) {
        return str == null ? Code.NULL_STR : str;
    }

    /**
     * Date转String
     * @param date
     * @param patten
     * @return
     */
    public static String dateCastToString(Date date, String patten) {
        return date == null ? Code.NULL_STR : DateFormatUtils.format(date, patten);
    }
    /**
     * String转Date
     * @param dateStr
     * @param patten
     * @return
     */
    public static Date stringCastToDate(String dateStr, String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取字符串中的大写字母
     * @param str
     * @return
     */
    public static String interceptTheInitials(String str){
        char a[] = str.toCharArray();
        String upperName = "";
        for(int i=0; i<a.length; i++){
            if(a[i]<='Z' && a[i]>='A'){
                upperName=upperName+a[i];
            }
        }
        return upperName;
    }


    /**
     * 安全截取字符串
     *
     * @param str
     * @param length
     * @return
     */
    public static String getSubStr(String str, Integer length) {
        if (StringUtils.isNotBlank(str)) {
            if (str.length() > length) {
                return str.substring(0, length);
            } else {
                return str;
            }
        }
        return Code.NULL_STR;
    }

    /**
     * 去重list中的重复值
     *
     * @param list
     * @return
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     * 将对象转换成Json字符串
     *
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

    /**
     * 将Json字符串转换成Map对象
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> jsonToMap(String jsonStr) {
        Gson gson = new Gson();
        Map<String, Object> param = gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {}.getType());
        return param;
    }
    /**
     * 将Json字符串转换成Map对象
     * @param jsonArrayStr
     * @return
     */
    public static List<Map<String,Object>> jsonArrayToMapList(String jsonArrayStr) {
        Gson gson = new Gson();
        JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);
        List<Map<String,Object>> maps =new ArrayList<Map<String, Object>>();
        for(int i = 0; i<jsonArray.size();i++){
            String jsonStr=jsonArray.getString(i);
            Map<String, Object> param = gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {}.getType());
            maps.add(param);
        }
        return maps;
    }



    /**
     * @param str
     * @return
     * @author zhoupeng  20170410
     * 去除字符串中的空格
     */
    public static String TrimStr(String str) {
        String returnStr = null;
        if (StringUtils.isNotBlank(str)) {
            returnStr = str.replace(" ", "");
        }
        return str;
    }

    /**
     * long转换成String
     *
     * @param number
     * @return
     */
    public static String longCastString(Long number) {
        if (number == null) {
            return Code.NULL_STR;
        }
        String str = String.valueOf(number);
        return str;
    }
    /**
     * BigDecimal转换成String
     *
     * @param number
     * @return
     */
    public static String bigDecimalCastString(BigDecimal number) {
        if (number == null) {
            return Code.NULL_STR;
        }
        String str = String.valueOf(number);
        return str;
    }

    /**
     * 检查输入参数，是否为null
     *
     * @param str
     * @return
     */
    public static String inspectStr(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.trim();
        }
        return null;
    }

    //将yyyy-mm-dd解析成Date类型
    public static Date getDatefromStr(String datestr) {
        SimpleDateFormat sdf = new SimpleDateFormat(Code.YYYY_MM_DD);
        try {
            Date date = sdf.parse(datestr);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    //将yyyy-mm-dd HH:mm:ss解析成Date类型
    public static Date getDateTimefromStr(String datestr) {
        SimpleDateFormat sdf = new SimpleDateFormat(Code.YYYY_MM_DD_HH_MM_SS);
        try {
            Date date = sdf.parse(datestr);
            return date;
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 使用身份证号解析性别
     *
     * @param identityCard
     * @return 1男 0女
     */
    public static Byte resolveGender(String identityCard) {
        if (StringUtils.isBlank(identityCard)) {
            return null;
        }
        String gender = StringUtils.substring(identityCard, 16, 17);
        if (Integer.valueOf(gender) % 2 == 0) {
            return Code.ZERO;
        }
        return Code.ONE;
    }


    /** 产生一个长度为length的随机字符串*/
    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    /** 产生一个长度为length的随机数字字符串*/
    public static String randomNumberStr(int length) {
        if(length<1){
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return String.valueOf((long)(Math.random()*9*Math.pow(10,length-1)) + (long)Math.pow(10,length-1));
    }
    /**
     * URL 解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static Double decimalFormat(String str) {
        if(str==null){
            return null;
        }
        double d1 = 0;
        try {
            d1 = new DecimalFormat().parse(str).doubleValue();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d1;
    }
    /*********************************自定义用公共方法*******************************************/
    public static void main(String[] args) {

    }

}
