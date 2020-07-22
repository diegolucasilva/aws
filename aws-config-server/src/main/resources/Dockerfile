FROM openjdk:11

EXPOSE 8089

ADD /target/aws-config-server-0.0.1-SNAPSHOT.jar aws-config-server.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/aws-config-server.jar"]