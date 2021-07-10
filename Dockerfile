FROM java:8
EXPOSE 8080
ADD target/sysadap.jar sysadap.jar 
ENTRYPOINT ["java","-jar","sysadap.jar"]