package com.axial.modules.openapi.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ConfigurationProperties("api-config")
public class ApplicationApiConfig {

    /**
     * Application Name
     */
    private String name;

    /**
     * Custom server's domain names
     */
    private List<String> domains;

    private String version;

    private Map<String, SecurityHeaderConfig> commonSecurityHeaders;

    private Map<String, HeaderConfig> commonHeaders;

    private Map<String, ApiConfig> apis;

}
