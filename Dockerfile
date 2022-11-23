FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV DB_USERNAME=db_user
ENV DB_PASSWORD=db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${DB_PASSWORD}", "-Dspring.datasource.username=${DB_USERNAME}", "-jar", "app.jar"]