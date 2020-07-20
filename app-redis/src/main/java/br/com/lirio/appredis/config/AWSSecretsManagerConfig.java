package br.com.lirio.appredis.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;


@Configuration
@Slf4j
public class AWSSecretsManagerConfig {

    public static PropertySourcesPlaceholderConfigurer placeholderConfigurerDev(ConfigurableEnvironment env) throws JsonProcessingException {

        final String secretName = env.getProperty("aws.secretsManager.secretName");
        final String region = env.getProperty("aws.secretsManager.region");
        final String endpoint = env.getProperty("aws.secretsManager.endpoint");

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        String secret = getSecretValueResult.getSecretString();
        RedisAuth redisAuth = new ObjectMapper().readValue(secret, RedisAuth.class);
        log.info("Redis secret  => {}", redisAuth.getRedisAuth());

        MutablePropertySources propertySources = env.getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("spring.redis.password",
                redisAuth.getRedisAuth());
        propertySources
                .addFirst(new MapPropertySource("newmap", map));
        return new PropertySourcesPlaceholderConfigurer();
    }

}