FROM frolvlad/alpine-oraclejdk8
EXPOSE 8080
ADD /build/libs/spring-example-1.0.jar /opt/api.jar
ENTRYPOINT exec java $JAVA_OPTS $APPDYNAMICS -jar /opt/api.jar
