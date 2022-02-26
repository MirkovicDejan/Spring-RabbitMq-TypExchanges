package org.example.microservice1.controller;
import lombok.RequiredArgsConstructor;
import org.example.microservice1.models.Message;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @PostMapping("/direct-exchange-A")
    public String sendA(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.A",message);
        return "Message sent successfully !";
    }
    @PostMapping("/direct-exchange-B")
    public String sendB(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.B",message);
        return "Message sent successfully !";
    }

}
