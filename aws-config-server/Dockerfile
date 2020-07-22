FROM fabric8/java-alpine-openjdk11-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/aws-config-server.jar
WORKDIR /app
ENTRYPOINT java -jar aws-config-server.jar