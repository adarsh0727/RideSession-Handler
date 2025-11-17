package com.ridesession.lld.strategy;

import com.ridesession.lld.common.Rating;
import com.ridesession.lld.pricing.DefaultPricingStrategy;
import com.ridesession.lld.pricing.PricingStrategy;
import com.ridesession.lld.pricing.RatingBasedPricingStrategy;
import com.ridesession.lld.trip.TripMetaData;

public class StrategyManager {

    private static volatile StrategyManager instance;

    private StrategyManager() {}

    public static StrategyManager getInstance() {
        if (instance == null) {
            synchronized (StrategyManager.class) {
                if (instance == null) {
                    instance = new StrategyManager();
                }
            }
        }
        return instance;
    }

    // -------------------
    // Pricing Strategy
    // -------------------
    public PricingStrategy determinePricingStrategy(TripMetaData metaData) {

        if (metaData.getRiderRating() == Rating.FIVE_STARS ||
            metaData.getRiderRating() == Rating.FOUR_STARS) {

            return new RatingBasedPricingStrategy();
        }

        return new DefaultPricingStrategy();
    }

    // -------------------
    // Driver Matching Strategy
    // -------------------
    public DriverMatchingStrategy determineMatchingStrategy(TripMetaData metaData) {

        // In future we can switch to surge, pool, premium, etc.
        return new LeastTimeBasedMatchingStrategy();
    }
}
