package br.com.lirio.appredis.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class RedisAuth {

    @Getter
    @JsonProperty("spring.redis.password")
    private String redisAuth;

}