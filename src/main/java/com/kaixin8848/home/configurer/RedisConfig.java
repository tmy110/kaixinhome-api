package com.kaixin8848.home.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration//表示这个一个配置类，相当于spring的xml 文件
public class RedisConfig {
    /**
     * 防止redis入库序列化乱码的问题
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean//相当于spring <bean id="redisTemplate"> 默认的名称 就是方法名
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());// key序列化 简单的字符串序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());// value序列化 序列化object对象为json字符串
        //下面两行不太明白，难道set list或者zset不进行单独的序列化吗？？？
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());// Hash key序列化 简单的字符串序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());// Hash value序列化 序列化object对象为json字符串
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}