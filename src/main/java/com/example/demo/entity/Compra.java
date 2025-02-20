package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Entidade que representa uma compra com itens associados.
 *
 * @author Bruno Lopes
 */
@Entity
@Table(name = "compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra implements Serializable {

	private static final long serialVersionUID = -3007121438073378540L;

	@EmbeddedId
	private CompraID id;

	@NotNull(message = "A data deve ser informada")
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Calendar data;

	@NotNull(message = "O valor total deve ser informado")
	@Column(name = "valor_total", nullable = false, columnDefinition = "numeric(10,2)")
	private Double valorTotal;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@Builder.Default
	private List<CompraItem> itens = new ArrayList<>();

	public void adicionarItem(CompraItem obj) {
		obj.setCompra(this);
		this.valorTotal += obj.getValorTotal();
		this.itens.add(obj);
	}

	public void removerItem(int index) {
		CompraItem obj = this.itens.get(index);
		this.valorTotal -= obj.getValorTotal();
		this.itens.remove(index);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Compra compra = (Compra) o;
		return Objects.equals(id, compra.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Compra{id=" + id + ", data=" + data.getTime() + ", valorTotal=" + valorTotal + "}";
	}
}