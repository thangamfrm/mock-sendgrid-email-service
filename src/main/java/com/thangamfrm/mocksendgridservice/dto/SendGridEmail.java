package com.thangamfrm.mocksendgridservice.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class SendGridEmail {

    @JsonProperty(value = "to")
    private String to;

    @JsonProperty(value = "toname")
    private String toName;

    @JsonProperty(value = "x-smtpapi")
    private String xSmtpApi;

    @JsonProperty(value = "subject")
    private String subject;

    @JsonProperty(value = "text")
    private String text;

    @JsonProperty(value = "html")
    private String html;

    @JsonProperty(value = "from")
    private String from;

    @JsonProperty(value = "bcc")
    private String bcc;

    @JsonProperty(value = "fromname")
    private String fromName;

    @JsonProperty(value = "replyto")
    private String replyTo;

    @JsonProperty(value = "date")
    private String date;

    @JsonProperty(value = "files")
    private String files;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "headers")
    private String headers;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getxSmtpApi() {
        return xSmtpApi;
    }

    public void setxSmtpApi(String xSmtpApi) {
        this.xSmtpApi = xSmtpApi;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

}
