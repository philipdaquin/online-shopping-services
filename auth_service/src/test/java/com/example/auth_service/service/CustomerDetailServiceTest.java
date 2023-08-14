package com.example.auth_service.service;

import com.example.auth_service.domain.Address;
import com.example.auth_service.domain.actors.Account;
import com.example.auth_service.domain.actors.CustomerDetails;
import com.example.auth_service.util.AddressUtil;

public class CustomerDetailServiceTest {
    

    public CustomerDetailServiceTest() {}

    public CustomerDetails createMockOne() {

        var account = new AccountServiceTest();
        Account accountMock = account.createMockOne();

        AddressUtil util = new AddressUtil();
        Address address = util.createMock();

        CustomerDetails mock = new CustomerDetails();
        mock.setAccount(accountMock);
        mock.setAddress(address);
    
        return mock;

    }
    public CustomerDetails createMockTwo() {
        var account = new AccountServiceTest();
        Account accountMock = account.createMockOne();
        
        AddressUtil util = new AddressUtil();
        Address address = util.createMockTwo();
        
        CustomerDetails mock = new CustomerDetails();
        
        mock.setAccount(accountMock);
        mock.setAddress(address);
    
        return mock;

    }
}
