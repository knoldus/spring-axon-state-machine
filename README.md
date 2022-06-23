# spring-axon-state-machine


### How to Run- 

install kafkacat - ```sudo apt install kafkacat```
install mongo client - ```sudo apt install mongodb-clients```

MongoDb is used as event store.


#### Start kafka
```
docker-compose up -d
```

#### Start the spring-boot application -

Run the main class from intellij
  or
```
mvn spring-boot:run
```

#### send event to Kafka 


tradeAd event(TradeEligible)
```
kafkacat -P -b localhost:9092 -t trades_buckets  src/main/resources/tradeEligible.json

```
Bucket master sync event(ToldBucketCutoff)
```
kafkacat -P -b localhost:9092 -t master_buckets  src/main/resources/toldbucketcutoff.json
```
open mongo shell 
```mongo admin -u root -p rootpassword
```

show databases
```show dbs```

swithch to event store database 
```use axonframework```

show collectiopns in databases
```show collections```

show documents inside collection
```db.domainevents.find()```
