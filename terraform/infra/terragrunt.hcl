remote_state { 
    backend = "local"

    generate = { 
        path = "backend.tf"
        if_exists = "overwrite_terragrunt"
    }

    config = { 
        path = "${path_relative_to_include()}/terraform.tfstate"
    }
}


generate "provider" {
    path = "provider.tf"
    if_exists = "overwrite_terragrunt"

    contents = <<EOF
        provider "aws" {
            region = "ap-southeast-2"
            access_key = var.ACCESS_KEY
            secret_key=  var.SECRET_KEY
        }
    EOF
}

generate "variable" { 
    path = "variables.tf"
    if_exists = "overwrite_terragrunt"
    contents = <<EOF
        variable "ACCESS_KEY" {
            description = "Access value"
            sensitive = true
            type = string
            # default = "XX"
        }
        variable "SECRET_KEY" {
            description="Secret value"
            sensitive = true
            type= string
            # default = "XX"
        }
    EOF
}