package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Entity
@Table(name = "pais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pais implements Serializable {

    private static final long serialVersionUID = 8857474659591525862L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O código não pode ser nulo")
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @NotNull(message = "O fone não pode ser nulo")
    @NotBlank(message = "O fone deve ser informado")
    @Length(min = 4, max = 4, message = "O fone deve ter exatamente {min} caracteres")
    @Column(name = "fone", nullable = false, length = 4)
    private String fone;

    @NotNull(message = "O ISO não pode ser nulo")
    @NotBlank(message = "O ISO deve ser informado")
    @Length(min = 2, max = 2, message = "O ISO deve ter exatamente {min} caracteres")
    @Column(name = "iso", nullable = false, length = 3)
    private String iso;

    @NotNull(message = "O ISO3 não pode ser nulo")
    @NotBlank(message = "O ISO3 deve ser informado")
    @Length(min = 3, max = 3, message = "O ISO3 deve ter exatamente {min} caracteres")
    @Column(name = "iso3", nullable = false, length = 3)
    private String iso3;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotNull(message = "O nome formal não pode ser nulo")
    @NotBlank(message = "O nome formal deve ser informado")
    @Length(max = 100, message = "O nome formal não pode ter mais de {max} caracteres")
    @Column(name = "nomeFormal", nullable = false, length = 100)
    private String nomeFormal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return id != null && id.equals(pais.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}