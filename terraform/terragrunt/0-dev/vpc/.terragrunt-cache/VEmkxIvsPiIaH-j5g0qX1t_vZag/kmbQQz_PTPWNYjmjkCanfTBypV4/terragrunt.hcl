terraform { 
    source = "../../../enviroment_modular/modules/vpc/"
    // source = "../../../enviroment_modular/module_environment/dev/vpc/"
}

include "root" { 
    path = find_in_parent_folders()
}

# Indicate what region to deploy the resources into
// generate "provider" {
//   path = "provider.tf"

//   if_exists = "overwrite_terragrunt"
  
//   contents = <<EOF
//     provider "aws" {
//         region = "ap-southeast-2"
//     }
//     EOF
// }


inputs = { 
    env                 = "dev"
    azs                 = ["ap-southeast-2a", "ap-southeast-2b"]
    private_subnets     = ["10.0.0.0/19", "10.0.32.0/19"]
    public_subnets      = ["10.0.64.0/19", "10.0.96.0/19"]

    private_subnet_tags = {
        "kubernetes.io/role/internal-elb"       = "1"
        "kubernetes.io/cluster/dev-demo"    = "owned"
    }
    public_subnet_tags = {
        "kubernetes.io/role/elb"                = "1"
        "kubernetes.io/cluster/dev-demo"    = "owned"
    }
}