# 

## Model
www.msaez.io/#/storming/H6tLbk0ptNX2TgnchFK15fnL8xl1/cbb21a2c294f5219828d43c9f93e5e35

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- house
- store
- delivery
- customer


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- house
```
 http :8088/orders id="id" foodId="foodId" customerId="customerId" options="options" address="address" status="status" 
 http :8088/payments id="id" orderId="orderId" foodId="foodId" 
```
- store
```
 http :8088/storeOrders id="id" foodId="foodId" orderId="orderId" status="status" 
```
- delivery
```
 http :8088/deliveries id="id" address="address" orderId="orderId" riderId="riderId" 
```
- customer
```
 http :8088/notificationLogs id="id" customerId="customerId" message="message" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

