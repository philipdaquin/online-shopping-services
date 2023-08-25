/*
    Network Address Translation Gateway
    - NAT helps manage traffic flows for private resources that need to communicate with external servces 
*/
resource aws_eip nat { 
    vpc = true

    tags = { 
        Name = "dev-nat"
    }
}

resource aws_nat_gateway nat { 
    allocation_id = aws_eip.nat.id
    
    subnet_id = aws_subnet.public_subnet_2a.id
    tags = { 
        Name = "dev-nat"
    }

    depends_on = [aws_internet_gateway.gateway]
}
