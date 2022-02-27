package org.example.microservice2.controller;
import lombok.extern.slf4j.Slf4j;
import org.example.microservice2.models.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerTopicExchange {

    @RabbitListener(queues ="queueTopic.A")
    private void receiveA(Message message){
        log.info("Message received form QUEUE TOPIC A->{}",message);}

    @RabbitListener(queues = "queueTopic.B")
    private void receiveB(Message message){
        log.info("Message received form QUEUE TOPIC B->{}",message);
    }

    @RabbitListener(queues = "queueTopic.all")
    private void receiveAll(Message message){
        log.info("Message received form QUEUE TOPIC ALL->{}",message);
    }
}
