

/*
    Subnets 
    - Subnets are subdivisions of a VPC's IP address range. They provide 
    segmentation of network resources

    - Subnets can be public or private 
*/

resource "aws_subnet" "private_southeast_2a" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.0.0/19"
    availability_zone   = "ap-southeast-2a"

    tags =  { 
        "Name"                              = "staging-private-southeast-2a"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/staging-demo"    = "owned"
    }
}
resource "aws_subnet" "private_southeast_2b" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.32.0/19"
    availability_zone   = "ap-southeast-2b"

    tags =  { 
        "Name"                              = "staging-private-southeast-2b"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/staging-demo"    = "owned"
    }
}



resource "aws_subnet" "public_southeast_2a" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.64.0/19"
    availability_zone   = "ap-southeast-2a"
    map_public_ip_on_launch = true

    tags =  { 
        "Name"                              = "staging-public-southeast-2a"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/staging-demo"    = "owned"
    }
}
resource "aws_subnet" "public_southeast_2b" { 
    vpc_id              = aws_vpc.main.id
    cidr_block          = "10.0.96.0/19"
    availability_zone   = "ap-southeast-2b"
    map_public_ip_on_launch = true

    tags =  { 
        "Name"                              = "staging-public-southeast-2b"
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/staging-demo"    = "owned"
    }
}

