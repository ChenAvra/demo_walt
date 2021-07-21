package com.walt;

import com.walt.dao.CustomerRepository;
import com.walt.model.City;
import com.walt.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@SpringBootApplication
public class WaltApplication {

    @Autowired

    CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(WaltApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WaltApplication.class);
    }



    @GetMapping("/hello")
    public String sayHello() {
        String s="";
        City city= new City("tel aviv");
        Customer customer= new Customer("Jack",city,"ben yehuda");
        customerRepository.save(customer);
        for (Customer c : customerRepository.findAll()) {
           s=c.toString()+" ";
        }
        return s;
    }


}
