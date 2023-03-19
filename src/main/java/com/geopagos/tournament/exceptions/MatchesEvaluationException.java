package com.geopagos.tournament.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchesEvaluationException extends RuntimeException {

    private final String code;
    private final String description;

    public MatchesEvaluationException(String description) {
        this.code = "00020";
        this.description = description;
    }

}

