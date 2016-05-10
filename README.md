# Get Started

Welcome to Okapi Information Retrieval System

## Okapi on AWS
Okapi web interface is available on AWS
```
http://ec2-54-191-146-173.us-west-2.compute.amazonaws.com:8080/
```

## Run Docker Container
Run the following Docker machine commands:
```bash
sudo docker pull sadrayan/okapi-ws-docker-file

sudo docker run --rm -it sadrayan/okapi-ws-docker-file
```

## How to run
In Docker container, run init.sh to start Okapi Web Service
```bash
cd /home/okapi-web-service
./init.sh
```

## How to use Web servic
You can you curl to access the Okapi Web Service API
```bash
# Get example
curl localhost:8080/database

#Post example
curl -H "Content-Type: application/x-www-form-urlencoded" -X POST -d 'parseTerms=united nations' localhost:8080/parseterms
```



