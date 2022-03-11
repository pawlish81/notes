package com.pwldata.config;

import com.pwl.rocket_sim.api.v1.model.ApiError;
import com.pwldata.exceptions.RocketNotFoundException;
import com.pwldata.exceptions.RocketValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.error(ex.getLocalizedMessage());

        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();

        final ApiError apiError = new ApiError().status(HttpStatus.BAD_REQUEST.value()).errors(List.of(error)).message(ex.getLocalizedMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.error(ex.getLocalizedMessage());

        logger.error("error", ex);
        final ApiError apiError = new ApiError().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).errors(List.of("error occurred")).message(ex.getLocalizedMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({RocketNotFoundException.class, RocketValidationException.class})
    public ResponseEntity<Object> handleNoteNotFoundException(final RuntimeException ex, final WebRequest request) {
        logger.error(ex.getLocalizedMessage());
        logger.error("error", ex);
        final ApiError apiError = new ApiError().status(HttpStatus.BAD_REQUEST.value()).errors(List.of(ex.getMessage())).message(ex.getLocalizedMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}