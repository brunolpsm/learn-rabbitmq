package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade que representa uma cidade associada a um estado.
 *
 * @author Bruno Lopes
 */
@Entity
@Table(name = "cidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cidade implements Serializable {

	private static final long serialVersionUID = -2364404356742076390L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O nome deve ser informado")
	@NotNull(message = "O nome não pode ser nulo")
	@Length(max = 50, message = "O nome não deve ter mais de {max} caracteres")
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@NotNull(message = "O estado deve ser informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_cidade_estado_id"))
	private Estado estado;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cidade cidade = (Cidade) o;
		return Objects.equals(id, cidade.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return nome;
	}
}