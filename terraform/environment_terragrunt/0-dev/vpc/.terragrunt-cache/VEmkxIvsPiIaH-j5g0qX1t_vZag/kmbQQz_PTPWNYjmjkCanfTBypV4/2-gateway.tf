
/*
    Gateway
    - Refers to an internet gateway or a virtual private gateway 
    
    - Allows resources wihtin a VPC to access the internet, while a virtual 
    private gateway enables secures communication between a VPC and an on-premise network 
*/
resource "aws_internet_gateway" "this" {
  vpc_id = aws_vpc.this.id
  
  tags = { 
    Name = "${var.env}-gateway"
  }
}