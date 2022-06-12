package ru.tsvlad.octopus.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "object-storage")
@Data
public class ObjectStorageProperties {
    private String bucketName;
    private String keyId;
    private String keySecret;
    private Long urlDuration;
}
