package org.knoldus.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class SpringAxonStateMachineExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAxonStateMachineExampleApplication.class, args);
	}

}
