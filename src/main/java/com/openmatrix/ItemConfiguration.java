package com.openmatrix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ItemConfiguration {
    @Bean
    ReactiveRedisOperations<String, Item> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Item> serializer = new Jackson2JsonRedisSerializer<>(Item.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Item> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Item> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}
