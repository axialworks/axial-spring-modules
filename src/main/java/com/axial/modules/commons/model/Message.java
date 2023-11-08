package com.axial.modules.commons.model;

import com.axial.modules.commons.outline.message_key.MessageKey;
import com.axial.modules.commons.core.message.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private Severity severity;

    private String code;

    private String key;

    private String message;


    public Message(Severity severity, MessageKey messageKey, String message) {

        this.severity = severity;
        this.code = messageKey.getCode();
        this.key = messageKey.getKey();
        this.message = message;
    }
}