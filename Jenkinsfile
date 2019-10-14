pipeline {
    agent any
    stages {

        stage ('Building, testing and publishing reports') {
            steps {
                sh 'mvn clean install'
            }

            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    publishHTML([
                    allowMissing          : false,
                    alwaysLinkToLastBuild : false,
                    keepAll               : true,
                    reportDir             : 'target/site/jacoco/',
                    reportFiles           : 'index.html',
                    reportTitles          : "Tests coverage",
                    reportName            : "Tests coverage"
                    ])
                }
                success {
                    archive "target/restcms-0.0.1-SNAPSHOT.jar"
                }
            }
        }

        stage('Deploying artifacts to selected target') {
        agent any
            steps {
            sh "bash ./stop.sh"
            sh "bash ./start_silent.sh"
            }
        }
    }
}