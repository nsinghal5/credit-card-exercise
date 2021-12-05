package com.sapient.credit.card.exercise.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class ServiceResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timeStamp;
    private Status status;

    public ServiceResponse() {
        timeStamp = new Date();
    }

    public ServiceResponse(Status status){
        this();
        this.status = status;
    }
}
