package com.walt.model;

import java.time.LocalDateTime;

public class DriverNameDistance {

    String name;
    Long distance;

    public DriverNameDistance(String name, Long distance) {
        this.name = name;
        this.distance = distance;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }


}
