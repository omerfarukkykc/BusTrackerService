package com.lepric.btservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.ALREADY_REPORTED)
public class ResourceAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String resourceName;    
    private String fieldName;    
    private Object fieldValue;   
    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName + " not found with " + fieldName + " : " + fieldValue);
        this.setResourceName(resourceName);
        this.setFieldName(fieldName);
        this.setFieldValue(fieldValue);
    }
    public Object getFieldValue() {
        return fieldValue;
    }
    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    } 
}