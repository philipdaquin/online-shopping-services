package com.example.auth_service.util;

import com.example.auth_service.domain.Address;

public class AddressUtil {
    
    public AddressUtil() {}

    public Address createMock() { 
        Address address = new Address()
            .addressLineOne("123 Main St")
            .addressLineTwo("Apt 4B")
            .city("Exampleville")
            .state("Exampleria")
            .zipCode("12345")
            .country("Exampleland");
        return address;
    }

    public Address createMockTwo() { 
        Address address = new Address()
            .addressLineOne("456 Elm Street")
            .addressLineTwo("Unit 7C")
            .city("Maplewood")
            .state("New Jersey")
            .zipCode("07040")
            .country("United States");
        return address;
    }
}
