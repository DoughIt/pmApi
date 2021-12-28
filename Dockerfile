ARG IMAGE_HOST
FROM java:8

ARG JAR_FILE
ADD target/${JAR_FILE} /var/app/pmapi.jar
WORKDIR /var/app/
ENTRYPOINT exec java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap $JAVA_OPTS $SKYWALKING_OPTS  -jar pmapi.jar