package com.axial.modules.commons.component.exception;

import com.axial.modules.commons.core.message.CommonMessageUtils;
import com.axial.modules.commons.core.message.enums.Severity;
import com.axial.modules.commons.outline.message_key.MessageKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestExceptionMessageCode implements MessageKey {

    UNKNOWN_EXCEPTION("exception.message.unknown",
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "0001"),
            "Unknown exception occurred."),

    UNKNOWN_EXCEPTION_TYPE(null, null, "Unknown exception type"),

    PARAMETER_BINDING_ERROR(null, null, "Parameter binding exception occurred."),
    METHOD_NOT_ALLOWED_ERROR("exception.message.method_not_allowed",
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "0002"),
            null),

    DEFAULT_VALIDATION_ERROR("validation.message.default",
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "0003"),
            "A validation error occurred."),

    ENUM_FORMAT_ERROR("validation.message.invalid_enum_value",
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "0004"),
            "Invalid enum value! Valid values: %s"),
    INVALID_REQUEST_BODY("validation.message.invalid_request_body",
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "0005"),
            "Invalid request value!"),
    REQUEST_FORMAT_ERROR("validation.message.request_format_error",
            CommonMessageUtils.generateMessageCode(Severity.ERROR, "0006"),
            null),
    BAD_FORMAT_ERROR(null, null, "Bad Format: %s - %s");


    private String code;

    private String key;

    private String message;

}
