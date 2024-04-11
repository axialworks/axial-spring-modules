package com.axial.modules.commons.component.mapping;

import com.axial.modules.commons.core.template_engine.TextTemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ObjectMappingService {

    private static ObjectMapper objectMapper;

    private final TextTemplateService textTemplateService;

    @PostConstruct
    public void initOps() {

        objectMapper = new ObjectMapper();
    }

    private ObjectMapper getObjectMapper() {

        return objectMapper;
    }

    public <T> TypeReference<T> getTypeReference(T object) {

        return new TypeReference<T>() {};
    }

    public <T> T makeDeepCopy(T object) {

        final String str = objectToString(object);
        return stringToObject(str, new TypeReference<T>() {});
    }

    public <T> String objectToString(T object) {

        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T stringToObject(String objectStr, Class<T> classType) {

        return stringToObject(objectStr, classType, null);
    }

    public <T> T stringToObject(String objectStr, TypeReference<T> typeReference) {

        return stringToObject(objectStr, typeReference, null);
    }

    public <T> T stringToObject(String objectStr, Class<T> classType, Context context) {

        try {
            final String strText = Objects.nonNull(context) ?
                    textTemplateService.processTemplate(objectStr, context) : objectStr;

            return getObjectMapper().readValue(strText, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T stringToObject(String objectStr, TypeReference<T> typeReference, Context context) {

        try {
            final String strText = Objects.nonNull(context) ?
                    textTemplateService.processTemplate(objectStr, context) : objectStr;

            return getObjectMapper().readValue(strText, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
