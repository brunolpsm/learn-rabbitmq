package com.example.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.service.PedidoProducer;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoProducer pedidoProducer;
    private final AtomicLong counter = new AtomicLong(1);

    public PedidoController(PedidoProducer pedidoProducer) {
        this.pedidoProducer = pedidoProducer;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody PedidoDTO pedido) {
        PedidoDTO novoPedido = new PedidoDTO(counter.getAndIncrement(), pedido.produto(), pedido.quantidade(), pedido.preco());
        pedidoProducer.enviarPedido(novoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido enviado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API de pedidos está funcionando corretamente!");
    }
}

