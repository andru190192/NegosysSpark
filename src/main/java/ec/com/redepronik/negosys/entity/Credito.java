package ec.com.redepronik.negosys.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "creditos")
public class Credito implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal interes;
	private Integer meses;
	private BigDecimal monto;
	private Boolean pagado;
	private Factura factura;
	private List<DetalleCredito> detallesCreditos;
	private List<Garante> garantes;

	public Credito() {
	}

	public Credito(Integer id, BigDecimal interes, Integer meses,
			BigDecimal monto, Boolean pagado, Factura factura,
			List<DetalleCredito> detallesCreditos, List<Garante> garantes) {
		this.id = id;
		this.interes = interes;
		this.meses = meses;
		this.monto = monto;
		this.pagado = pagado;
		this.factura = factura;
		this.detallesCreditos = detallesCreditos;
		this.garantes = garantes;
	}

	public DetalleCredito addDetalleCredito(DetalleCredito detalleCredito) {
		getDetallesCreditos().add(detalleCredito);
		detalleCredito.setCredito(this);

		return detalleCredito;
	}

	public Garante addGarante(Garante garante) {
		getGarantes().add(garante);
		garante.setCredito(this);

		return garante;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credito other = (Credito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "credito")
	// @IndexColumn(name = "orden", base = 1)
	public List<DetalleCredito> getDetallesCreditos() {
		return detallesCreditos;
	}

	@OneToOne
	@JoinColumn(nullable = false)
	public Factura getFactura() {
		return this.factura;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "credito")
	// @IndexColumn(name = "orden", base = 1)
	public List<Garante> getGarantes() {
		return this.garantes;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "CREDITOS_ID_GENERATOR", sequenceName = "CREDITOS_ID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDITOS_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(nullable = false, precision = 8, scale = 2)
	public BigDecimal getInteres() {
		return this.interes;
	}

	@Column(nullable = false)
	public Integer getMeses() {
		return this.meses;
	}

	@Column(nullable = false, precision = 8, scale = 2)
	public BigDecimal getMonto() {
		return this.monto;
	}

	@Column(nullable = false)
	public Boolean getPagado() {
		return this.pagado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public DetalleCredito removeDetalleCredito(DetalleCredito detalleCredito) {
		getDetallesCreditos().remove(detalleCredito);
		detalleCredito.setCredito(null);

		return detalleCredito;
	}

	public Garante removeGarante(Garante garante) {
		getGarantes().remove(garante);
		garante.setCredito(null);

		return garante;
	}

	public void setDetallesCreditos(List<DetalleCredito> detallesCreditos) {
		this.detallesCreditos = detallesCreditos;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public void setGarantes(List<Garante> garantes) {
		this.garantes = garantes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public void setMeses(Integer meses) {
		this.meses = meses;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

}