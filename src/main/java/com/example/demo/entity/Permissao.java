package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Entity
@Table(name = "permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "nome")
public class Permissao implements Serializable {

    private static final long serialVersionUID = -6537000076897353638L;

    @Id
    @NotBlank(message = "O nome não pode ser em branco")
    @NotNull(message = "O nome não pode ser nulo")
    @Length(max = 30, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotBlank(message = "A descrição não pode ser em branco")
    @NotNull(message = "A descrição não pode ser nula")
    @Length(max = 40, message = "A descrição não pode ter mais de {max} caracteres")
    @Column(name = "descricao", length = 40, nullable = false)
    private String descricao;
}