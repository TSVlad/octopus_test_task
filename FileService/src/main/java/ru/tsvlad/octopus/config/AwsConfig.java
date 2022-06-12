package ru.tsvlad.octopus.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tsvlad.octopus.config.props.ObjectStorageProperties;

@Configuration
@AllArgsConstructor
public class AwsConfig {

    private final ObjectStorageProperties objectStorageProperties;

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        objectStorageProperties.getKeyId(), objectStorageProperties.getKeySecret())))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "storage.yandexcloud.net","ru-central1"))
                .build();
    }
}
