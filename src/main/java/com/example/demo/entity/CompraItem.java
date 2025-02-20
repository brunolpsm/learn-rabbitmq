package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "compra_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class CompraItem implements Serializable {

    private static final long serialVersionUID = 441763397282713794L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "A quantidade deve ser informada")
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    @Column(name = "quantidade", nullable = false, columnDefinition = "numeric(10,2)")
    private Double quantidade;

    @NotNull(message = "O valor unitário deve ser informado")
    @Min(value = 0, message = "O valor unitário não pode ser negativa")
    @Column(name = "valor_unitario", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valorUnitario;

    @NotNull(message = "O valor total deve ser informado")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valorTotal;

    @NotNull(message = "O produto deve ser informado")
    @ManyToOne
    @JoinColumn(
        name = "produto",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_compra_item_produto_id")
    )
    private Produto produto;

    @NotNull(message = "A compra deve ser informada")
    @ManyToOne
    @JoinColumn(name = "compra", nullable = false) // Ajuste o nome da coluna se necessário
    private Compra compra;

    // Setter customizado para atualizar valorTotal automaticamente
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
        atualizarValorTotal();
    }

    // Setter customizado para atualizar valorTotal automaticamente
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
        atualizarValorTotal();
    }

    // Método auxiliar para calcular o valor total
    private void atualizarValorTotal() {
        if (this.quantidade != null && this.valorUnitario != null) {
            this.valorTotal = this.quantidade * this.valorUnitario;
        }
    }
}