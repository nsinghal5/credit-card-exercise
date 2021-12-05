package com.sapient.credit.card.exercise.model;

public enum Status {
    SUCCESS("success"),
    FAIL("fail");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
