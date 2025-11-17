package com.ridesession.lld.trip;

import com.ridesession.lld.rider.Rider;
import com.ridesession.lld.driver.Driver;
import com.ridesession.lld.location.Location;
import com.ridesession.lld.common.TripStatus;
import com.ridesession.lld.common.IdGenerator;
import com.ridesession.lld.pricing.PricingStrategy;
import com.ridesession.lld.strategy.DriverMatchingStrategy;

public class Trip {

    private Rider rider;
    private Driver driver;
    private Location srcLoc;
    private Location dstLoc;
    private TripStatus status;
    private int tripId;
    private double price;

    private PricingStrategy pricingStrategy;
    private DriverMatchingStrategy driverMatchingStrategy;

    public Trip(Rider rider,
                Driver driver,
                Location srcLoc,
                Location dstLoc,
                double price,
                PricingStrategy pricingStrategy,
                DriverMatchingStrategy driverMatchingStrategy) {

        this.rider = rider;
        this.driver = driver;
        this.srcLoc = srcLoc;
        this.dstLoc = dstLoc;
        this.price = price;

        this.pricingStrategy = pricingStrategy;
        this.driverMatchingStrategy = driverMatchingStrategy;

        this.status = TripStatus.DRIVER_ON_THE_WAY;
        this.tripId = IdGenerator.getNextTripId();
    }

    public int getTripId() {
        return tripId;
    }

    public void displayTripDetails() {
        System.out.println("\nTrip ID: " + tripId);
        System.out.println("Rider: " + rider.getRiderName());
        System.out.println("Driver: " + driver.getDriverName());
        System.out.println("Price: " + price);
        System.out.println("Locations: " +
                srcLoc.getLatitude() + "," + srcLoc.getLongitude() +
                " → " +
                dstLoc.getLatitude() + "," + dstLoc.getLongitude());
    }
}
