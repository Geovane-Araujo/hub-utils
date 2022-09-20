FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
WORKDIR /api
ADD target/hub-utils-0.0.1.jar app.jar
EXPOSE 8090
RUN bash -c 'touch ./app.jar'
ENTRYPOINT ["java", "-jar", "./app.jar"]