package com.example.auth_service.service;

import com.example.auth_service.domain.Address;
import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.actors.CustomerDetails;

public class CustomerDetailServiceTest {
    

    public CustomerDetailServiceTest() {}

    public CustomerDetails createMockOne() {

        var account = new AccountServiceTest();
        Account accountMock = account.createMockOne();
        Address address = new Address()
            .addressLineOne("123 Main St")
            .addressLineTwo("Apt 4B")
            .city("Exampleville")
            .state("Exampleria")
            .zipCode("12345")
            .country("Exampleland");
        
        CustomerDetails mock = new CustomerDetails();
        mock.setAccount(accountMock);
        mock.setAddress(address);
    
        return mock;

    }
    public CustomerDetails createMockTwo() {
        var account = new AccountServiceTest();
        Account accountMock = account.createMockOne();
        Address address = new Address()
            .addressLineOne("456 Elm Street")
            .addressLineTwo("Unit 7C")
            .city("Maplewood")
            .state("New Jersey")
            .zipCode("07040")
            .country("United States");
        
        CustomerDetails mock = new CustomerDetails();
        mock.setAccount(accountMock);
        mock.setAddress(address);
    
        return mock;

    }
}
