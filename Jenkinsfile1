pipeline {
    agent any

    environment {
        APPLICATION_NAME = "Customer Automation"
        BUILD_DIR = "app"
        JAVA_VERSION = "17"
        MAVEN_HOME = "/usr/share/maven"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out code from repository..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building ${APPLICATION_NAME}..."
                dir("${BUILD_DIR}") {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
                dir("${BUILD_DIR}") {
                    sh 'mvn test'
                }
            }
        }

        stage('Code Quality') {
            steps {
                echo "Running code quality checks..."
                dir("${BUILD_DIR}") {
                    sh 'mvn spotbugs:check || true'
                }
            }
        }

        stage('Package') {
            steps {
                echo "Packaging application..."
                dir("${BUILD_DIR}") {
                    sh 'mvn package -DskipTests'
                }
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "Deploying ${APPLICATION_NAME}..."
                echo "Deployment steps would go here"
                echo "JAR location: ${BUILD_DIR}/target/customer-automation-1.0.0.jar"
            }
        }
    }

    post {
        always {
            echo "Pipeline execution completed"
            dir("${BUILD_DIR}") {
                junit 'target/surefire-reports/**/*.xml' || true
            }
        }
        success {
            echo "Build successful!"
        }
        failure {
            echo "Build failed!"
        }
    }
}
