package com.guopa.edu.movie02;

/**
 * 影片类：普通对象
 * Created by ThinkPad on 2018/1/2.
 */
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private Price _price;

    public Movie(String title, int priceCode) throws IllegalAccessException {
        this._title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public int getPriceCode() {
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) throws IllegalAccessException {
        switch (arg){
            case REGULAR:
                _price = new RegularPrice();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalAccessException("Incorrect Price Code");
        }
    }

    public double getCharge(int daysRented){
        double result = _price.getCharge(daysRented);
        return result;
    }

    public int getFrequentRenterPoints(int daysRented){
        return _price.getFrequentRenterPoints(daysRented);
    }
}
