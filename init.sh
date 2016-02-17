#!/bin/bash

# initialize global variables
source /home/okapi/environmentSettings.bshrc


# Package the Web Service Project
#cd /home/okapi-web-service/ && mvn install

# Run Okapi WS - avaiable at localhost:8080
java -jar /home/okapi-web-service/output/okapi-web-service-0.1.0.jar
