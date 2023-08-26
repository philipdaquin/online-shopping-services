
/*
    Providers
        - Enable you to interact with various cloud providers and services. 
        They allow Terraform to manage resources on these platform 
*/
terraform { 
    required_version = ">= 1.0"
    
    # backend "local" { 
    #     path = "dev/vpc/terraform.tfstate"
    # }

    required_providers { 
        aws = {
            source  = "hashicorp/aws"
            version = "~> 4.62"
        }
    }
}