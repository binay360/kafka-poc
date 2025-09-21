package com.scalability.likes_service.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic likesTopic(){
        return new NewTopic("likes",12,(short)1);
    }
}
