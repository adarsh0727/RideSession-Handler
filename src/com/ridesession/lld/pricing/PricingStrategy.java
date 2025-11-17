package com.ridesession.lld.pricing;

import com.ridesession.lld.trip.TripMetaData;

public interface PricingStrategy {
    double calculatePrice(TripMetaData tripMetaData);
}
