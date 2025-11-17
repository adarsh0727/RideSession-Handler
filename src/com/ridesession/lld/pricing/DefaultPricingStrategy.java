package com.ridesession.lld.pricing;

import com.ridesession.lld.trip.TripMetaData;

public class DefaultPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(TripMetaData metaData) {
        System.out.println("Based on default strategy, price is 100");
        return 100.0;
    }
}
