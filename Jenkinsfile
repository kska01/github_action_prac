pipeline {
    agent any

    stages {
        stage('Copy Environment Variable File') {
            steps {
	              script {
									// withCredentials : Credentials 서비스를 활용하겠다
									// file : secret file을 불러오겠다
									// credentialsId : 불러올 file의 식별 ID
									// variable : 블록 내부에서 사용할 변수명명
									withCredentials([file(credentialsId: 'env-file', variable: 'env_file')]) {
										// 젠킨스 서비스 내 .env 파일을 
										// 파이프라인 프로젝트 내부로 복사
										sh 'cp $env_file .env'

										// 파일 권한 설정
										// 소유자 : 읽기 + 쓰기
										// 그외 : 읽기 권한
										// 권한 : 읽기4 쓰기2 실행1

										sh 'chmod 644 .env'
									}
								}
            } 
        }

				stage("Docker Image Build & Container Run") {
					steps {
						script {
							sh 'docker compose build'
							sh 'docker compose up -d'
						}
					}
				}
		}
}