package com.Devansh.Journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

    @Bean
    public PlatformTransactionManager Tran(MongoDatabaseFactory mdTran){
        return new MongoTransactionManager(mdTran);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
