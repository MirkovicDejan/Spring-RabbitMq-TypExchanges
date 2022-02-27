package org.example.microservice1.controller;
import lombok.RequiredArgsConstructor;
import org.example.microservice1.models.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerTopicExchange {
    @Autowired
    private RabbitTemplate rabbitTopicTemplate;
    @Autowired
    private TopicExchange topicExchange;

    @PostMapping("/topic-exchange-topicA")
    public String sendTopicA(@RequestBody Message message){
        rabbitTopicTemplate.convertAndSend(topicExchange.getName(),"routing-topic.A",message);
        return "Message sent successfully !";
    }
    @PostMapping("/topic-exchange-topicB")
    public String sendTopicB(@RequestBody Message message){
        rabbitTopicTemplate.convertAndSend(topicExchange.getName(),"routing-topic.B",message);
        return "Message sent successfully !";
    }

}
