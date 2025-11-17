package com.ridesession.lld.strategy;

import com.ridesession.lld.driver.Driver;
import com.ridesession.lld.driver.DriverManager;
import com.ridesession.lld.trip.TripMetaData;

import java.util.Map;

public class LeastTimeBasedMatchingStrategy implements DriverMatchingStrategy {

    @Override
    public Driver matchDriver(TripMetaData metaData) {

        DriverManager driverManager = DriverManager.getInstance();
        Map<String, Driver> drivers = driverManager.getDriversMap();

        if (drivers.isEmpty()) {
            System.out.println("No drivers! What service is this huh?");
            return null;
        }

        System.out.println("Using quadtree logic to find nearest cab (simplified demo).");

        // Pick first driver (just like your C++ version)
        Driver driver = drivers.values().iterator().next();

        System.out.println("Setting " + driver.getDriverName() + " as driver");

        metaData.setDriverRating(driver.getRating());
        return driver;
    }
}
