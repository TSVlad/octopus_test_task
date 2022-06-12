package ru.tsvlad.user.config.props;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@ConditionalOnProperty("keycloak.enabled")
@Data
public class KeycloakProperties {
    private String realm;
}
