FROM maven
RUN apt-get update -y && apt-get install dcraw -y
COPY ./ server/
RUN mkdir rawflash_cache/
WORKDIR server/
RUN mvn clean install
EXPOSE 4000
RUN ls
CMD ["mvn", "exec:java"]
