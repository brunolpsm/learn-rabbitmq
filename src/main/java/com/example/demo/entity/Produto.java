package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto implements Serializable {

    private static final long serialVersionUID = -5750891867618889934L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não deve ter mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @NotNull(message = "O preço deve ser informado")
    @Column(name = "preco", nullable = false, columnDefinition = "decimal(12,2)")
    private Double preco;

    @NotNull(message = "A quantidade em estoque deve ser informada")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    @Column(name = "quantidade_estoque", nullable = false, columnDefinition = "decimal(12,2)")
    private Double quantidadeEstoque;

    @NotNull(message = "A categoria deve ser informada")
    @ManyToOne
    @JoinColumn(
        name = "categoria",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_produto_categoria_id")
    )
    private Categoria categoria;

    @NotNull(message = "A marca deve ser informada")
    @ManyToOne
    @JoinColumn(
        name = "marca",
        referencedColumnName = "id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_produto_marca_id")
    )
    private Marca marca;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "desejos",
        joinColumns = @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_produto_id")),
        inverseJoinColumns = @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_pessoa_fisica_id")),
        uniqueConstraints = @UniqueConstraint(columnNames = {"pessoa_fisica", "produto"})
    )
    private List<PessoaFisica> desejam = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Foto> fotos = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Arquivo> arquivos = new ArrayList<>();

    public void adicionarFoto(Foto obj) {
        obj.setProduto(this);
        this.fotos.add(obj);
    }

    public void removerFoto(int index) {
        this.fotos.remove(index);
    }

    public void adicionarArquivo(Arquivo obj) {
        obj.setProduto(this);
        this.arquivos.add(obj);
    }

    public void removerArquivo(int index) {
        this.arquivos.remove(index);
    }

    @Override
    public String toString() {
        return nome;
    }
}