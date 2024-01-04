package com.viettel.etc.utils.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;
import java.util.function.Supplier;

/**
 * lop mo rong exception
 *
 * @author datnv5
 */
public class EtcException extends RuntimeException implements Supplier<EtcException> {
    private Integer codeError;
    private String error;
    @JsonIgnore
    Integer httpCode;
    Map<String, String> errorMapKey;

    public EtcException(String message) {
        super(message);
    }

    public EtcException(Integer codeError, String message) {
        super(message);
        this.codeError = codeError;
    }

    public EtcException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMapKey = errorMap;
    }

    public EtcException(String error, String message, Integer httpCode) {
        super(message);
        this.error = error;
        this.httpCode = httpCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Integer getCodeError() {
        return codeError;
    }

    public String getError() {
        return error;
    }

    public Map<String, String> getErrorMapKey() {
        return errorMapKey;
    }

    @Override
    public EtcException get() {
        return this;
    }
}
