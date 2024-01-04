FROM 10.248.158.7/library/openjdk:8-jdk-alpine

WORKDIR /

COPY ./serviceEtc-0.0.1-SNAPSHOT.jar ./serviceEtc-0.0.1-SNAPSHOT.jar

ENV TZ="Asia/Ho_Chi_Minh"

EXPOSE 8989

ENTRYPOINT ["java","-jar","/serviceEtc-0.0.1-SNAPSHOT.jar"]
