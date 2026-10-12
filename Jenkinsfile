pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pulls the latest code from the Git repository where this Jenkinsfile is stored
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                // Compiles the source code using the Maven wrapper in your project
                sh './mvnw clean compile'
            }
        }

        stage('Test') {
            steps {
                // Executes the Spring Boot unit tests
                sh './mvnw test'
            }
        }

        stage('Install') {
            steps {
                // Packages the final .jar file into the target/ directory
                // We skip tests here because they already passed in the previous stage
                sh './mvnw install -DskipTests'
            }
        }

        stage('Docker Deploy') {
            steps {
                // Builds the new Docker image with the freshly compiled .jar and starts the container
                sh 'docker compose up -d --build'
            }
        }
    }
}