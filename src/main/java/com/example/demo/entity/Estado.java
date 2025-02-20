package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Entity
@Table(name = "estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Estado implements Serializable {

    private static final long serialVersionUID = -1656685821951989692L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotNull(message = "A UF não pode ser nula")
    @NotBlank(message = "A UF deve ser informada")
    @Length(min = 2, max = 2, message = "A UF deve ter exatamente {min} caracteres")
    @Column(name = "iso", nullable = false, length = 3)
    private String uf;

    @NotNull(message = "O país deve ser informado")
    @ManyToOne
    @JoinColumn(
        name = "pais",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_estado_pais_id")
    )
    private Pais pais;
}