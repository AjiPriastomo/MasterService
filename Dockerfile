FROM adoptopenjdk/openjdk8:x86_64-alpine-jre8u-nightly
EXPOSE 8080
ADD build/libs/MasterService-0.0.1-SNAPSHOT.jar MasterService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "MasterService-0.0.1-SNAPSHOT.jar"]