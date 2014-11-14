package com.kkk.rest.exception;


public enum KErrorCode implements ErrorCode {
    UNKNOWN_ERROR("System-00001", "unknown error"),
    RECORD_ALREADY_EXIST("System-002", "The record already exists"),
    ERROR_GETTING_DATA_FROM_DB("System-005", "Error occured getting data from database");

    private String code;
    private String message;
    private String localizationKey;

    private KErrorCode(String code, String message, String localizationKey){
        this.code = code;
        this.message = message;
        this.localizationKey = localizationKey;
    }

    private KErrorCode(String code, String message){
        this(code, message, null);
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    public String getLocalizationKey() {
        return localizationKey;
    }
}

