package com.ridesession.lld.trip;

import com.ridesession.lld.driver.Driver;
import com.ridesession.lld.driver.DriverManager;
import com.ridesession.lld.rider.Rider;
import com.ridesession.lld.rider.RiderManager;
import com.ridesession.lld.location.Location;
import com.ridesession.lld.strategy.StrategyManager;
import com.ridesession.lld.pricing.PricingStrategy;
import com.ridesession.lld.strategy.DriverMatchingStrategy;

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

        // -------- 1. Build metadata (needed for strategy selection) --------
        TripMetaData metaData = new TripMetaData(srcLoc, dstLoc, rider.getRating());

        // -------- 2. Select correct pricing + matching strategies --------
        PricingStrategy pricingStrategy =
                strategyManager.determinePricingStrategy(metaData);

        DriverMatchingStrategy matchingStrategy =
                strategyManager.determineMatchingStrategy(metaData);

        // -------- 3. Select driver --------
        Driver driver = matchingStrategy.matchDriver(metaData);

        // -------- 4. Calculate price --------
        double price = pricingStrategy.calculatePrice(metaData);

        // -------- 5. Create Trip object --------
        Trip trip = new Trip(
                rider,
                driver,
                srcLoc,
                dstLoc,
                price,
                pricingStrategy,
                matchingStrategy
        );

        // -------- 6. Store trip + metadata --------
        tripsInfo.put(trip.getTripId(), trip);
        metaData.setDriverRating(driver.getRating());
        tripsMetaDataInfo.put(trip.getTripId(), metaData);

        // -------- 7. Display details --------
        trip.displayTripDetails();
    }

    public Map<Integer, Trip> getTripsMap() {
        return tripsInfo;
    }
}
