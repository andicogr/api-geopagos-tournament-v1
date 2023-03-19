package com.geopagos.tournament.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerException extends RuntimeException {

    private final String code;
    private final String description;

    public PlayerException(String description) {
        this.code = "00010";
        this.description = description;
    }

}
