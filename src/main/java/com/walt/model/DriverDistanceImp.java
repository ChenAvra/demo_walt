package com.walt.model;

public class DriverDistanceImp implements DriverDistance {

    Driver d;
    long distance;


    public DriverDistanceImp(Driver d, long distance) {
        this.d = d;
        this.distance = distance;
    }



    @Override
    public Driver getDriver() {
        return null;
    }

    @Override
    public Long getTotalDistance() {
        return null;
    }
}
