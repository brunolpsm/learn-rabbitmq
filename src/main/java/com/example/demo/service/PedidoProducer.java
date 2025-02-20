package com.example.demo.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.PedidoDTO;

@Service
public class PedidoProducer {
	private final RabbitTemplate rabbitTemplate;

	public PedidoProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void enviarPedido(PedidoDTO pedido) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_PEDIDOS, RabbitMQConfig.ROUTING_KEY_PEDIDOS, pedido);
		System.out.println("📨 Pedido enviado: " + pedido);
	}
}