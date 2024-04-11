package com.axial.modules.commons.component.exception;

import com.axial.modules.commons.component.exception.definition.AxialException;
import com.axial.modules.commons.component.exception.type.AuthException;
import com.axial.modules.commons.component.exception.type.DataNotFoundException;
import com.axial.modules.commons.component.exception.type.GenericException;
import com.axial.modules.commons.component.exception.type.ValidationException;
import com.axial.modules.commons.core.message.CommonMessageUtils;
import com.axial.modules.commons.model.Message;
import com.axial.modules.commons.outline.message_key.MessageKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionHelper {


    public static AuthException newAuthException(String key, Object... args) {

        return newException(AuthException.class, key, args);
    }

    public static ValidationException newValidationException(String key, Object... args) {

        return newException(ValidationException.class, key, args);
    }

    public static GenericException newGenericException(String key, Object... args) {

        return newException(GenericException.class, key, args);
    }

    public static DataNotFoundException newDataNotFoundException(String key, Object... args) {

        return newException(DataNotFoundException.class, key, args);
    }


    public static AuthException newAuthException(String key, Locale locale, Object... args) {

        return newException(AuthException.class, key, locale, args);
    }

    public static ValidationException newValidationException(String key, Locale locale, Object... args) {

        return newException(ValidationException.class, key, locale, args);
    }

    public static GenericException newGenericException(String key, Locale locale, Object... args) {

        return newException(GenericException.class, key, locale, args);
    }

    public static DataNotFoundException newDataNotFoundException(String key, Locale locale, Object... args) {

        return newException(DataNotFoundException.class, key, locale, args);
    }


    public static AuthException newAuthException(MessageKey messageKey, Object... args) {

        return newException(AuthException.class, messageKey, args);
    }

    public static ValidationException newValidationException(MessageKey messageKey, Object... args) {

        return newException(ValidationException.class, messageKey, args);
    }

    public static GenericException newGenericException(MessageKey messageKey, Object... args) {

        return newException(GenericException.class, messageKey, args);
    }

    public static DataNotFoundException newDataNotFoundException(MessageKey messageKey, Object... args) {

        return newException(DataNotFoundException.class, messageKey, args);
    }


    public static AuthException newAuthException(MessageKey messageKey, Locale locale, Object... args) {

        return newException(AuthException.class, messageKey, locale, args);
    }

    public static ValidationException newValidationException(MessageKey messageKey, Locale locale, Object... args) {

        return newException(ValidationException.class, messageKey, locale, args);
    }

    public static GenericException newGenericException(MessageKey messageKey, Locale locale, Object... args) {

        return newException(GenericException.class, messageKey, locale, args);
    }

    public static DataNotFoundException newDataNotFoundException(MessageKey messageKey, Locale locale, Object... args) {

        return newException(DataNotFoundException.class, messageKey, locale, args);
    }


    private static <T extends AxialException> T newException(Class<T> exceptionType, String key, Object... args) {

        return newException(exceptionType, key, CommonMessageUtils.getLocale(), args);
    }

    private static <T extends AxialException> T newException(Class<T> exceptionType, String key, Locale locale, Object... args) {

        final String message = CommonMessageUtils.getMessage(key, locale, args);
        return newException(exceptionType, key, message);
    }


    private static <T extends AxialException> T newException(Class<T> exceptionType, MessageKey messageKey, Object... args) {

        return newException(exceptionType, messageKey, CommonMessageUtils.getLocale(), args);
    }

    private static <T extends AxialException> T newException(Class<T> exceptionType, MessageKey messageKey, Locale locale, Object... args) {

        final String message = CommonMessageUtils.getMessage(messageKey.getKey(), locale, args);
        return newException(exceptionType, messageKey.getCode(), messageKey.getKey(), message);
    }


    private static <T extends AxialException> T newException(Class<T> exceptionType, String code, String key, String message) {

        if (AuthException.class.equals(exceptionType)) {
            return (T) new AuthException(code, key, message, new Message[0]);
        }

        if (ValidationException.class.equals(exceptionType)) {
            return (T) new ValidationException(code, key, message, new Message[0]);
        }

        if (GenericException.class.equals(exceptionType)) {
            return (T) new GenericException(code, key, message, new Message[0]);
        }

        if (DataNotFoundException.class.equals(exceptionType)) {
            return (T) new DataNotFoundException(code, key, message, new Message[0]);
        }

        throw new UnsupportedOperationException(RestExceptionMessageCode.UNKNOWN_EXCEPTION_TYPE.getMessage());
    }

}