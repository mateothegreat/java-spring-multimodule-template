FROM openjdk:8-jre-alpine

RUN apk add --update curl

COPY application/build/libs/*.jar /application.jar

CMD [ "java", "-jar", "/application.jar" ]
