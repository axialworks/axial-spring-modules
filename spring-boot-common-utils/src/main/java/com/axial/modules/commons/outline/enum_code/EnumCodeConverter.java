package com.axial.modules.commons.outline.enum_code;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Converter(autoApply = false)
public abstract class EnumCodeConverter<T extends EnumCode> implements AttributeConverter<T, String> {

    public abstract Class<T> getClassType();

    @Override
    public String convertToDatabaseColumn(T enumValue) {

        return Objects.nonNull(enumValue) ? enumValue.getCode() : null;
    }

    @Override
    public T convertToEntityAttribute(String columnValue) {

        return StringUtils.isNotBlank(columnValue) ?
                EnumCodeUtils.findByCode(getClassType(), columnValue) : null;
    }
}
