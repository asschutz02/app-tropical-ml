FROM openjdk

WORKDIR /app

COPY build/libs/tropical-0.0.1-SNAPSHOT.jar /app/tropical.jar

EXPOSE 9096

ENTRYPOINT ["java", "-jar", "tropical.jar"]