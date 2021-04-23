package com.invoiceservice;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/*@Description:- to generate a invoice this class is created and have minimum rates applied per kilometer
 * and per minute for normal and premium rides and also have minimum fare which will be applied if total
 * fare is less
 * @instance variable:- minimum cost per kilometer and per time and minimum fare for both normal and premium
 * customer and hash map for customer containing customer list */
public class InvoiceGenerator {
    private static final double NORMAL_MINIMUM_COST_PER_KILOMETER = 10;
    private static final double NORMAL_COST_PER_TIME = 1;
    private static final double NORMAL_MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 15;
    private static final double PREMIUM_COST_PER_TIME = 2;
    private static final double PREMIUM_MINIMUM_FARE = 20;
    public Map<Integer, Customer> customerList = new HashMap<>();

    /*@Description:- add customer to the customer list so later their total fare can be calculated for
     * that customer id is taken to uniquely identify each customer and all rides information he is going
     * to take
     * @param:- user id of customer and multiple rides information
     * @return int:- increased size of the list after adding the customer to it  */
    public int addCustomer(int userId, Ride[] rides) {
        Customer customer = new Customer(userId, rides);
        customerList.put(userId, customer);
        return customerList.size();
    }

    /*@Description:- calculate the total fare for a given ride and apply charge according to normal
     * ride and also check if total fare is less than minimum fare then return minimum fare
     * @param:- ride object that have details of distance and time
     * @return double:- return maximum of total fare and minimum fare for normal rides*/
    public double calculateFareForNormalRides(Ride ride) {
        double totalFare = ride.distance * NORMAL_MINIMUM_COST_PER_KILOMETER + ride.time * NORMAL_COST_PER_TIME;
        return Math.max(totalFare, NORMAL_MINIMUM_FARE);
    }

    /*@Description:- calculate the total fare for a given ride and apply charge according to premium
     * ride and also check if total fare is less than minimum fare then return minimum fare
     * @param:- ride object that have details of distance and time
     * @return double:- return maximum of total fare and minimum fare for premium rides*/
    public double calculateFareForPremiumRides(Ride ride) {
        double totalFare = ride.distance * PREMIUM_MINIMUM_COST_PER_KILOMETER + ride.time * PREMIUM_COST_PER_TIME;
        return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
    }

    /*@Description:- get the total fare according the type of ride for the given user id and check if
     * given user id is there in the customer list or not and lastly return the invoice summary for all
     * the rides, here ride type is also checked if not correct then return null invoice and if given
     * user id not exists then also return null invoice and throw exception
     * @param:- user id and type of ride to apply charges according to it
     * @return InvoiceSummary:- return invoice summary for total rides for given user id */
    public InvoiceSummary calculateFare(int userId, String rideType) {
        double totalFare = 0.0;
        try {
            if (customerList.containsKey(userId)) {
                if (rideType.equalsIgnoreCase("Premium")) {
                    for (Ride ride : customerList.get(userId).getRides())
                        totalFare += this.calculateFareForPremiumRides(ride);
                } else if (rideType.equalsIgnoreCase("Normal")) {
                    for (Ride ride : customerList.get(userId).getRides())
                        totalFare += this.calculateFareForNormalRides(ride);
                } else
                    throw new InputMismatchException();
                customerList.get(userId).setInvoiceSummary(totalFare);
                return customerList.get(userId).getInvoiceSummary();
            } else
                throw new NullPointerException();
        } catch (InputMismatchException e) {
            System.out.println("given user id customer not exist");
        } catch (NullPointerException e) {
            System.out.println("given user id customer not exist");
        }
        return null;
    }
}
