package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Entity
@Table(name = "tipo_endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoEndereco implements Serializable {

    private static final long serialVersionUID = -178096335831525521L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição deve ser informada")
    @Length(max = 30, message = "A descrição não pode ter mais de {max} caracteres")
    @Column(name = "descricao", length = 30, nullable = false)
    private String descricao;

    @Override
    public String toString() {
        return descricao;
    }
}