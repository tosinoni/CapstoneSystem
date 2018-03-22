package com.carleton.CapstoneSystem.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailDTO {
    @JsonProperty
    private String recipients;

    @JsonProperty
    private String content;
    @JsonProperty
    private String subject;
    @JsonProperty
    private String from;

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
