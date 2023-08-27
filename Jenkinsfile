def pipelineMapping = [
    './auth_service' : authService,
    './apigateway': apiGateway,
    './config_server': configServer,
    './discovery_service': discoveryService,
    './order_service': orderService,
    './product_service': productService,
    './shopping_cart': shoppingCart,
]


pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code
                checkout scm
            }
        }

        stage('Run Changed Pipelines') {
            steps {
                script {
                    for (def entry in pipelineMapping) {
                        def pipelinePath = entry.key
                        def pipelineName = entry.value

                        echo 'hello world!'

                        // if (changesetContainsPath(pipelinePath)) {
                        //     echo "Running ${pipelineName} pipeline..."
                        //     build job: "${pipelineName}", wait: false
                        // }
                    }
                }
            }
        }
    }
}

def changesetContainsPath(path) {
    def changedFiles = currentBuild.changeSets.collectMany { it.affectedFiles.collect { it.path } }
    return changedFiles.any { it.startsWith(path) }
}