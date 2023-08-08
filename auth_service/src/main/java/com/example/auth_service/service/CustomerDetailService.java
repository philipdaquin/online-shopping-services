package com.example.auth_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.auth_service.domain.Address;
import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.repository.CustomerDetailsRepository;

@Service
@Transactional
public class CustomerDetailService {
    

    private final Logger log = LoggerFactory.getLogger(CustomerDetailService.class);

    private final CustomerDetailsRepository customerDetailsRepository;

    public CustomerDetailService(CustomerDetailsRepository customerDetailsRepository) { 
        this.customerDetailsRepository = customerDetailsRepository;
    }

    /**
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<CustomerDetails> getOne(Long id) { 
        return customerDetailsRepository.findById(id);
    }

    /**
     * 
     * @param curCustomerDetails
     * @return
     */
    public CustomerDetails save(CustomerDetails curCustomerDetails) { 
        return customerDetailsRepository.save(curCustomerDetails);
    }

    /**
     * 
     * @param pageable
     * @return
     */
    public Page<CustomerDetails> getAll(Pageable pageable) { 
        return customerDetailsRepository.findAll(pageable);
    }

    /**
     * 
     * @param id
     */
    public void delete(Long id) { 
        customerDetailsRepository.deleteById(id);
    }

    /**
     * 
     * @param customerDetails
     * @return
     */
    public Optional<CustomerDetails> partialUpdateDetails(CustomerDetails customerDetails) { 
        return customerDetailsRepository
            .findById(customerDetails.getId())
            .map(current -> { 


                Address newAddress = customerDetails.getAddress();
                Address currentAddress = current.getAddress();

                if (currentAddress == null) current.setAddress(new Address());
                if (newAddress.getAddressLineOne() != null) { 
                    currentAddress.setAddressLineOne(newAddress.getAddressLineOne());
                }
                if (newAddress.getAddressLineTwo() != null) { 
                    currentAddress.setAddressLineTwo(newAddress.getAddressLineTwo());
                }
                if (newAddress.getCity() != null) { 
                    currentAddress.setCity(newAddress.getCity());
                }
                if (newAddress.getState() != null) { 
                    currentAddress.setState(newAddress.getState());
                }
                if (newAddress.getZipCode() != null) { 
                    currentAddress.setZipCode(newAddress.getZipCode());
                }
                if (newAddress.getCountry() != null) { 
                    currentAddress.setCountry(newAddress.getCountry());
                }
                
                return current;
            })
            .map(customerDetailsRepository::save);
    }

}
