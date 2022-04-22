FROM openjdk:11-jdk-oracle

COPY docker/hospital-service-0.0.1-SNAPSHOT.jar /oze-hospital.jar

EXPOSE 5050
CMD java -Duser.timezone=UTC -jar oze-hospital.jar