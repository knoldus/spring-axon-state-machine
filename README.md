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

bucketMaster events(CreateBucket) 
```
kafkacat -P -b localhost:9092 -t classmaster_buckets  src/main/resources/createbucket1.json

```

```
kafkacat -P -b localhost:9092 -t classmaster_buckets  src/main/resources/createbucket2.json

```


eligibleTradeData events(TradeEligible)
```
kafkacat -P -b localhost:9092 -t feed_trade  src/main/resources/trade1.json

```

```
kafkacat -P -b localhost:9092 -t feed_trade  src/main/resources/trade2.json

```

```
kafkacat -P -b localhost:9092 -t feed_trade  src/main/resources/trade3.json

```
--------
-------- trade5

Bucket master sync events(ToldBucketCutoff)
Bucket1 -
```
kafkacat -P -b localhost:9092 -t master_buckets  src/main/resources/toldbucket1cutoff.json
```
Bucket2 -
```
kafkacat -P -b localhost:9092 -t master_buckets  src/main/resources/toldbucket2cutoff.json
```

Bucket master sync events(ToldBucketCutoff)
Bucket1
```
kafkacat -P -b localhost:9092 -t master_buckets  src/main/resources/masterLock1.json
```
Bucket2
```
kafkacat -P -b localhost:9092 -t master_buckets  src/main/resources/masterLock2.json

```

####MongoDb database operations - 

open mongo shell 
```
mongo admin -u root -p rootpassword
```

show databases
```show dbs```

switch to event store database 
```use axonframework```

show collections in databases
```show collections```

show documents inside domainevents collection
```db.domainevents.find()```

switch to bucketEntity store database
```use axonframework_bucket```

show documents inside bucketEntity collection
```db.bucketEntity.find()```


To see output of netted trade 
```
kafkacat -b localhost:9092 -t net_trade -C 
```