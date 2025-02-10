pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
	              echo '애플리케이션 빌드'
            } 
        }
        
        stage('Test'){
		        steps{
				        echo '애플리케이션 테스트'
		        }
        }
    }
    
    post {
		    always {
				    echo '항상 실행된다.'
		    }
		    
		    success {
				    echo '성공 시 실행된다.'
		    }
		    
		    failure {
				    echo '실패 시 실행된다.'
		    }
    }
}