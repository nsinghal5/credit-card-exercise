package com.sapient.credit.card.exercise.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private List<String> details;

    public ErrorDetails(Date timestamp, String message, List<String> details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
