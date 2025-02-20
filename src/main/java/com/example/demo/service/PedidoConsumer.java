package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.PedidoDTO;

@Service
public class PedidoConsumer {
	@RabbitListener(queues = RabbitMQConfig.FILA_PEDIDOS)
	public void processarPedido(PedidoDTO pedido) {
		System.out.println("✅ Pedido recebido e processado: " + pedido);
	}
}