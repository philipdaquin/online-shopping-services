
provider "aws" {
    region = "ap-southeast-2"
    access_key = var.ACCESS_KEY
    secret_key=  var.SECRET_KEY
}


terraform { 
    required_version = ">= 1.0"
    
    backend "local" { 
        path = "dev/vpc/terraform.tfstate"
    }

    required_providers { 
        aws = { 
            source = "hashicorp/aws"
            version = ">= 4.62"
        }
    }
}

data "aws_availability_zones" "azs" {
    state = "available"
}