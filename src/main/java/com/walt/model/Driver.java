package com.walt.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Driver extends NamedEntity {

    @ManyToOne
    City city;


    int history_num;

    public Driver(){}

    public Driver(String name, City city){
        super(name);
        this.city = city;
        this.history_num=0;
    }


    public void updateHistory(){
        history_num=history_num+1;
    }

    public int getHistory(){
        return history_num;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
