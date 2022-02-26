package org.example.microservice2.controller;
import lombok.extern.slf4j.Slf4j;
import org.example.microservice2.models.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "queue.A")
    private void receiveA(Message message){
        log.info("Message received form QUEUE A->{}",message);
    }

    @RabbitListener(queues = "queue.B")
    private void receiveB(Message message){
        log.info("Message received form QUEUE B->{}",message);
    }
}
