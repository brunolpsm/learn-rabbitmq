package com.example.demo.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Endereco implements Serializable {

    private static final long serialVersionUID = -224332653064848415L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O apelido não pode ser nulo")
    @NotBlank(message = "O apelido deve ser informado")
    @Length(max = 20, message = "O apelido não pode ter mais de {max} caracteres")
    @Column(name = "nick_name")
    private String nickName;

    @NotNull(message = "O endereço não pode ser nulo")
    @NotBlank(message = "O endereço deve ser informado")
    @Length(max = 50, message = "O endereço não pode ter mais de {max} caracteres")
    @Column(name = "endereco")
    private String endereco;

    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número não pode ser em branco")
    @Length(max = 10, message = "O número não pode ter mais de {max} caracteres")
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;

    @Length(max = 20, message = "O complemento não pode ter mais de {max} caracteres")
    @Column(name = "complemento", length = 20)
    private String complemento;

    @NotNull(message = "O CEP não pode ser nulo")
    @NotBlank(message = "O CEP não pode ser em branco")
    @Length(max = 9, message = "O CEP não pode ter mais de {max} caracteres")
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @NotNull(message = "O bairro não pode ser nulo")
    @NotBlank(message = "O bairro não pode ser em branco")
    @Length(max = 40, message = "O bairro não pode ter mais de {max} caracteres")
    @Column(name = "bairro", length = 40, nullable = false)
    private String bairro;

    @Length(max = 30, message = "A referência não pode ter mais de {max} caracteres")
    @Column(name = "referencia", length = 30)
    private String referencia;

    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(
        name = "pessoa_id",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_endereco_pessoa_id")
    )
    private Pessoa pessoa;

    @NotNull(message = "O tipo de endereço deve ser informado")
    @ManyToOne
    @JoinColumn(
        name = "tipo_endereco_id",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_endereco_tipo_endereco_id")
    )
    private TipoEndereco tipoEndereco;

    @NotNull(message = "A cidade deve ser informada")
    @ManyToOne
    @JoinColumn(
        name = "cidade",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_endereco_cidade_id")
    )
    private Cidade cidade;
}