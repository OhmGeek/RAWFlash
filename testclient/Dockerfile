FROM kyma/docker-nginx
COPY src/ /var/www
RUN apt-get update -y
RUN apt-get install curl -y
RUN curl -sL https://deb.nodesource.com/setup_6.x | bash
RUN apt-get install -y nodejs
WORKDIR /var/www
RUN npm install
CMD 'nginx'
EXPOSE 80 443
