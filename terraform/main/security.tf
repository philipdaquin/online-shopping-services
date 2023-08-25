
/*
  AWS Security Group 
*/
resource "aws_security_group" "group_one" {
  name_prefix = "group_one"
  vpc_id = module.vpc.vpc_id

  ingress { 
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["10.0.0.0/8"]
  }
}

resource "aws_security_group" "group_two" {
  name_prefix = "group_two"
  vpc_id = module.vpc.vpc_id

  ingress { 
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["10.0.0.0/8"]
  }
}


resource "aws_security_group" "all_group_mgmt" { 
  name_prefix = "all_group_mgmt"
  vpc_id = module.vpc.vpc_id

  ingress { 
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["10.0.0.0/8"]
  }
}