package com.axial.modules.commons.component.exception.handler;

import com.axial.modules.commons.component.exception.definition.AxialException;
import com.axial.modules.commons.component.exception.RestExceptionMessageCode;
import com.axial.modules.commons.component.exception.type.ValidationException;
import com.axial.modules.commons.core.message.enums.Severity;
import com.axial.modules.commons.model.ErrorResponse;
import com.axial.modules.commons.model.Message;
import com.axial.modules.commons.outline.message_key.MessageKey;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestExceptionUtils {

    protected static ValidationException mapToValidationException(Class<?> targetType, List<JsonMappingException.Reference> pathList, String message) {

        if (Objects.isNull(targetType)) {
            return new ValidationException(RestExceptionMessageCode.DEFAULT_VALIDATION_ERROR, message);
        } else {
            String formattedMessage;
            if (targetType.isEnum()) {
                formattedMessage = getEnumMessage(targetType);
                return new ValidationException(RestExceptionMessageCode.ENUM_FORMAT_ERROR, formattedMessage);
            } else if (!CollectionUtils.isEmpty(pathList)) {
                formattedMessage = getRequestBodyMessage(targetType, pathList);
                return new ValidationException(RestExceptionMessageCode.REQUEST_FORMAT_ERROR, formattedMessage);
            } else {
                return new ValidationException(RestExceptionMessageCode.DEFAULT_VALIDATION_ERROR, message);
            }
        }
    }

    protected static ValidationException createInvalidRequestBodyException() {

        return new ValidationException(RestExceptionMessageCode.INVALID_REQUEST_BODY, RestExceptionMessageCode.INVALID_REQUEST_BODY.getMessage(), new Message[0]);
    }

    protected static String getEnumMessage(Class<?> targetType) {

        final String validOptions = new StringBuilder().append("[").append(StringUtils.join(targetType.getEnumConstants(), ", ")).append("]").toString();
        return String.format(RestExceptionMessageCode.ENUM_FORMAT_ERROR.getMessage(), validOptions);
    }

    protected static String getRequestBodyMessage(Class<?> targetType, List<JsonMappingException.Reference> pathList) {

        final String fieldName = (pathList.get(pathList.size() - 1)).getFieldName();
        return String.format(RestExceptionMessageCode.BAD_FORMAT_ERROR.getMessage(), fieldName, targetType);
    }

    protected static List<Message> getFieldErrorsAsMessageArray(Exception ex, Collection<FieldError> fieldErrors) {

        final List<Message> messages = new ArrayList<>();
        final Iterator iter = fieldErrors.iterator();

        while (iter.hasNext()) {
            final FieldError fieldError = (FieldError) iter.next();
            if (ex instanceof BindException) {
                final Class<?> clazz = ((BindException) ex).getFieldType(fieldError.getField());
                if (Objects.nonNull(clazz) && clazz.isEnum()) {
                    final String formattedMessage = getEnumMessage(clazz);
                    messages.add(new Message(Severity.ERROR, RestExceptionMessageCode.ENUM_FORMAT_ERROR, formattedMessage));
                } else {
                    messages.add(new Message(Severity.ERROR, getFieldErrorCode(fieldError.getCode()), null, fieldError.getField() + " " + fieldError.getDefaultMessage()));
                }
            } else {
                messages.add(new Message(Severity.ERROR, getFieldErrorCode(fieldError.getCode()), null, fieldError.getField() + " " + fieldError.getDefaultMessage()));
            }
        }

        return messages;
    }

    protected static String getFieldErrorCode(String code) {

        return RestExceptionMessageCode.DEFAULT_VALIDATION_ERROR.getCode() + (StringUtils.isNotBlank(code) ? "." + code : "");
    }

    protected static String decodePath(WebRequest request) {

        try {
            return URLDecoder.decode(((ServletWebRequest) request).getRequest().getRequestURI(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException ex) {
            log.error(ex.getMessage());
            return "";
        }
    }

    protected static ResponseEntity<Object> createGenericResponseEntityForError(HttpStatus httpCode, Throwable ex) {

        return createGenericResponseEntityForError(httpCode, ex, null, null, null, null);
    }

    protected static ResponseEntity<Object> createGenericResponseEntityForError(HttpStatus httpCode, Throwable ex, MessageKey exceptionMessageKey, String exceptionMessage, List<Message> additionalMessages) {

        log.error(ex.getClass().getName(), ex);
        final ErrorResponse response = createErrorResponseForResponseEntity(ex, exceptionMessageKey.getCode(), exceptionMessageKey.getKey(), exceptionMessage, additionalMessages);
        return new ResponseEntity<>(response, httpCode);
    }

    protected static ResponseEntity<Object> createGenericResponseEntityForError(HttpStatus httpCode, Throwable ex, String exceptionCode, String exceptionKey, String exceptionMessage, List<Message> additionalMessages) {

        log.error(ex.getClass().getName(), ex);
        final ErrorResponse response = createErrorResponseForResponseEntity(ex, exceptionCode, exceptionKey, exceptionMessage, additionalMessages);
        return new ResponseEntity<>(response, httpCode);
    }

    protected static ResponseEntity<ErrorResponse> createResponseEntityForError(HttpStatus httpCode, Throwable ex) {

        return createResponseEntityForError(httpCode, ex, null, null, null, null);
    }

    protected static ResponseEntity<ErrorResponse> createResponseEntityForError(HttpStatus httpCode, Throwable ex, String exceptionCode, String exceptionKey, String exceptionMessage, List<Message> additionalMessages) {

        log.error(ex.getClass().getName(), ex);
        final ErrorResponse response = createErrorResponseForResponseEntity(ex, exceptionCode, exceptionKey, exceptionMessage, additionalMessages);
        return new ResponseEntity<>(response, httpCode);
    }

    protected static ErrorResponse createErrorResponseForResponseEntity(Throwable ex, String exceptionCode, String exceptionKey, String exceptionMessage, List<Message> additionalMessages) {

        ErrorResponse response = null;

        if (StringUtils.isNotBlank(exceptionCode)) {
            response = RestExceptionUtils.createErrorResponse(exceptionCode, exceptionKey, exceptionMessage, additionalMessages);
        } else if (ex instanceof AxialException) {
            final AxialException baseEx = (AxialException) ex;
            response = RestExceptionUtils.createErrorResponse(baseEx.getCode(), baseEx.getKey(), baseEx.getMessage(), baseEx.getAdditionalMessages());
        } else {
            response = RestExceptionUtils.createErrorResponse(RestExceptionMessageCode.UNKNOWN_EXCEPTION.getCode(), RestExceptionMessageCode.UNKNOWN_EXCEPTION.getKey(), RestExceptionMessageCode.UNKNOWN_EXCEPTION.getMessage(), additionalMessages);
        }

        return response;
    }

    private static ErrorResponse createErrorResponse(String code, String key, String message, List<Message> additionalMessages) {

        final ErrorResponse response = new ErrorResponse();
        response.setCode(code);
        response.setKey(key);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setAdditionalMessages(additionalMessages);
        return response;
    }

}