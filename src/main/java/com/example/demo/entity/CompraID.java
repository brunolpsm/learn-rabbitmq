package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompraID implements Serializable {

    private static final long serialVersionUID = 9027643839269084589L;

    @NotNull(message = "O número da nota deve ser informado")
    @Column(name = "numero_nota", nullable = false)
    private Integer numeroNota;

    @NotNull(message = "A pessoa jurídica deve ser informada")
    @ManyToOne
    @JoinColumn(
        name = "pessoa_juridica",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_compra_id_pessoa_juridica_id")
    )
    private PessoaJuridica pessoaJuridica;

    // Construtores
    public CompraID() {
    }

    public CompraID(Integer numeroNota, PessoaJuridica pessoaJuridica) {
        this.numeroNota = numeroNota;
        this.pessoaJuridica = pessoaJuridica;
    }

    // Getters e Setters
    public Integer getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Integer numeroNota) {
        this.numeroNota = numeroNota;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    // Métodos sobrescritos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraID compraID = (CompraID) o;
        return Objects.equals(numeroNota, compraID.numeroNota) &&
               Objects.equals(pessoaJuridica, compraID.pessoaJuridica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroNota, pessoaJuridica);
    }

    @Override
    public String toString() {
        return "CompraID{" +
               "numeroNota=" + numeroNota +
               ", pessoaJuridica=" + pessoaJuridica +
               '}';
    }
}