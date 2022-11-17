ARG MSVC_NAME=nucleo-dash-msvc-gateway

FROM openjdk:17-alpine as builder

ARG MSVC_NAME

WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .
RUN sed -i 's/\r$//' mvnw

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline


COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests



FROM openjdk:17-alpine

WORKDIR /app

RUN mkdir ./logs
ARG PORT_APP=8090
ARG MSVC_NAME
ARG TARGET_FOLDER=/app/$MSVC_NAME/target

COPY  --from=builder $TARGET_FOLDER/nucleo-dash-msvc-gateway-0.0.1-SNAPSHOT.jar .

ARG CERT=./server.crt

#import cert into java
COPY $CERT /app
RUN keytool -importcert -file server.crt -alias server.crt -cacerts -storepass changeit -noprompt




ENV PORT $PORT_APP

EXPOSE $PORT
#CMD sleep 20 && java -jar msvc-usuarios-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "nucleo-dash-msvc-gateway-0.0.1-SNAPSHOT.jar"]
