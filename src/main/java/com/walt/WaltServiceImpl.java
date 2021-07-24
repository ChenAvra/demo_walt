package com.walt;

import com.walt.dao.CustomerRepository;
import com.walt.dao.DeliveryRepository;
import com.walt.dao.DriverRepository;
import com.walt.dao.Driver_hoursRepository;
import com.walt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class WaltServiceImpl implements WaltService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Driver_hoursRepository  driverHoursRepository;

    public int getHour(Date d){


        LocalDateTime time=d.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        // get HourOfDay
        int hourOfDay = time.getHour();

        return hourOfDay;
    }

    @Override
    public Delivery createOrderAndAssignDriver(Customer customer, Restaurant restaurant, Date deliveryTime) {
        if(customerRepository.findByName(customer.getName())==null){
            customerRepository.save(customer);
        }
        //find all the driver from the same city of the customer and restaurant
        List<Driver> drivers=driverRepository.findAllDriversByCity(restaurant.getCity());
        //find a free driver
        Driver driver=findDriver(drivers,deliveryTime);
        if(driver==null){
            System.out.println("there is no available driver for now ");
        }else {
            driver.updateHistory();
            driverRepository.save(driver);
            //create delivery
            Delivery delivery=new Delivery(driver, restaurant, customer,deliveryTime);
            //generate disatce
            Random rand = new Random();
            int distance = rand.nextInt(21);
            delivery.setDistance(distance);
            //save delivery to repo
            deliveryRepository.save(delivery);
            //save driver,hour and distance of the hour's day
            Driver_hours dh= new Driver_hours(driver.getName(),getHour(deliveryTime),distance);
            driverHoursRepository.save(dh);
        }
        return null;
    }


    public Driver findDriver(List<Driver> drivers,Date deliveryTime){
        int numDelivery =Integer.MAX_VALUE;
        Driver driver=new Driver();
        for(int i=0; i<drivers.size(); i++){
            Driver_hours dh=driverHoursRepository.findByDriverNameAndHour(drivers.get(i).getName(),getHour(deliveryTime));
            if(dh==null && drivers.get(i).getHistory()<numDelivery){
                driver=drivers.get(i);
                numDelivery=drivers.get(i).getHistory();
            }
        }
        return driver;
    }

    @Override
    public List<DriverDistance> getDriverRankReport() {
        //get all the sums of distances of all the drivers and insert them to DriveDistance list
        List<DriverNameDistance> list=driverHoursRepository.sumDriverDistance();
        List<DriverDistance> listDistances= new LinkedList<>();
        for(int i=0; i<list.size(); i++){
            Driver d=driverRepository.findByName(list.get(i).getName());
            long distance=list.get(i).getDistance();
            listDistances.add(new DriverDistanceImp(d,distance));
        }
        return listDistances;
    }

    @Override
    public List<DriverDistance> getDriverRankReportByCity(City city) {
        return null;
    }
}
