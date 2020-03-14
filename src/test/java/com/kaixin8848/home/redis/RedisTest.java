//package com.kaixin8848.home.redis;
//
//import com.kaixin8848.home.Tester;
//import com.kaixin8848.home.redis.bean.TestInfo;
//import com.kaixin8848.home.utility.redis.RedisUtil;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//
//public class RedisTest extends Tester {
//
//    @Autowired
//    RedisUtil redisUtil;
//
//    @Test
//    public void test() {
//        // =============================common============================
//        System.out.println("=============================common============================");
//        //指定缓存失效时间
////        boolean test01 = redisUtil.expire("common-test01", 1000);
////        System.out.println("指定缓存失效时间:" + test01);
//        //根据key 获取过期时间
////        long test011 = redisUtil.getExpire("common-test01");
////        System.out.println("根据key 获取过期时间:" + test011);
//        //判断key是否存在
////        boolean test012 = redisUtil.hasKey("common-test01");
////        System.out.println("判断key是否存在:" + test012);
////        //删除缓存
////        redisUtil.del("common-test01");
////        System.out.println("删除缓存");
//        System.out.println("============================String=============================");
//        // ============================String=============================
//        //普通缓存放入
//        boolean set = redisUtil.set("String-test01", "String-test01Value");
//        System.out.println("普通缓存放入:" + set);
//        //普通缓存获取
//        String test02 = (String) redisUtil.get("String-test01");
//        System.out.println("普通缓存获取:" + test02);
//
//        //普通缓存放入并设置时间
//        boolean set03 = redisUtil.set("String-test02", "String-test02Value", 100);
//        System.out.println("普通缓存放入并设置时间:" + set03);
//
//        long test03 = redisUtil.getExpire("String-test02");
//        System.out.println("根据key 获取过期时间:" + test03);
//        //递增
//        //递减
//        boolean set04 = redisUtil.set("String-test03", 100);
//        System.out.println("普通缓存放入:" + set04);
//
//        Integer test043 = (Integer) redisUtil.get("String-test03");
//        System.out.println("普通缓存获取:" + test043);
//
//        long test041 = redisUtil.incr("String-test03", 10);
//        System.out.println("递增:" + test041);
//
//        test043 = (Integer) redisUtil.get("String-test03");
//        System.out.println("普通缓存获取:" + test043);
//
//        long test042 = redisUtil.decr("String-test03", 100);
//        System.out.println("递减:" + test042);
//
//        test043 = (Integer) redisUtil.get("String-test03");
//        System.out.println("普通缓存获取:" + test043);
//        System.out.println("================================Map=================================");
//        //================================Map=================================
//        //HashSet
//        Map<String, Object> map = new HashMap<>();
//        map.put("Map-01Key", "Map-01Value");
//        map.put("Map-02Key", "Map-02Value");
//        map.put("Map-03Key", "Map-03Value");
//        map.put("Map-04Key", "Map-04Value");
//        boolean hashset01 = redisUtil.hmset("Map-test01", map);
//        System.out.println("HashSet:" + hashset01);
//        //获取hashKey对应的所有键值
//        Map<Object, Object> hashset011 = redisUtil.hmget("Map-test01");
//        if (hashset011 != null && hashset011.size() > 0) {
//            for (Map.Entry<Object, Object> entry : hashset011.entrySet()) {
//                String mapKey = (String) entry.getKey();
//                String mapValue = (String) entry.getValue();
//                System.out.println("获取hashKey对应的所有键值:" + mapKey + ":" + mapValue);
//            }
//        }
//        //HashGet
//        String hget = (String) redisUtil.hget("Map-test01", "Map-04Key");
//        System.out.println("HashGet:" + hget);
//        //HashSet 并设置时间
//        boolean hashset02 = redisUtil.hmset("Map-test02", map, 100);
//        System.out.println("HashSet 并设置时间:" + hashset02);
//        //向一张hash表中放入数据,如果不存在将创建
//        boolean hset01 = redisUtil.hset("Map-test03", "Map-01Key", "Map-01Value");
//        boolean hset02 = redisUtil.hset("Map-test03", "Map-02Key", "Map-02Value", 100);
//        System.out.println("向一张hash表中放入数据,如果不存在将创建:" + hset01 + " " + hset02);
//        //判断hash表中是否有该项的值
//        boolean b01 = redisUtil.hHasKey("Map-test03", "Map-02Key");
//        System.out.println("判断hash表中是否有该项的值" + b01);
//        //删除hash表中的值
//        redisUtil.hdel("Map-test03", "Map-01Key");
//        //判断hash表中是否有该项的值
//        boolean b02 = redisUtil.hHasKey("Map-test03", "Map-01Key");
//        System.out.println("判断hash表中是否有该项的值:" + b01 + " " + b02);
//        //hash递增 如果不存在,就会创建一个 并把新增后的值返回
//        double hincr01 = redisUtil.hincr("Map-test04", "Map-01Key", 100);
//        double hincr02 = redisUtil.hincr("Map-test04", "Map-01Key", 120);
//        //hash递减
//        double hdecr01 = redisUtil.hdecr("Map-test04", "Map-01Key", 10);
//        double hdecr02 = redisUtil.hdecr("Map-test04", "Map-01Key", 100);
//        System.out.println("hash递增&hash递减:" + hincr01 + " " + hincr02 + " " + hdecr01 + " " + hdecr02);
//        // ============================set=============================
//        System.out.println("============================set=============================");
//        //根据key获取Set中的所有值
//        Set<Object> settest01 = redisUtil.sGet("Set-test01");
//        System.out.println("根据key获取Set中的所有值:" + settest01.size());
//        //根据value从一个set中查询,是否存在
//        boolean b = redisUtil.sHasKey("Set-test01", "Set-test01Value");
//        System.out.println("根据value从一个set中查询,是否存在:" + b);
//        //将数据放入set缓存
//        long l = redisUtil.sSet("Set-test01", "Set-test01Value");
//        System.out.println("将数据放入set缓存:" + l);
//        //将set数据放入缓存
//        long l2 = redisUtil.sSetAndTime("Set-test02", 100, "Set-test02Value");
//        System.out.println("将set数据放入缓存:" + l2);
//        //获取set缓存的长度
//        long settest011 = redisUtil.sGetSetSize("Set-test02");
//        System.out.println("获取set缓存的长度:" + settest011);
//        //移除值为value的
//        long l1 = redisUtil.setRemove("Set-test02", "Set-test02Value");
//        System.out.println("移除值为value的:" + l1);
//
//        // ===============================list=================================
//        System.out.println("===============================list=================================");
//        //获取list缓存的长度
//        long listtest011 = redisUtil.lGetListSize("List-test01");
//        System.out.println("获取list缓存的长度:" + listtest011);
////        //通过索引 获取list中的值
//        Object listtest012 = redisUtil.lGetIndex("List-test01", 1);
//        System.out.println("通过索引 获取list中的值:" + listtest012);
//        //将list放入缓存
//        TestInfo testInfo = new TestInfo();
//        testInfo.setAge(29);
//        testInfo.setName("田满意");
//        boolean listtest013 = redisUtil.lSet("List-test01", testInfo);
//        System.out.println("将list放入缓存" + listtest013);
//
//        //获取list缓存的内容
//        List<Object> listtest01 = redisUtil.lGet("List-test01", 0, -1);
//        for (Object object : listtest01) {
//            System.out.println("获取list缓存的内容:" + object);
//        }
//        //将list放入缓存
//        boolean listtest02 = redisUtil.lSet("List-test02", testInfo, 100);
//        System.out.println("将list放入缓存:" + listtest02);
//        //将list放入缓存
//        List<TestInfo> testInfos = new ArrayList<>();
//        testInfos.add(testInfo);
//        testInfos.add(testInfo);
//        testInfos.add(testInfo);
//        testInfos.add(testInfo);
//        boolean listtest0301 = redisUtil.lSet("List-test03", testInfos);
//        boolean listtest0302 = redisUtil.lSet("List-test04", testInfos, 1000);
//        System.out.println("将list放入缓存:" + listtest0301 + " " + listtest0302);
//        //根据索引修改list中的某条数据
//        boolean listtest03 = redisUtil.lUpdateIndex("List-test03", 0, testInfo);
//        System.out.println("根据索引修改list中的某条数据:" + listtest03);
//        //移除N个值为value
//        long listtest031 = redisUtil.lRemove("List-test03", 0, testInfo);
//        System.out.println("移除N个值为value:" + listtest031);
//    }
//}
