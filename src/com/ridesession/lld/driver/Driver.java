package com.ridesession.lld.driver;

import com.ridesession.lld.common.Rating;

public class Driver {

    private String name;
    private boolean available;
    private Rating rating;

    public Driver(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
        this.available = false;
    }

    public void updateAvailability(boolean available) {
        this.available = available;
    }

    public String getDriverName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Driver{name='" + name + "', rating=" + rating + ", available=" + available + "}";
    }
}
