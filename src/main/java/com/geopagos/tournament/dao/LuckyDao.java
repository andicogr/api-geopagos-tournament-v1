package com.geopagos.tournament.dao;

import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class LuckyDao {

    public double getLucky() {
        Random r = new Random();
        int low = 1;
        int high = 50;
        return 1 + ((r.nextInt(high-low) + (double) low) / 100);
    }
}
