package com.axial.modules.commons.outline.enum_code;

public interface EnumCode<T extends EnumCode> {

    String getCode();

    String getName();

    String getExplanation();

    default String getEnumTypeCodeInDB() {
        return null;
    }

    default boolean isExistsInDB() {
        return false;
    }

}
