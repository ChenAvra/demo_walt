package com.walt;

import com.walt.dao.CustomerRepository;
import com.walt.dao.DeliveryRepository;
import com.walt.dao.DriverRepository;
import com.walt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WaltServiceImpl implements WaltService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CustomerRepository customerRepository;



    @Override
    public Delivery createOrderAndAssignDriver(Customer customer, Restaurant restaurant, Date deliveryTime) {

        if(customerRepository.findByName(customer.getName())==null){
            customerRepository.save(customer);
        }

        List<Driver> drivers=driverRepository.findAllDriversByCity(restaurant.getCity());

        int numDelivery =Integer.MAX_VALUE;

        Driver driver=null;
        for(int i=0; i<drivers.size(); i++){

            if(drivers.get(i).getHistory()<numDelivery){
                driver=drivers.get((i));
            }
        }

        if(driver==null){
            System.out.println("there is no available driver for now ");
        }

        Delivery delivery=new Delivery(driver, restaurant, customer,deliveryTime);

        deliveryRepository.save(delivery);

        return null;
    }

    @Override
    public List<DriverDistance> getDriverRankReport() {
        return null;
    }

    @Override
    public List<DriverDistance> getDriverRankReportByCity(City city) {
        return null;
    }
}
