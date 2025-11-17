package com.ridesession.lld.pricing;

import com.ridesession.lld.common.Util;
import com.ridesession.lld.trip.TripMetaData;

public class RatingBasedPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(TripMetaData metaData) {

        double price = Util.isHighRating(metaData.getRiderRating()) ? 55.0 : 65.0;

        System.out.println(
            "Based on " + Util.ratingToString(metaData.getRiderRating()) +
            " rider rating, price of the trip is " + price
        );

        return price;
    }
}
