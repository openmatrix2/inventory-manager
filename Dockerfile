## Create image from openjdk:8 base image
FROM openjdk:8

## ADD <src>... <dest>
## The ADD instruction copies new files, directories from <src> and adds them to 
## the filesystem of the image at the path <dest>.
ADD ./target/inventory-manager-0.0.1.jar inventory-manager-0.0.1.jar

## Expose this port on the docker virtual network
## Still need to use -P to open/forward ports on host to these docker ports
EXPOSE 9003

## ENTRYPOINT ["executable", "param1", "param2"]
ENTRYPOINT ["java","-jar","inventory-manager-0.0.1.jar"]
