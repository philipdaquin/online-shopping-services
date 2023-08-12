package com.example.auth_service.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.errors.BadRequestException;
import com.example.auth_service.service.CustomerDetailService;


@RestController
@RequestMapping(name = "/api")
public class CustomerDetailsController {
    
    private final Logger log = LoggerFactory.getLogger(CustomerDetailsController.class);

    private final CustomerDetailService customerDetailService;

    public CustomerDetailsController(
        CustomerDetailService customerDetailService
    ) { 
        this.customerDetailService = customerDetailService;
    }


    /**
     *
     *  
     * @param id
     * @return
     */
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDetails> getCustomerDetails(@PathVariable final Long id) { 
        CustomerDetails customerDetails = customerDetailService
            .getOne(id)
            .orElseThrow(() -> new BadRequestException("Invalid Customer Id"));
        
        return ResponseEntity.ok().body(customerDetails);   
    }

    void createCustomerDetails() {}
    void partialUpdateDetails() {} 
    void deleteCustomerDertails() {}
    void getAllCustomerDetails() {}
 
}
