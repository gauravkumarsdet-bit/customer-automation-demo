pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Automation Tests') {
            steps {
                dir('automation') {
                    bat '"C:\\Users\\sohinee\\apache-maven-3.9.10-bin\\apache-maven-3.9.10\\bin\\mvn.cmd" clean test'
                }
            }
        }
    }
}