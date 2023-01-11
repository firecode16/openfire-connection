FROM java:8

LABEL maintainer="devops@gmail.com"

VOLUME /tmp

EXPOSE 8081

ADD $PWD/deployments/xmpp-login-service/target/openfire-connection-0.0.1-SNAPSHOT.jar openfire-connection-service.jar

RUN bash -c 'touch /openfire-connection-service.jar'

ENV JAVA_OPTS=""

# Run the jar file
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /openfire-connection-service.jar"]