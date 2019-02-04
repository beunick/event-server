FROM openjdk:8-jdk-alpine
LABEL maintainer = "Yannick P. Tchatchoua"
#ENV JAVA_OPTS="-XX:PermSize=1024m -XX:MaxPermSize=512m"
VOLUME /tmp        
ARG JAR_FILE=target/events-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} event.jar

#ENV JAVA_OPTS="-XX:PermSize=1024m -XX:MaxPermSize=512m"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/event.jar"]
                                         

