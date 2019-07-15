package com.thangamfrm.mocksendgridservice.dto;

public class SendGridEmailResponse {

    private String message;

    private String[] errors;

    public SendGridEmailResponse() {
        super();
    }

    public SendGridEmailResponse(String message, String errorMsg) {
        this.errors = new String[] { errorMsg };
        this.message = message;
    }

    public SendGridEmailResponse(String message) {
        this.errors = new String[] {};
        this.message = message;
    }

    public SendGridEmailResponse(String message, String[] errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

}
