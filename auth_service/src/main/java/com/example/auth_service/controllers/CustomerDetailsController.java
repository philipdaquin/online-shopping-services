package com.example.auth_service.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.errors.BadRequestException;
import com.example.auth_service.errors.NotFoundException;
import com.example.auth_service.repository.CustomerDetailsRepository;
import com.example.auth_service.service.CustomerDetailService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(name = "/api")
public class CustomerDetailsController {
    
    private final Logger log = LoggerFactory.getLogger(CustomerDetailsController.class);

    private final CustomerDetailService customerDetailService;

    private final CustomerDetailsRepository customerDetailsRepository;

    public CustomerDetailsController(
        CustomerDetailService customerDetailService,
        CustomerDetailsRepository customerDetailsRepository
    ) { 
        this.customerDetailService = customerDetailService;
        this.customerDetailsRepository = customerDetailsRepository;
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

    /**
     * 
     * @param customerDetails
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(name = "/customer")
    ResponseEntity<CustomerDetails> createCustomerDetails(@Valid @RequestBody CustomerDetails customerDetails) throws URISyntaxException {
        CustomerDetails newCustomer = customerDetailService.save(customerDetails);

        return ResponseEntity
            .created(new URI("/customer" + "/" + newCustomer.getId()))
            .build();
    }

    /**
     * 
     * @param id
     * @param customerDTO
     * @return
     */
    @PatchMapping(name = "/customer/{id}", consumes = "applications/merge-patch+json")
    ResponseEntity<CustomerDetails> partialUpdateDetails(
        @PathVariable(value = "id", required = false) final Long id, 
        @Valid @RequestBody CustomerDetails customerDTO
    ) {
        if (customerDTO.getId() == null) throw new BadRequestException("Invalid Customer ID");
        if (!customerDetailsRepository.existsById(id)) throw new BadRequestException("Missing Customer ID");
        if (customerDTO.getId() != id) throw new BadRequestException("Invalid Customer Id");

        CustomerDetails response = customerDetailService
            .partialUpdateDetails(customerDTO)
            .orElseThrow(() -> new BadRequestException("Unable to execute updates on Customer Details"));

        return ResponseEntity.ok().body(response);
    } 


    /**
     * 
     * @param customerId
     * @return
     */
    @DeleteMapping(name = "/customer/{id}")
    ResponseEntity<Void> deleteCustomerDertails(@PathVariable Long customerId) {

        if (!customerDetailsRepository.existsById(customerId)) throw new NotFoundException();

        customerDetailService.delete(customerId);

        return ResponseEntity.notFound().build();
    }

    /**
     * 
     * @param pageable
     * @return
     */
    @GetMapping(name = "/customer")
    ResponseEntity<List<CustomerDetails>> getAllCustomerDetails(Pageable pageable) {
        List<CustomerDetails> customers = customerDetailService.getAll(pageable);

        return ResponseEntity
            .ok()
            .body(customers);
    }
}
