package com.scalability.likes_service.Controller;

import com.scalability.likes_service.Service.LikesConsumer;
import com.scalability.likes_service.Service.LikesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/load")
public class LoadTestController {
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    LikesConsumer consumer;

    @Autowired
    LikesProducer likesProducer;

    public LoadTestController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/likes/{postId}/{count}")
    public String generateLikes(@PathVariable String postId, @PathVariable int count) throws Exception {
        likesProducer.sendLikeEvent(postId, count);
        return "Dispatched " + count + " likes to Kafka for postId=" + postId;
    }


    // Check how many likes we SENT for a given postId
    @GetMapping("/likes/{postId}")
    public String getSentLikes(@PathVariable String postId) {
        return " SENT : "+likesProducer.getSentLike(postId) +" /nReceived : "+ consumer.getLikes(postId) ;
    }

    @PostMapping("/likes/single/{postId}/{count}")
    public String generateLikesSingleThread(@PathVariable String postId, @PathVariable int count) {
        likesProducer.generateLikesSingleThread(postId,count);
        return "Dispatched " + count + " likes (single-threaded) for postId=" + postId;
    }

}
