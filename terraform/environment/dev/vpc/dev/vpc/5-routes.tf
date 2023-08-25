
/*
    Routes tables are associated with subnets and contain rules for routing traffic either 
    within the VPC or externally through gateway 
*/
resource "aws_route_table" "private" { 
    vpc_id = aws_vpc.main.id

    route { 
        cidr_block = "0.0.0.0/0"
        nat_gateway_id = aws_nat_gateway.nat.id
    }


    tags = { 
        Name = "dev-private"
    }
}
resource "aws_route_table" "public" { 
    vpc_id = aws_vpc.main.id

    route { 
        cidr_block = "0.0.0.0/0"
        nat_gateway_id = aws_nat_gateway.nat.id
    }


    tags = { 
        Name = "dev-public"
    }
}

resource "aws_route_table_association" "private_subnet_2a" { 
    subnet_id = aws_subnet.private_subnet_2a.id
    route_table_id = aws_route_table.private.id
}
resource "aws_route_table_association" "private_subnet_2b" { 
    subnet_id = aws_subnet.private_subnet_2b.id
    route_table_id = aws_route_table.private.id
}



resource "aws_route_table_association" "public_subnet_2a" { 
    subnet_id = aws_subnet.public_subnet_2a.id
    route_table_id = aws_route_table.public.id
}
resource "aws_route_table_association" "public_subnet_2b" { 
    subnet_id = aws_subnet.public_subnet_2b.id
    route_table_id = aws_route_table.public.id
}