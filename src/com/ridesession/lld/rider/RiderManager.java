package com.ridesession.lld.rider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RiderManager {

    private static volatile RiderManager instance;

    private Map<String, Rider> ridersMap;

    private RiderManager() {
        ridersMap = new ConcurrentHashMap<>();
    }

    public static RiderManager getInstance() {
        if (instance == null) {
            synchronized (RiderManager.class) {
                if (instance == null) {
                    instance = new RiderManager();
                }
            }
        }
        return instance;
    }

    public void addRider(String riderName, Rider rider) {
        ridersMap.put(riderName, rider);
    }

    public Rider getRider(String riderName) {
        return ridersMap.get(riderName);
    }
}
