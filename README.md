# Likes Service – Kafka Learning Project

## 📌 Overview
This project is a **Spring Boot microservice** that simulates managing "likes" on posts.  
It integrates with **Apache Kafka** to produce and consume events in a distributed, scalable way.  

The application is **containerized with Docker**, allowing multiple instances of the service to run simultaneously for load testing and Kafka exploration.  

---

## 🚀 Features
- REST API to load likes for posts  
- Publishes "like" events to Kafka  
- Works with Kafka + Zookeeper + Kafka UI containers  
- Scales horizontally by running multiple service instances  
- Demonstrates Kafka’s consumer group and partitioning behavior  
- Uses **Java 21 virtual threads** for lightweight concurrency  
- Achieved **100,000 likes in a single request** using virtual threads

---

## 🛠️ Tech Stack
- **Java 21 / Spring Boot** (with virtual threads)  
- **Apache Kafka + Zookeeper**  
- **Docker & Docker Compose**  
- **Kafka UI** for monitoring  

---

## 📖 What I Learned
Working on this project helped me understand core **Kafka and concurrency concepts** in practice:  

- ✅ **How Kafka works** end-to-end (producer → broker → consumer)  
- ✅ **Kafka partitions** and how they enable scalability and parallelism  
- ✅ Running the service on a **single instance** vs. **multiple instances**  
- ✅ **Consumer groups** and how Kafka distributes messages among them  
- ✅ The importance of **horizontal scaling** and **load balancing** in event-driven systems  
- ✅ Difference between **single-threading vs multithreading** in handling requests  
- ✅ How **Java 21 virtual threads** simplify concurrency and improve scalability  
- ✅ Handling **100k concurrent likes** smoothly with virtual threads 
