package com.kaixin8848.home.utility.redis;

public enum RedisCode {

    IMAGE_VERIFICATION_CODE("imageVerificationCode"),

    MOBILE_VERIFICATION_CODE("mobileVerificationCode");

    private final String key;

    RedisCode(String key) {
        this.key =key;
    }

    public  String  key(){
        return  this.key+"-";
    }

    public  String  key(String id){
        return  this.key + "-" + id;
    }

    public  String  key(long id){
        return  this.key  + "-" + id;
    }
}
