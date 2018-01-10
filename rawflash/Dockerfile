FROM goyalzz/ubuntu-java-8-maven-docker-image
RUN apt-get update -y && apt-get install dcraw -y
COPY ./* server/
WORKDIR server/
RUN mvn clean install
EXPOSE 4000
CMD ["mvn", "exec:java"]
