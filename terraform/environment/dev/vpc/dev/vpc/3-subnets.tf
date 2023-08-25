

/*
    Subnets 
    - Subnets are subdivisions of a VPC's IP address range. They provide 
    segmentation of network resources

    - Subnets can be public or private 
*/

resource "aws_subnet" "private_subnet_2a" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.0.0/19"
    availability_zone   = "ap-southeast-2"

    tags =  { 
        "Name"                              = "dev-private-southeast-2a"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/dev-demo"    = "owned"
    }
}
resource "aws_subnet" "private_subnet_2b" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.0.0/19"
    availability_zone   = "ap-southeast-2"

    tags =  { 
        "Name"                              = "dev-private-southeast-2b"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/dev-demo"    = "owned"
    }
}



resource "aws_subnet" "public_subnet_2a" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.0.0/19"
    availability_zone   = "ap-southeast-2"
    map_public_ip_on_launch = true

    tags =  { 
        "Name"                              = "dev-public-southeast-2a"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/dev-demo"    = "owned"
    }
}
resource "aws_subnet" "public_subnet_2b" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.0.0/19"
    availability_zone   = "ap-southeast-2"
    map_public_ip_on_launch = true

    tags =  { 
        "Name"                              = "dev-public-southeast-2b"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/dev-demo"    = "owned"
    }
}

