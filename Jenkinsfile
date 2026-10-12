pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
        disableConcurrentBuilds()
        timeout(time: 30, unit: 'MINUTES')
        timestamps()
    }
    
    environment {
        DOCKER_HOST = 'unix:///home/sriram-srikanth/.docker/desktop/docker.sock'
        COMPOSE_PROJECT_NAME = 'spe_mini_project-1'
        CALCULATOR_IMAGE = "humanoid2005/calculaor-service:${BUILD_NUMBER}"
        NOTIFICATION_EMAIL = 'Sriram.Srikanth@iiitb.ac.in'
    }

    stages {
        stage('Checkout') {
            steps {
                sh '''
                    docker rm -f $(docker ps -aq --filter "label=com.docker.compose.project=$COMPOSE_PROJECT_NAME") || true
                    docker rmi $(docker image ls -q --filter "label=com.docker.compose.project=$COMPOSE_PROJECT_NAME") || true
                '''
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/TEST-*.xml', allowEmptyResults: false
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build --label 'com.docker.compose.project=$COMPOSE_PROJECT_NAME' -t $CALCULATOR_IMAGE ."
                sh "docker build --label 'com.docker.compose.project=$COMPOSE_PROJECT_NAME' -f Dockerfile.nginx -t calculator-nginx:local ."
            }
        }

        stage('Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: '8cf076a1-946f-4376-9cfe-0202f87cc553',
                    usernameVariable: 'DOCKER_HUB_USERNAME',
                    passwordVariable: 'DOCKER_HUB_TOKEN'
                )]) {
                    sh 'echo "$DOCKER_HUB_TOKEN" | docker login -u "$DOCKER_HUB_USERNAME" --password-stdin'
                    sh "docker push $CALCULATOR_IMAGE"
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose up -d --no-build --pull never --remove-orphans --wait --wait-timeout 180'
                sh 'curl -fsS http://localhost:8000/actuator/health | grep -q \'"status":"UP"\''
                sh 'docker compose ps'
            }
        }
    }

    post {
        always {
            script {
                def status = currentBuild.currentResult ?: 'SUCCESS'
                
                emailext(
                    to: "${NOTIFICATION_EMAIL}",
                    subject: "[${status}] ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """Job: ${env.JOB_NAME}
                    Build number: ${env.BUILD_NUMBER}
                    Result: ${status}
                    Build details: ${env.BUILD_URL}
                    Docker image tag: ${CALCULATOR_IMAGE}""",
                    mimeType: 'text/plain'
                )
            }
        }
    }
}
