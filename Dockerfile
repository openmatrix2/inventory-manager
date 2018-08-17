FROM openjdk:8
ADD target/inventory-manager-0.0.1.jar inventory-manager-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","inventory-manager-0.0.1.jar"]