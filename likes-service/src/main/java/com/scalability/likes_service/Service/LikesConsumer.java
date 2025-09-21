package com.scalability.likes_service.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class LikesConsumer {
    private final ConcurrentHashMap<String, Integer> likeStore = new ConcurrentHashMap<>();

    @KafkaListener(topics = "likes")
    public void consumeLike(@Header(KafkaHeaders.RECEIVED_KEY) String postId,
                            @Payload String message) {
        likeStore.merge(postId, 1, Integer::sum);
        System.out.println("Post -> " + postId +
                " received " + message +
                " | total likes = " + likeStore.get(postId));
    }
    public int getLikes(String postId) {
        return likeStore.getOrDefault(postId, 0);
    }
}
