package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String FILA_PEDIDOS = "fila.pedidos";
    public static final String EXCHANGE_PEDIDOS = "exchange.pedidos";
    public static final String ROUTING_KEY_PEDIDOS = "pedidos.novos";

    @Bean
    Queue queue() {
        return new Queue(FILA_PEDIDOS, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_PEDIDOS);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_PEDIDOS);
    }

    // ✅ Define Jackson JSON Converter instead of default Java Serialization
    @Bean
     MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
     RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
