package com.axial.modules.commons.component.exception.type;

import com.axial.modules.commons.component.exception.definition.AxialException;
import com.axial.modules.commons.model.Message;
import com.axial.modules.commons.outline.message_key.MessageKey;

import java.io.Serializable;

public class ValidationException extends AxialException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ValidationException(String code, String key, String message, Message... additionalMessages) {
        super(code, key, message, additionalMessages);
    }

    public ValidationException(MessageKey messageKey, String message, Message... additionalMessages) {
        super(messageKey.getCode(), messageKey.getKey(), message, additionalMessages);
    }
}