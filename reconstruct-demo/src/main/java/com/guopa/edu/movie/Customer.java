package com.guopa.edu.movie;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客租赁影片:
 * Created by ThinkPad on 2018/1/2.
 */
public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Vector getRentals() {
        return _rentals;
    }

    public void setRentals(Vector _rentals) {
        this._rentals = _rentals;
    }

    /**
     * 这里的代码应需要重构
     * @return
     */
    public String statement(){
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental record for "+ getName()+"\n";
        while (rentals.hasMoreElements()){
            double thisAcount = 0;
            Rental each = (Rental) rentals.nextElement();
            switch (each.getMovie().getPriceCode()){
                case Movie.REGULAR:
                    thisAcount += 2;
                    if (each.getRentalDays()>2){
                        thisAcount +=(each.getRentalDays()-2)*1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAcount += each.getRentalDays()*3;
                    break;
                case Movie.CHILDRENS:
                    thisAcount +=1.5;
                    if (each.getRentalDays()>3){
                        thisAcount +=(each.getRentalDays()-3)*1.5;
                    }
                    break;
            }
            frequentRenterPoints ++;
            if (each.getMovie().getPriceCode()==Movie.NEW_RELEASE&&each.getRentalDays()>1){
                frequentRenterPoints ++;
            }
            result += "\t"+each.getMovie().getTitle()+"\t"+String.valueOf(thisAcount)+"\n";
            totalAmount = thisAcount;
        }
        result += "Amount owed is "+String.valueOf(totalAmount)+"\n";
        result += "You earned is "+String.valueOf(frequentRenterPoints)+"frequent renter points";
        return  result;
    }
}
