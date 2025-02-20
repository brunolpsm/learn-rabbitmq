package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "parcela")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "parcelaID")
public class Parcela implements Serializable {

    private static final long serialVersionUID = -6812947445143343396L;

    @EmbeddedId
    private ParcelaID parcelaID;

    @NotNull(message = "O valor deve ser informado")
    @Min(value = 0, message = "O valor não pode ser negativo")
    @Column(name = "valor", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valor;

    @NotNull(message = "O vencimento não pode ser nulo")
    @Temporal(TemporalType.DATE)
    @Column(name = "vencimento", nullable = false)
    private Calendar vencimento;

    @Min(value = 0, message = "O valor do pagamento não pode ser negativo")
    @Column(name = "valor_pagamento", columnDefinition = "decimal(12,2)")
    private Double valorPagamento;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_pagamento")
    private Calendar dataPagamento;
}