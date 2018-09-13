ARG BASE_IMAGE
FROM $BASE_IMAGE

ARG SERVICE_NAME

# Spring environment variables will be picked by Spring automatically
ENV SPRING_CLOUD_CONFIG_LABEL master
ENV SPRING_PROFILES_ACTIVE default

# Java options environment variable from base image in use
ENV JAVA_OPTS -server -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseParallelGC -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m

EXPOSE 8080

ADD target/${SERVICE_NAME}-service.jar /app.jar

CMD java \
  $JAVA_OPTS \
  -jar \
  -Djava.security.egd=file:/dev/./urandom \
  /app.jar
  