package org.example.microservice1.configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Route messages to multiple queues by matching a routing
@Configuration
public class RabbitMQTopicExchange {

    public static final String ROUTING_TOPIC_A = "routing-topic.A";
    public static final String ROUTING_TOPIC_B = "routing-topic.B";

    @Bean
    Queue queueTopicA(){
        return new Queue("queueTopic.A",false);
    }

    @Bean
    Queue queueTopicB(){
        return new Queue("queueTopic.B",false);
    }

    @Bean
    Queue allQueue(){
        return new Queue("queueTopic.all",false);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("exchange.topic");
    }

    @Bean
    Binding bindingTopicA(Queue queueTopicA, TopicExchange topicExchange){
        return BindingBuilder.bind(queueTopicA)
                .to(topicExchange)
                .with(ROUTING_TOPIC_A);
    }

    @Bean
    Binding bindingTopicB(Queue queueTopicB,TopicExchange topicExchange){
        return BindingBuilder.bind(queueTopicB)
                .to(topicExchange)
                .with(ROUTING_TOPIC_B);
    }

    @Bean
    Binding bindingTopicAll(Queue allQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(allQueue)
                .to(topicExchange)
                .with("routing-topic.*");
    }

    @Bean
    MessageConverter messageTopicConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTopicTemplate(ConnectionFactory topicFactory){
        RabbitTemplate rabbitTopicTemplate = new RabbitTemplate(topicFactory);
        rabbitTopicTemplate.setMessageConverter(messageTopicConverter());
        return rabbitTopicTemplate;
    }

}
