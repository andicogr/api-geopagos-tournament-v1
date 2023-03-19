package com.geopagos.tournament.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordNotFoundException extends RuntimeException {

    private final String code;
    private final String description;

    public RecordNotFoundException() {
        this.code = "00090";
        this.description = "The record was not found";
    }

}
