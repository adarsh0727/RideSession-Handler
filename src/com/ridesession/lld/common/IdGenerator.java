package com.ridesession.lld.common;

public class IdGenerator {
    private static int nextTripId = 1;

    public static int getNextTripId() {
        return nextTripId++;
    }
}