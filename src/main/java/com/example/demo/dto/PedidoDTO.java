package com.example.demo.dto;

import java.math.BigDecimal;

public record PedidoDTO(Long id, String produto, int quantidade, BigDecimal preco)  {}