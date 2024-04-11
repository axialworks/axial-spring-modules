package com.axial.modules.openapi.model.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ApiConfig {

    /**
     * Unique name for Api
     */
    private String name;

    /**
     * Visible name for Api
     */
    private String groupName;

    private String path;

    private Map<String, SecurityHeaderConfig> securityHeaders;

    private Map<String, HeaderConfig> headers;

    private String description;

}
