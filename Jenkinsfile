pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/gauravkumarsdet-bit/customer-automation-demo.git'
            }
        }

        stage('Build and Test') {
            steps {
                bat '"C:\\Users\\sohinee\\apache-maven-3.9.10-bin\\apache-maven-3.9.10\\bin\\mvn.cmd" clean test'
            }
        }
    }
}