package com.geopagos.tournament.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidTournamentParticipantException extends RuntimeException {

    private final String code;
    private final String description;

    public InvalidTournamentParticipantException(Integer participantQuantity) {
        this.code = "00010";
        this.description = "The tournament participants must be power of two, the current Player participants are " + participantQuantity;
    }

}
