/*
    Amazon Elastic Kubernetes Service 

*/

module eks { 
    source = "terraform-aws-modules/eks/aws"
    version = "17.1.0"

    cluster_name = local.cluster_name
    vpc_id = module.vpc.vpc_id
    cluster_version = "1.20"
    tags = { 
        "Name" = "EKS-Cluster"
    }

    workers_group_defaults = {
        root_volume_type = "gp2"
    }

    subnets = module.vpc.private_subnets

    # Security groups 
    worker_groups = [
        {
            name = "Worker-Group-1"
            instance_type = "t2.micro"
            asg_desired_capacity = 2
            additional_security_group_ids = [aws_security_group.group_one.id]
        },
        {
            name = "Worker-Group-2"
            instance_type = "t2.micro"
            asg_desired_capacity = 1
            additional_security_group_ids = [aws_security_group.group_two.id]
        }
    ]
}

data "aws_eks_cluster" "eks" {
  name = module.eks.cluster_id
}

data "aws_eks_cluster_auth" "eks" {
  name = module.eks.cluster_id
}

provider "kubernetes" {
  host                   = data.aws_eks_cluster.eks.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.eks.certificate_authority[0].data)
  token                  = data.aws_eks_cluster_auth.eks.token
}
