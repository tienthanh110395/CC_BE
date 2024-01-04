package com.viettel.etc.utils.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.viettel.etc.services.JedisCacheService;
import com.viettel.etc.utils.Constants;
import com.viettel.etc.utils.FnCommon;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    JedisCacheService jedisCacheService;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidateException(ConstraintViolationException e) {
        LOG.error("Has ERROR", e);
        String mess = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).findFirst().orElse("");
        return new ResponseEntity<>(FnCommon.responseToClient(HttpStatus.BAD_REQUEST.value(), mess), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest req, Authentication authentication) {
        LOG.error("Has Access is denied ERROR user: {} in: {}", FnCommon.getUserLogin(authentication), req.getRequestURI());
        String mess = jedisCacheService.getMessageErrorByKey("access.denied");
        return new ResponseEntity<>(FnCommon.responseToClient(HttpStatus.FORBIDDEN.value(), mess), HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error("Has ERROR MethodArgumentNotValidException with message = {}", ex.getMessage());
        String mess = "";
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            mess = error.getDefaultMessage();
            switch (Objects.requireNonNull(error.getCode())) {
                case "NotBlank":
                case "NotEmpty":
                case "NotNull":
                    mess = String.format(jedisCacheService.getMessageErrorByKey("common.validate.not.null"), FnCommon.getErrorField(error.getField()));
                    break;
                case "Pattern":
                    mess = String.format(jedisCacheService.getMessageErrorByKey("common.validate.type.invalid"), FnCommon.getErrorField(error.getField()));
            }
        }
        return new ResponseEntity<>(FnCommon.responseToClient(HttpStatus.BAD_REQUEST.value(), mess), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error("Has ERROR HttpMessageNotReadableException with message = {}", ex.getMessage());
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) ex.getCause();
            for (JsonMappingException.Reference path : cause.getPath()) {
                String mess = path.getFieldName() + ": Invalid format";
                return new ResponseEntity<>(FnCommon.responseToClient(HttpStatus.BAD_REQUEST.value(), mess), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(FnCommon.responseToClient(jedisCacheService.getCodeErrorByKey("crm.internal.server"), jedisCacheService.getMessageErrorByKey("crm.internal.server")), HttpStatus.OK);
    }

    @ExceptionHandler(EtcException.class)
    public ResponseEntity<Object> handleETCException(EtcException ex) {
        if (!FnCommon.isNullOrEmpty(ex.getStackTrace())) {
            LOG.error("Has ERROR EtcException with code = {}, message = {}, at = {}",
                    jedisCacheService.getCodeErrorByKey(ex.getMessage()), ex.getMessage(), ex.getStackTrace()[0].toString());
        } else {
            LOG.error("Has ERROR EtcException with code = {}, message = {}", jedisCacheService.getCodeErrorByKey(ex.getMessage()), ex.getMessage());
        }
        return new ResponseEntity<>(FnCommon.responseToClient(jedisCacheService.getCodeErrorByKey(ex.getMessage()), jedisCacheService.getMessageErrorByKey(ex.getMessage())), HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        LOG.error("Has ERROR", ex);
        return new ResponseEntity<>(FnCommon.responseToClient(jedisCacheService.getCodeErrorByKey("crm.internal.server"), jedisCacheService.getMessageErrorByKey("crm.internal.server")), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Missing parameter");
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error("No handler found exception", ex);
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error("Handle type mismatch", ex);
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)   //(1)
    public Object exceptionHandler(IOException e, HttpServletRequest request) {
        if (StringUtils.containsIgnoreCase(ExceptionUtils.getRootCauseMessage(e), "Broken pipe")) {   //(2)
            return null;        //(2)	socket is closed, cannot return any response
        } else {
            return new HttpEntity<>(e.getMessage());  //(3)
        }
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new EtcException("crm.boo.method.not_allowed"));
    }
}
