pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code
                git url: 'https://github.com/philipdaquin/java_microservices.git'
            }
        }
        stage('Build discovery service') { 
            steps { 
                build '1-discovery-service-pipeline'
            }
        }
        stage('Build Config Service') { 
            steps { 
                build '2-config-service-pipeline'
            }
        }
        stage('Build Auth Service') { 
            steps { 
                build '3-auth-service-pipeline'
            }
        }
        stage('Build API Gateway') { 
            steps { 
                build '3-api-gateway-pipeline'
            }
        }
        stage('Build order service') { 
            steps { 
                build '4-order-service-pipeline'
            }
        }
        stage('Build product service') { 
            steps { 
                build '5-product-service-pipeline'
            }
        }

        /*
            Ansible to deploy new changes to current instance of EKS cluster
        */
        stage('Deploy to EKS Cluster') { 
            steps { 
                script { 
                    sh 'ansible-playbook ./ansible-server/deployment-playbook.yaml'
                }
            }
        } 
    }
}
