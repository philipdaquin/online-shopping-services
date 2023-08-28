
variable "env" { 
    description = "Environment Name"
    type = string
}

variable "eks_name" { 
    description = "Name of the cluster"
    type = string
    default = "k8_demo"
}

variable "enable_cluster_autoscaler" {
    description = "Determines whether to deploy cluster autoscaler"
    type = bool
    default = false
}


variable "cluster_autoscaler_helm_version" { 
    type = string
    description = "Cluster Autoscaler Helm version"
}

variable "openid_provider_arn" { 
    description = "IAM Openid Connect Provider ARN"
    type = string
} 
