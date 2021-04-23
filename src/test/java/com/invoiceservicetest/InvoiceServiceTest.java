package com.invoiceservicetest;

import com.invoiceservice.InvoiceGenerator;
import com.invoiceservice.InvoiceSummary;
import com.invoiceservice.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator = null;
    Ride[] rides = null;

    @Before
    public void setUp() throws Exception {
        invoiceGenerator = new InvoiceGenerator();
        rides = new Ride[]{new Ride(2.0, 5),
                new Ride(0.1, 1)};
        invoiceGenerator.addCustomer(1, rides);
        invoiceGenerator.addCustomer(2, rides);
    }

    @Test
    public void givenCustomer_WhenAddedToCustomerList_ShouldReturnIncreasedSize() {
        Ride[] allRides = {new Ride(5, 10)};
        int size = invoiceGenerator.addCustomer(3, allRides);
        Assert.assertEquals(3, size);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double fare = invoiceGenerator.calculateFareForNormalRides(rides[0]);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double fare = invoiceGenerator.calculateFareForNormalRides(rides[1]);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(1, "premium");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(rides.length, 60);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenWrongRideType_WhenCalculatedFare_ShouldThrowException() {
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(1, "royal");
        boolean result = invoiceSummary == null;
        Assert.assertTrue(result);
    }

    @Test
    public void givenUserId_WhenQueriedAboutMultipleRidesInvoice_ShouldReturnInvoiceSummaryForGivenUserId() {
        InvoiceSummary expectedInvoiceSummary = invoiceGenerator.calculateFare(2, "premium");
        InvoiceSummary actualInvoiceSummary = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummary, actualInvoiceSummary);
    }
}
