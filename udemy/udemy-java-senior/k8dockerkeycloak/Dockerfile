FROM openjdk:11-slim as build
MAINTAINER madalinaotvos91
COPY target/k8dockerkeycloak-0.0.1-SNAPSHOT.jar k8dockerkeycloack-0.1.0.jar
ENTRYPOINT ["java","-jar","/k8dockerkeycloack-0.1.0.jar"]