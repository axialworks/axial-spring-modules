package com.axial.modules.commons.component.exception.definition;

import com.axial.modules.commons.model.Message;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;

@Getter
public class AxialException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String code;

    private final String key;

    private final List<Message> additionalMessages;


    protected AxialException(String code, String key, String message, Message... additionalMessages) {

        super(message);
        this.code = code;
        this.key = key;
        this.additionalMessages = ArrayUtils.isNotEmpty(additionalMessages)
                ? Arrays.asList(additionalMessages) : null;
    }

}