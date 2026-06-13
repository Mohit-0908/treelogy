pipeline {
    agent any   // where to run

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/Mohit-0908/treelogy.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Generate Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            echo 'Execution Completed'
        }
        success {
            echo 'Tests Passed'
        }
        failure {
            echo 'Test Failed'
        }
    }
}