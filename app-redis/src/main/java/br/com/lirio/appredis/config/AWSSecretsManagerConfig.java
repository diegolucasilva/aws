package br.com.lirio.appredis.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


@Configuration
@Slf4j
public class AWSSecretsManagerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurerDev(ConfigurableEnvironment env) throws JsonProcessingException {

        final Map<String, Object> secrets = getSecrets(env);

        final MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(new MapPropertySource("newmap", secrets));

        return new PropertySourcesPlaceholderConfigurer();
    }

    private static Map<String, Object> getSecrets(ConfigurableEnvironment env) throws JsonProcessingException {
        final String secretName = env.getProperty("aws.secretsManager.secretName");
        final String region = env.getProperty("aws.secretsManager.region");
        final String endpoint = env.getProperty("aws.secretsManager.endpoint");

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);

        final String secret = getSecretValueResult.getSecretString();
        return new ObjectMapper().readValue(secret, Map.class);

    }

}