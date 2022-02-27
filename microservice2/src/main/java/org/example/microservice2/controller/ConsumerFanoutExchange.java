package org.example.microservice2.controller;
import lombok.extern.slf4j.Slf4j;
import org.example.microservice2.models.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerFanoutExchange {

    @RabbitListener(queues ="queueFanout.A")
    private void receiveA(Message message){
        log.info("Message received form QUEUE FANOUT A->{}",message);
    }

    @RabbitListener(queues = "queueFanout.B")
    private void receiveB(Message message){
        log.info("Message received form QUEUE FANOUT B->{}",message);
    }
}
