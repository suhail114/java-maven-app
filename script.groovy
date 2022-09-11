def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t suhail114/java-maven-app:java-mv-app-2.1 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push suhail114/java-maven-app:java-mv-app-2.1'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
