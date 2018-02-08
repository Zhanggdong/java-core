package com.guopa.edu.movie02;

/**
 * Created by ThinkPad on 2018/1/3.
 */
public class RegularPrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    public double getCharge(int daysRented){
        double result = 2;
        if (daysRented >2){
            result +=(daysRented-2)*1.5;
        }
        return result;
    }
}
