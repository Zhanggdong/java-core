package com.guopa.edu.movie02;

/**
 * 租赁
 * Created by ThinkPad on 2018/1/2.
 */
public class Rental {
    private Movie _movie;
    private int _rentalDays;

    public Rental(Movie _movie, int _rentalDays) {
        this._movie = _movie;
        this._rentalDays = _rentalDays;
    }

    public Movie getMovie() {
        return _movie;
    }

    public void setMovie(Movie _movie) {
        this._movie = _movie;
    }

    public int getRentalDays() {
        return _rentalDays;
    }

    public void setRentalDays(int _rentalDays) {
        this._rentalDays = _rentalDays;
    }

    public double getCharge() {
        return _movie.getCharge(getRentalDays());
    }

    public int getFrequentRenterPoints(){
        if (getMovie().getPriceCode()== Movie.NEW_RELEASE&&getRentalDays()>1){
            return  2;
        }else {
            return 1;
        }
    }
}
