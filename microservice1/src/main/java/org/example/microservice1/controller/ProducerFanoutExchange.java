package org.example.microservice1.controller;
import lombok.RequiredArgsConstructor;
import org.example.microservice1.models.Message;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerFanoutExchange {
    @Autowired
    private RabbitTemplate rabbitFanoutTemplate;
    @Autowired
    private FanoutExchange fanoutExchange;
    @PostMapping("/fanout-exchange")
    public String sendA(@RequestBody Message message){
        rabbitFanoutTemplate.convertAndSend(fanoutExchange.getName(),"",message);
        return "Message sent successfully !";
    }
}
