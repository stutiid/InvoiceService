package com.invoiceservice;

/*@Description:- class represents a invoice summary which is generated for multiple rides customer
 * has taken and its total fare and average cost for these rides
 * @instance variable:- no of rides, total and average fare for those rides*/
public class InvoiceSummary {
    private final int noOfRides;
    private final double totalFare;
    private final double averageFare;

    public InvoiceSummary(int noOfRides, double totalFare) {
        this.noOfRides = noOfRides;
        this.totalFare = totalFare;
        this.averageFare = this.totalFare / noOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceSummary)) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return noOfRides == that.noOfRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFare, averageFare) == 0;
    }

}
