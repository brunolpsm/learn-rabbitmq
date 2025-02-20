package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "pessoa_juridica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridica extends Pessoa {

    @NotNull(message = "A inscrição estadual não pode ser nula")
    @NotBlank(message = "Informe a inscrição estadual")
    @Length(max = 15, message = "A inscrição estadual deve possuir no máximo {max} caracteres")
    @Column(name = "ie", length = 15, nullable = false)
    private String ie;

    @NotNull(message = "O CNPJ não pode ser nulo")
    @NotBlank(message = "Informe o CNPJ")
    @CNPJ(message = "CNPJ inválido")
    @Length(min = 18, max = 18, message = "O CNPJ deve possuir exatamente {max} caracteres")
    @Column(name = "cnpj", length = 18, nullable = false)
    private String cnpj;
}