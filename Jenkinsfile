pipeline {
    agent any
    environment {
        CREDS = credentials('test_data')
    }
    parameters {
        string(name: 'ENV', defaultValue: 'prod', description: "Environment to run tests against")
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Execute Tests') {
            steps {
                script {
                    bat 'set ENV=%ENV%'
                    bat 'gradlew clean test'
                }
            }
            post {
                always {
                    allure includeProperties: false, jdk: '', results: [[path: 'app/build/allure-results']]
                    publishTestNG()
                }
            }
        }
    }

    post {
        always {
            withCredentials([string(credentialsId: 'DISCORD_WEBHOOK', variable: 'WEBHOOK')]) {
                discordSend(
                description: currentBuild.result,
                title: "Jenkins Build Notification",
                webhookURL: WEBHOOK
                )
            }
        }
    }
}

def publishTestNG() {
    step([$class: 'Publisher', reportFilenamePattern: '**app/build/reports/tests/test/testng-results.xml'])
}
