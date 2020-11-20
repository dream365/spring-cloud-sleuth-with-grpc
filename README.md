# spring-cloud-sleuth-with-grpc

spring cloud sleuth를 spring grpc starter 환경에서 테스트한 Repo입니다.

## Scenario
1. (A)--getResult-->(B)--getResult-->(C)
2. (A)--getResult-->(D)

- A서버가 1, 2의 Reponse를 Zip하여 Client에게 반환

## Result
