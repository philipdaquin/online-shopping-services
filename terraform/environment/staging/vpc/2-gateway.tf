
/*
    Gateway
    - Refers to an internet gateway or a virtual private gateway 
    
    - Allows resources wihtin a VPC to access the internet, while a virtual 
    private gateway enables secures communication between a VPC and an on-premise network 
*/
resource "aws_internet_gateway" "gateway" {
  vpc_id = aws_vpc.main.id
  
  tags = { 
    Name = "staging-gateway"
  }
}