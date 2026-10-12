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

        stage('Build') {
            steps {
                sh './mvnw install -DskipTests'
            }
        }

        stage('Deploy') {
            environment {
                DOCKER_HOST = 'unix:///home/sriram-srikanth/.docker/desktop/docker.sock'
            }
            steps {
                sh 'docker info'
                sh 'docker compose up -d --build'
            }
        }
    }
}