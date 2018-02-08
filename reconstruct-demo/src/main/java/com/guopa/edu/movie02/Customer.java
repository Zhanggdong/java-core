package com.guopa.edu.movie02;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客租赁影片
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


    public String statement(){
        Enumeration rentals = _rentals.elements();
        String result = "Rental record for "+ getName()+"\n";
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += "\t"+each.getMovie().getTitle()+"\t"+String.valueOf(each.getCharge())+"\n";
        }
        result += "Amount owed is "+String.valueOf(getTotalCharge())+"\n";
        result += "You earned is "+String.valueOf(getTotalFrequentRenterPoints())+"frequent renter points";
        return  result;
    }

    private double acountFor(Rental aRental) {
        return aRental.getCharge();
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result +=each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result +=each.getFrequentRenterPoints();
        }
        return result;
    }

    public static void main(String[] args) throws IllegalAccessException {
        Customer customer = new Customer("张三");
        Movie movie = new Movie("让子弹飞",2);
        Rental rental = new Rental(movie,2);
        customer._rentals.add(rental);
        System.out.println(customer.statement());
    }
}
