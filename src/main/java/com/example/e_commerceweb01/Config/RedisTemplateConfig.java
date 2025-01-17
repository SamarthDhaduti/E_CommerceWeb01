package com.example.e_commerceweb01.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {

    /* here we are using String and Object as Key and Value
    Products -> {products_1, {....} , ....}
    Categories -> {category_1 , {....} , ... }
    Key is string because to specify that is it product object or category object and value will be any of the object
    so we mentioned it as object
     */

    @Bean
    public RedisTemplate<String, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
           RedisTemplate<String, Object> template = new RedisTemplate<>();
           template.setConnectionFactory(redisConnectionFactory);
           return template;
    }
}
