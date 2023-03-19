package com.geopagos.tournament.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidParticipantsGenderException extends RuntimeException {

    private final String code;
    private final String description;

    public InvalidParticipantsGenderException() {
        this.code = "00050";
        this.description = "The tournament participants gender must be the same";

    }

}
