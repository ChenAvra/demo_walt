package com.walt.model;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class Driver_hours {
    @Id
    String driverName;
    @Id
    int hour;
    int distance;
    public Driver_hours(String driverName, int hour,int distance) {
        this.driverName = driverName;
        this.hour = hour;
        this.distance=distance;
    }


    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }






}
