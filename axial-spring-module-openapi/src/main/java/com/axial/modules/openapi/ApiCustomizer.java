package com.axial.modules.openapi;

import com.axial.modules.openapi.model.ApiHeader;
import com.axial.modules.openapi.model.config.SecurityHeaderConfig;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface ApiCustomizer {

    default List<ApiHeader> getDefaultHeaders() {

        final List<ApiHeader> apiHeaders = Arrays.asList(
                ApiHeader
                        .builder()
                        .key("XForwardedFor")
                        .name("X-FORWARDED-FOR")
                        .defaultValue("0.0.0.0")
                        .required(false)
                        .description("Redirect IP address")
                        .defaultApiHeader(false)
                        .defaultSecurityHeader(false)
                        .build(),
                ApiHeader
                        .builder()
                        .key("AcceptLanguage")
                        .name("Accept-Language")
                        .defaultValue("tr")
                        .required(false)
                        .description("Accept Language")
                        .defaultApiHeader(true)
                        .defaultSecurityHeader(false)
                        .build()
        );

        return Collections.unmodifiableList(apiHeaders);
    }

    default List<ApiHeader> getHeaders() {
        return new ArrayList<>(getDefaultHeaders());
    }

    default List<ApiHeader> getApiHeaders() {
        return CollectionUtils.emptyIfNull(getHeaders())
                .stream().filter(ApiHeader::isDefaultApiHeader).collect(Collectors.toUnmodifiableList());
    }

    default List<SecurityHeaderConfig> getSecurityHeaders() {
        return CollectionUtils.emptyIfNull(getHeaders()).stream()
                .filter(ApiHeader::isDefaultSecurityHeader)
                .map(OpenApiMapper::convertApiHeaderToSecurityHeaderConfig)
                .collect(Collectors.toUnmodifiableList());
    }

}
