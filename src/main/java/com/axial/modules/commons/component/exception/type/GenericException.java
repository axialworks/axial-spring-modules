package com.axial.modules.commons.component.exception.type;

import com.axial.modules.commons.component.exception.definition.AxialException;
import com.axial.modules.commons.model.Message;
import com.axial.modules.commons.outline.message_key.MessageKey;

import java.io.Serializable;

public class GenericException extends AxialException implements Serializable {

    private static final long serialVersionUID = 1L;

    public GenericException(String code, String key, String message, Message... additionalMessages) {
        super(code, key, message, additionalMessages);
    }

    public GenericException(MessageKey messageKey, String message, Message... additionalMessages) {
        super(messageKey.getCode(), messageKey.getKey(), message, additionalMessages);
    }
}
