package com.ridesession.lld.rider;

import com.ridesession.lld.common.Rating;

public class Rider {

    private String name;
    private Rating rating;

    public Rider(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getRiderName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Rider{name='" + name + "', rating=" + rating + "}";
    }
}
