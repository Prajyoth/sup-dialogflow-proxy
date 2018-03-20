FROM openjdk:latest
MAINTAINER Prajyoth Bhandary
ADD target/sup-dialogflow-proxy.jar /default/sup-dialogflow-proxy.jar
USER root
RUN chown root:root /default
RUN chmod 777 /default
EXPOSE 9000
CMD ["java","-jar","/default/sup-dialogflow-proxy.jar"]