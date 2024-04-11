package com.axial.modules.openapi;

import com.axial.modules.openapi.model.ApiHeader;
import com.axial.modules.openapi.model.config.SecurityHeaderConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiMapper {

    protected static SecurityHeaderConfig convertApiHeaderToSecurityHeaderConfig(ApiHeader apiHeader) {

        if (Objects.isNull(apiHeader)) {
            return null;
        }
        return SecurityHeaderConfig
                .builder()
                .key(apiHeader.getKey())
                .name(apiHeader.getName())
                .example(apiHeader.getDefaultValue())
                .description(apiHeader.getDescription())
                .build();
    }

}
