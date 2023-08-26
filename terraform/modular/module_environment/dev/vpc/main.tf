

provider "aws" {
    region = "ap-southeast-2" 
    access_key = var.ACCESS_KEY
    secret_key=  var.SECRET_KEY
}

terraform { 
    backend "local" { 
        path = "dev/vpc/terraform.tfstate"
    }
}

module "vpc" { 

    source = "../../../modules/vpc"

    env = "dev"
    azs = ["ap-southeast-2a", "ap-southeast-2b"]
    private_subnets = ["10.0.0.0/19", "10.0.32.0/19"]
    public_subnets = ["10.0.64.0/19", "10.0.96.0/19"]

    private_subnet_tags = {
        "kubernetes.io/role/internal-elb"   = "1"
        "kubernetes.io/cluster/staging-demo"    = "owned"
    }
    public_subnet_tags = {
        "kubernetes.io/role/elb"   = "1"
        "kubernetes.io/cluster/staging-demo"    = "owned"
    }
}