package cn.cheng.flux.redis.config;

import cn.cheng.flux.common.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Cheng Mingwei
 * @create 2020-08-31 18:21
 **/
@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, Object> commonRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, Object> serializationContext =
                RedisSerializationContext.<String, Object>newSerializationContext(RedisSerializer.string())
                        .value(RedisSerializer.json())
                        .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    @Bean
    public ReactiveRedisTemplate<String, User> userRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, User> serializationContext =
                RedisSerializationContext.<String, User>newSerializationContext(RedisSerializer.string())
                        .value(new Jackson2JsonRedisSerializer<>(User.class))
                        .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

}
