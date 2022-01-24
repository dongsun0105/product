# 상품 관리
- 상품 조회/등록 REST API 구현
- REST API 버저닝 고려 : URI Versioning
- 쓰로틀링 오류 처리 고려 : Guava RateLimiter 활용
- Repository 타입 변경 고려 : JpaRepository 인터페이스 활용

## Environment
- JAVA 11
- Spring Boot 2.6.3
- Gradle 7.3.3
- H2 Database
- JPA
- JUnit5
- Goolge Guava 31.0.1

## Build
```
gradle build
```

## Execution
```
java -jar build/libs/product-1.0.0.jar
```
