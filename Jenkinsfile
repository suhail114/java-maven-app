pipeline {
    agent any
    tools {
        maven "Maven"
    }   
    stages {
        stage("build jar") {
            steps {
                script {
                   echo "build the application...."
                    sh 'mvn package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building the docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t suhail114/new-demo-project:java-mv-app-2.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push suhail114/new-demo-project:java-mv-app-2.0'
                }
            }
        }
        stage("test") {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the application"
                }
            }
        }
    }   
}
