package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade que representa um arquivo associado a um produto.
 *
 * @author Bruno Lopes
 */
@Entity
@Table(name = "arquivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Arquivo implements Serializable {

	private static final long serialVersionUID = 5848579673065195577L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "O nome deve ser informado")
	@NotBlank(message = "O nome não pode ser em branco")
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@NotNull(message = "A descrição deve ser informada")
	@NotBlank(message = "A descrição não pode ser em branco")
	@Column(name = "descricao", nullable = false, length = 50)
	private String descricao;

	@NotNull(message = "O arquivo deve ser informado")
	@Lob
	@Column(name = "arquivo", nullable = false)
	private byte[] arquivo;

	@NotNull(message = "O produto deve ser informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_arquivo_produto_id"))
	private Produto produto;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Arquivo arquivo = (Arquivo) o;
		return Objects.equals(id, arquivo.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Arquivo{" + "id=" + id + ", nome='" + nome + '\'' + ", descricao='" + descricao + '\'' + '}';
	}
}