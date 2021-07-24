package com.walt.dao;


import com.walt.model.DriverDistance;
import com.walt.model.DriverNameDistance;
import com.walt.model.Driver_hours;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface Driver_hoursRepository  extends CrudRepository<Driver_hours,String>  {

    // find only one row
    Driver_hours findByDriverNameAndHour(String name, int hour);
    List<Driver_hours> findAll();

    @Query(value = "SELECT sum(distance) as sumDriver,driverName from Driver_hours group by driverName order by sumDriver DESC ")
    List<DriverNameDistance> sumDriverDistance();



}
