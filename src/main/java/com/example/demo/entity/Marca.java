package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Marca implements Serializable {

    private static final long serialVersionUID = 8631132698320241732L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 40, message = "O nome não deve ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @Override
    public String toString() {
        return nome;
    }
}