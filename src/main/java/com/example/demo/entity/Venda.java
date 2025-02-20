package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venda implements Serializable {

    private static final long serialVersionUID = -2289955024658475643L;

    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "A data não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Calendar data;

    @NotNull(message = "O valor total deve ser informado")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorTotal;

    @NotNull(message = "A quantidade de parcelas deve ser informada")
    @Min(value = 0, message = "A quantidade de parcelas não pode ser negativa")
    @Column(name = "parcelas", nullable = false)
    private Integer parcelas;

    @NotNull(message = "A pessoa física não pode ser nula")
    @ManyToOne
    @JoinColumn(
        name = "pessoa_fisica",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_venda_pessoa_fisica_id")
    )
    private PessoaFisica pessoaFisica;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<VendaItens> itens = new ArrayList<>();

    @OneToMany(mappedBy = "parcelaID.venda", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Parcela> listaParcelas = new ArrayList<>();

//    public Venda() {
//        this.valorTotal = 0.0;
//    }

    public void gerarParcelas() {
        Double valorParcela = this.valorTotal / this.parcelas;
        for (int i = 1; i <= this.parcelas; i++) {
            Parcela p = new Parcela();
            ParcelaID id = new ParcelaID();
            id.setNumero(i);
            id.setVenda(this);
            p.setParcelaID(id);
            p.setValor(valorParcela);
            Calendar vencimento = (Calendar) this.data.clone();
            vencimento.add(Calendar.MONTH, i);
            p.setVencimento(vencimento);
            this.listaParcelas.add(p);
        }
    }

    public void adicionarItem(VendaItens obj) {
        obj.setVenda(this);
        this.valorTotal += obj.getValorTotal();
        this.itens.add(obj);
    }

    public void removerItem(int index) {
        VendaItens obj = this.itens.get(index);
        this.valorTotal -= obj.getValorTotal();
        this.itens.remove(index);
    }
}