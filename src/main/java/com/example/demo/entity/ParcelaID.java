package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ParcelaID implements Serializable {

    private static final long serialVersionUID = 6393916946597259300L;

    @NotNull(message = "O número não pode ser nulo")
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @NotNull(message = "A venda não pode ser nula")
    @ManyToOne
    @JoinColumn(
        name = "venda",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_parcela_id_venda_id")
    )
    private Venda venda;
}