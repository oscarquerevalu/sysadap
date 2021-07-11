# use latest Node image
FROM openjdk:8-alpine

# Install lots of fonts
ENV LANG en_GB.UTF-8
RUN apk add --update ttf-dejavu ttf-droid ttf-freefont ttf-liberation ttf-ubuntu-font-family && rm -rf /var/cache/apk/*
EXPOSE 8080
ADD target/sysadap.jar sysadap.jar 
ENTRYPOINT ["java","-jar","sysadap.jar"]