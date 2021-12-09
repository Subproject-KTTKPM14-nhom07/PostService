FROM openjdk:11
LABEL maintainer="postservice"
ADD target/Post-0.0.1-SNAPSHOT.jar springboot-docker-postservice.jar
ENTRYPOINT ["java","-jar","springboot-docker-postservice.jar"]