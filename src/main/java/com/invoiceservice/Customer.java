package com.invoiceservice;

/*@Description:- class represents a customer who have done multiple rides and invoice summary for
that rides
@instance variable:- user id, rides array for multiple rides and invoice summary*/
public class Customer {
    private Ride[] rides;
    private int userId;
    private InvoiceSummary invoiceSummary;

    public Customer(int userId, Ride[] rides) {
        this.rides = rides;
        this.userId = userId;
        this.invoiceSummary = null;
    }

    public Ride[] getRides() {
        return rides;
    }

    public void setRides(Ride[] rides) {
        this.rides = rides;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public InvoiceSummary getInvoiceSummary() {
        return invoiceSummary;
    }

    public void setInvoiceSummary(double totalFare) {
        this.invoiceSummary = new InvoiceSummary(rides.length, totalFare);
    }
}
