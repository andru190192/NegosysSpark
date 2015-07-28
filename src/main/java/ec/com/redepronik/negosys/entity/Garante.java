package ec.com.redepronik.negosys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "garantes")
public class Garante implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Persona cliente;
	private Integer orden;
	private Credito credito;

	public Garante() {
	}

	public Garante(Integer id, Persona cliente, Integer orden, Credito credito) {
		this.id = id;
		this.cliente = cliente;
		this.orden = orden;
		this.credito = credito;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Garante other = (Garante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Persona getCliente() {
		return cliente;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public Credito getCredito() {
		return this.credito;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "GARANTES_ID_GENERATOR", sequenceName = "GARANTES_ID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GARANTES_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(nullable = false)
	public Integer getOrden() {
		return orden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}