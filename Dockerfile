FROM java:8
EXPOSE 8085
ADD /target/ekrutes-data-broker-1.0-SNAPSHOT.jar ekrutes-data-broker-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ekrutes-data-broker-1.0-SNAPSHOT.jar"]