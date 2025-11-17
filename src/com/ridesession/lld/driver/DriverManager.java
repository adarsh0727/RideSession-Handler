package com.ridesession.lld.driver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {

    private static volatile DriverManager instance;

    private Map<String, Driver> driversMap;

    private DriverManager() {
        driversMap = new ConcurrentHashMap<>();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    public void addDriver(String driverName, Driver driver) {
        driversMap.put(driverName, driver);
    }

    public Driver getDriver(String driverName) {
        return driversMap.get(driverName);
    }

    public Map<String, Driver> getDriversMap() {
        return driversMap;
    }
}
