pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/lebalbina/web-tests.git'
            }
        }

        stage('Execute Tests') {
            steps {
                script {
                    bat 'gradlew clean test'
                }
            }
            post {
                always {
                    allure includeProperties: false, jdk: '', results: [[path: 'app/build/allure-results']]
                }
            }
        }

        stage('Publish TestNG reports') {
            steps {
                publishTestNG()
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
