ARG IMAGE_HOST
FROM java:8
ARG workdir=/var/app
VOLUME ${workdir}
ARG JAR_FILE
ADD target/${JAR_FILE} ${workdir}/pmapi.jar
WORKDIR ${workdir}
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "pmapi.jar"]