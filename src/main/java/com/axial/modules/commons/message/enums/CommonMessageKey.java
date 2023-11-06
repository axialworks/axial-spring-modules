package com.axial.modules.commons.message.enums;

import com.axial.modules.commons.message.CommonMessageUtils;
import com.axial.modules.commons.message.definition.MessageKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonMessageKey implements MessageKey {

    REFLECTION_ONLY_ENUM_CLASSES_ALLOWED(
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "1001"),
            "exception.module.commons.reflection.only_enum_classes_allowed"),

    REFLECTION_ENUM_CODE_TYPE_INCOMPATIBLE(
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "1002"),
            "exception.module.commons.reflection.enum_code_type_incompatible"),

    REFLECTION_ENUM_CODE_VALUE_INVALID(
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "1003"),
            "exception.module.commons.reflection.enum_code_value_invalid");


    /*
        CODE: SeverityCode (1 digit) + ModuleCode (2 digit) + MessageCode (4 digit)
        EXAMPLE: 1 + 01 + 0001 => 101001
        Common Messages ModuleCode: 01
     */
    private String code;

    private String key;

}
