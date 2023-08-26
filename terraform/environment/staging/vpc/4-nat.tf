/*
    Network Address Translation Gateway
    - NAT helps manage traffic flows for private resources that need to communicate with external servces 
*/
resource aws_eip nat { 
    vpc = true

    tags = { 
        Name = "staging-nat"
    }
}

resource aws_nat_gateway nat { 
    allocation_id = aws_eip.nat.id
    
    subnet_id = aws_subnet.public_southeast_2a.id
    tags = { 
        Name = "staging-nat"
    }

    depends_on = [aws_internet_gateway.gateway]
}
