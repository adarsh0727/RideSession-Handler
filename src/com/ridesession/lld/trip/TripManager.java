package com.ridesession.lld.trip;

import com.ridesession.lld.driver.Driver;
import com.ridesession.lld.driver.DriverManager;
import com.ridesession.lld.rider.Rider;
import com.ridesession.lld.rider.RiderManager;
import com.ridesession.lld.location.Location;
import com.ridesession.lld.strategy.StrategyManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TripManager {

    private static volatile TripManager instance;

    private RiderManager riderManager;
    private DriverManager driverManager;

    private Map<Integer, TripMetaData> tripsMetaDataInfo;
    private Map<Integer, Trip> tripsInfo;

    private TripManager() {
        riderManager = RiderManager.getInstance();
        driverManager = DriverManager.getInstance();

        tripsMetaDataInfo = new ConcurrentHashMap<>();
        tripsInfo = new ConcurrentHashMap<>();
    }

    public static TripManager getInstance() {
        if (instance == null) {
            synchronized (TripManager.class) {
                if (instance == null) {
                    instance = new TripManager();
                }
            }
        }
        return instance;
    }

    public void createTrip(Rider rider, Location srcLoc, Location dstLoc) {

        StrategyManager strategyManager = StrategyManager.getInstance();

        Driver driver = strategyManager.getDriverMatchingStrategy()
                .findDriver(rider, driverManager.getDriversMap().values().stream().toList());

        double price = strategyManager.getPricingStrategy().calculatePrice(
                new TripMetaData(srcLoc, dstLoc, rider.getRating())
        );

        Trip trip = new Trip(
                rider,
                driver,
                srcLoc,
                dstLoc,
                price,
                strategyManager.getPricingStrategy(),
                strategyManager.getDriverMatchingStrategy()
        );

        tripsInfo.put(trip.getTripId(), trip);

        TripMetaData meta = new TripMetaData(srcLoc, dstLoc, rider.getRating());
        meta.setDriverRating(driver.getRating());

        tripsMetaDataInfo.put(trip.getTripId(), meta);

        trip.displayTripDetails();
    }

    public Map<Integer, Trip> getTripsMap() {
        return tripsInfo;
    }
}
