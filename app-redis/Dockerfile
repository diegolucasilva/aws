FROM fabric8/java-alpine-openjdk11-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/app-redis.jar
WORKDIR /app
ENTRYPOINT java -jar app-redis.jar -Dspring.profiles.active=local