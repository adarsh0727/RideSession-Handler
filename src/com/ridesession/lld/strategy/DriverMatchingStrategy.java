package com.ridesession.lld.strategy;

import com.ridesession.lld.driver.Driver;
import com.ridesession.lld.trip.TripMetaData;

public interface DriverMatchingStrategy {
    Driver matchDriver(TripMetaData tripMetaData);
}
