package org.example.microservice1.configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Route messages to all the queues bound to it
@Configuration
public class RabbitMQFanoutExchange {

    public static final String ROUTING_FANOUT_A = "routing-fanout.A";
    public static final String ROUTING_FANOUT_B = "routing-fanout.B";

    @Bean
    Queue queueFanoutA(){
        return new Queue("queueFanout.A",false);
    }
    @Bean
    Queue queueFanoutB(){
        return new Queue("queueFanout.B",false);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    Binding bindingFanoutA(Queue queueFanoutA, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueFanoutA)
                .to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutB(Queue queueFanoutB,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueFanoutB)
                .to(fanoutExchange);
    }

    @Bean
    MessageConverter messageFanoutConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitFanoutTemplate(ConnectionFactory fanoutFactory){
        RabbitTemplate rabbitFanoutTemplate = new RabbitTemplate(fanoutFactory);
        rabbitFanoutTemplate.setMessageConverter(messageFanoutConverter());
        return rabbitFanoutTemplate;
    }
}
