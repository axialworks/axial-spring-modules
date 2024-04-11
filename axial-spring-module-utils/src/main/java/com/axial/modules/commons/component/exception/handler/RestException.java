package com.axial.modules.commons.component.exception.handler;

import com.axial.modules.commons.component.exception.type.AuthException;
import com.axial.modules.commons.component.exception.type.DataNotFoundException;
import com.axial.modules.commons.component.exception.type.GenericException;
import com.axial.modules.commons.component.exception.type.ValidationException;
import com.axial.modules.commons.model.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class RestException extends RestExceptionCustomizer {

    @ExceptionHandler({ Throwable.class })
    public static ResponseEntity<ErrorResponse> handleUnknownException(Throwable ex, WebRequest request) {

        return RestExceptionUtils.createResponseEntityForError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler({ AuthException.class })
    public final ResponseEntity<ErrorResponse> handleAuthException(AuthException ex, WebRequest request) {

        return RestExceptionUtils.createResponseEntityForError(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler({ ValidationException.class })
    public final ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, WebRequest request) {

        return RestExceptionUtils.createResponseEntityForError(HttpStatus.PRECONDITION_FAILED, ex);
    }

    @ExceptionHandler({ DataNotFoundException.class })
    public final ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {

        return RestExceptionUtils.createResponseEntityForError(HttpStatus.NO_CONTENT, ex);
    }

    @ExceptionHandler({ GenericException.class })
    public final ResponseEntity<ErrorResponse> handleGenericException(GenericException ex, WebRequest request) {

        return RestExceptionUtils.createResponseEntityForError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

}