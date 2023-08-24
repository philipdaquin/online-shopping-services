pipeline { 
    agent any
    tools{
        maven "maven_3_5_0"
    }
    stages {
        stage("Build and Install Maven dependencies") {
            steps {

                echo "Java VERSION"
                sh 'java --version'
                echo "Maven VERSION"
                sh 'mvn --version'
                echo 'building project...'
                
                dir("apigateway") { 
                    sh "mvn clean install"
                }

                dir("auth_service") { 
                    sh "mvn clean install"
                }
                dir("config_server") {
                    sh "mvn clean install"
                    
                }
                dir("discovery_service") {
                    sh "mvn clean install"
                    
                }
                dir("order_service") {
                    sh "mvn clean install"
                    
                }
                dir("product_service") {
                    sh "mvn clean install"
                    
                }
                dir("shopping_cart") {
                    sh "mvn clean install"
                    
                }
                
            }

        } 
     
    }   

    
}