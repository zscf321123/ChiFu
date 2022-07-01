FROM openjdk:11.0.15-slim
#EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /chi.jar
#ENTRYPOINT ["java", "-jar","/chi.jar"]