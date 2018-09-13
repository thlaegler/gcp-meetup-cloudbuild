FROM anapsix/alpine-java:8

ADD spring-boot-rest-example/target/spring-boot-rest-example.jar

EXPOSE 8080

ADD spring-boot-rest-example/target/spring-boot-rest-example.jar /app.jar

CMD java \
  -Xmx512m -server \
  -jar \
  -Djava.security.egd=file:/dev/./urandom \
  /app.jar