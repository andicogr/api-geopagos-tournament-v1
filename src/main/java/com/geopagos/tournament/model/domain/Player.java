package com.geopagos.tournament.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Player {

    private String id;
    private String name;
    private Gender gender;
    private Integer ability;
    private Integer strength;
    private Integer speed;
    private Integer reactionTime;

    public double getAverageStatistics(List<TournamentAttributes> attributes) {
        double count = 1.0;
        double total = this.ability;
        for (TournamentAttributes attribute: attributes) {
            if (TournamentAttributes.REACTION_TIME == attribute) {
                total += this.reactionTime;
                count++;
            } else if (TournamentAttributes.SPEED == attribute) {
                total += this.speed;
                count++;
            } else if (TournamentAttributes.STRENGTH == attribute) {
                total += this.strength;
                count++;
            }
        }

        return total / count;

    }

}
