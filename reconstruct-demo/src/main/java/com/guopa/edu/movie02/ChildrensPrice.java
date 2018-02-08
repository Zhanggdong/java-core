package com.guopa.edu.movie02;

/**
 * Created by ThinkPad on 2018/1/3.
 */
public class ChildrensPrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    public double getCharge(int daysRented){
        double result = 1.5;
        if (daysRented>3){
            result +=(daysRented-3)*1.5;
        }
        return result;
    }
}
