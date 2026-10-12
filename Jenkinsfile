pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                sh './mvnw clean compile'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Install') {
            steps {
                sh './mvnw install -DskipTests'
            }
        }

        stage('Docker Deploy') {
            steps {
                sh 'docker compose up -d --build'
            }
        }
    }
}