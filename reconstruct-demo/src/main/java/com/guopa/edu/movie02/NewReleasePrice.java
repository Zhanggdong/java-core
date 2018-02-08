package com.guopa.edu.movie02;

/**
 * Created by ThinkPad on 2018/1/3.
 */
public class NewReleasePrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    public double getCharge(int daysRented){
        return daysRented*3;
    }

    public int getFrequentRenterPoints(int daysRented){
        return (daysRented>1)?2:1;
    }
}
