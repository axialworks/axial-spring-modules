package com.axial.modules.openapi;

import com.axial.modules.openapi.model.config.ApiConfig;
import com.axial.modules.openapi.model.config.ApplicationApiConfig;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
@PropertySource("classpath:default-springdoc-config.properties")
public class SpringDocInitializer {

    private final ConfigurableListableBeanFactory beanFactory;

    private final ApplicationApiConfig applicationApiConfig;

    private final SpringDocCustomizerActions customizerActions;


    @PostConstruct
    private void initOps() {
        addCustomizers();
    }

    private void addCustomizers() {

        final Map<String, ApiConfig> apiMap = MapUtils.emptyIfNull(applicationApiConfig
                .getApis())
                .values()
                .stream()
                .collect(Collectors.toMap(ApiConfig::getName, Function.identity()));

        MapUtils
                .emptyIfNull(beanFactory
                        .getBeansOfType(GroupedOpenApi.class))
                .values().
                forEach(groupedOpenApi ->
                        groupedOpenApi
                                .getOpenApiCustomizers()
                                .add(openApi ->
                                        customizerActions.customizeOpenAPI(openApi, apiMap.get(groupedOpenApi.getGroup())))
                );
    }

}
