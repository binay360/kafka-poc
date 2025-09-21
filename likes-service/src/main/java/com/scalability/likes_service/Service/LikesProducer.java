package com.scalability.likes_service.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class LikesProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConcurrentHashMap<String, Integer> sentLikeStore = new ConcurrentHashMap<>();

    public LikesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLikeEvent(String postId, int count) throws Exception {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var futures = new ArrayList<Future<?>>();
            for (int i = 0; i < count; i++) {
                int likeId = i+1;
                futures.add(executor.submit(() -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    sentLikeStore.merge(postId, 1, Integer::sum);
                    kafkaTemplate.send("likes", postId, "Like #" + likeId);
                }));
            }

            for (var f : futures) {
                f.get();
            }
        }
    }

    public Integer getSentLike(String postId){
        return sentLikeStore.getOrDefault(postId, 0);
    }

    public void generateLikesSingleThread(String postId, int count){
        for (int i = 0; i < count; i++) {
            kafkaTemplate.send("likes", postId, "Like #" + i);
            sentLikeStore.merge(postId, 1, Integer::sum);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
