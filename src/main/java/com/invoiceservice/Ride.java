package com.invoiceservice;

/*@Description:- it represents a single ride which has distance and time given by user and on the
 * basis of these total fare for the ride will be calculated
 * @instance variable:- distance and time in minutes*/
public class Ride {
    public double distance;
    public int time;

    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
    }
}
