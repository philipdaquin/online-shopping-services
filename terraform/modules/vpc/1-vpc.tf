
/*
    VPC (Virtual Private Cloud)
    - A VPC is a logically isolated section of the cloud where you can launch resources in a virtual 
    network that you define

    - In AWS, a VPC allows you to create your own virtual network within the AWS cloud 
    You can control IP Address, subnets, route tables, and network gateways
*/
resource "aws_vpc" "this" { 
    cidr_block = var.vpc_cidr_block

    enable_dns_support = true
    enable_dns_hostnames = true

    tags = { 
        "Name" = "${var.env}-main"
    }
    
}