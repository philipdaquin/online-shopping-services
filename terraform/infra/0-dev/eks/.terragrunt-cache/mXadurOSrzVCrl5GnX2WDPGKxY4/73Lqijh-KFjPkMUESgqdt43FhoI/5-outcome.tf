output "eks_name" {
    value = aws_eks_cluster.this.name
}

output "open_provider_name" {
    value = aws_iam_openid_connect_provider.this[0].arn
}