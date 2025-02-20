package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "pessoa_fisica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica extends Pessoa {

    @NotNull(message = "O RG não pode ser nulo")
    @NotBlank(message = "O RG não pode ser em branco")
    @Length(max = 10, message = "O RG não pode ter mais de {max} caracteres")
    @Column(name = "RG", length = 10, nullable = false)
    private String rg;

    @NotNull(message = "O CPF não pode ser nulo")
    @NotBlank(message = "O CPF não pode ser em branco")
    @Length(max = 14, message = "O CPF não pode ter mais de {max} caracteres")
    @CPF(message = "O CPF deve ser válido")
    @Column(name = "CPF", length = 14, nullable = false)
    private String cpf;

    @NotNull(message = "O nascimento não pode ser nulo")
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento", nullable = false)
    private Calendar nascimento;

    @NotNull(message = "O nome de usuário não pode ser nulo")
    @NotBlank(message = "O nome de usuário não pode ser em branco")
    @Length(max = 20, message = "O nome de usuário não pode ter mais de {max} caracteres")
    @Column(name = "nome_usuario", length = 20, nullable = false, unique = true)
    private String nomeUsuario;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser em branco")
    @Length(max = 10, message = "A senha não pode ter mais de {max} caracteres")
    @Column(name = "senha", length = 10, nullable = false)
    private String senha;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "desejos", joinColumns = @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false), uniqueConstraints = @UniqueConstraint(columnNames = {
            "pessoa_fisica", "produto" }))
    private List<Produto> desejos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permissoes", joinColumns = @JoinColumn(name = "nome_usuario", referencedColumnName = "nome_usuario", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_fisica_nome_usuario_id")), inverseJoinColumns = @JoinColumn(name = "permissao", referencedColumnName = "nome", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_fisica_permissao_id")), uniqueConstraints = @UniqueConstraint(columnNames = {
            "nome_usuario", "permissao" }))
    private List<Permissao> permissoes = new ArrayList<>();

}