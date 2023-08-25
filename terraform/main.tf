
provider "aws" {
  region = "ap-southeast-2"
  access_key = "${variables.ACCESS_KEY}"
  secret_key= "${variables.SECRET_KEY}"
}

data "aws_availability_zones" "azs" {
  state = "available"
}