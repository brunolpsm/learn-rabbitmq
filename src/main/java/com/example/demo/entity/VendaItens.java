package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "venda_itens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VendaItens implements Serializable {

    private static final long serialVersionUID = 4116893293014625599L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "A quantidade deve ser informada")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    @Column(name = "quantidade", nullable = false, columnDefinition = "decimal(12,2)")
    private Double quantidade;

    @NotNull(message = "O valor unitário deve ser informado")
    @Min(value = 0, message = "O valor unitário não pode ser negativo")
    @Column(name = "valor_unitario", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorUnitario;

    @NotNull(message = "O valor total deve ser informado")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorTotal;

    @NotNull(message = "A venda não pode ser nula")
    @ManyToOne
    @JoinColumn(
        name = "venda",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_venda_itens_venda_id")
    )
    private Venda venda;

    @NotNull(message = "O produto não pode ser nulo")
    @ManyToOne
    @JoinColumn(
        name = "produto",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_venda_itens_produto_id")
    )
    private Produto produto;

    // Método auxiliar para calcular o valor total automaticamente
    public void atualizarValorTotal() {
        if (quantidade != null && valorUnitario != null) {
            this.valorTotal = quantidade * valorUnitario;
        }
    }

    // Sobrescrevendo setters para manter consistência
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
        atualizarValorTotal();
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
        atualizarValorTotal();
    }
}