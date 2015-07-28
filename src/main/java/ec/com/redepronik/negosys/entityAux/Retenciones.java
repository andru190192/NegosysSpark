package ec.com.redepronik.negosys.entityAux;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import ec.com.redepronik.negosys.entity.ImpuestoRetencion;
import ec.com.redepronik.negosys.entity.TarifaRetencion;

@Entity
public class Retenciones {

	@Id
	private Integer id;
	private String numeroRetencion;
	private Date fechaRetencion;
	private String proveedor;
	private String numeroComprobanteRetenido;
	private Date fechaComprobanteRetenido;
	private ImpuestoRetencion impuesto;
	private TarifaRetencion tarifa;
	private BigDecimal baseImponible;
	private BigDecimal porcentajeRetencion;
	private BigDecimal subTotal;

	public Retenciones() {

	}

	public Retenciones(Integer id, String numeroRetencion, Date fechaRetencion,
			String proveedor, String numeroComprobanteRetenido,
			Date fechaComprobanteRetenido, ImpuestoRetencion impuesto,
			TarifaRetencion tarifa, BigDecimal baseImponible,
			BigDecimal porcentajeRetencion, BigDecimal subTotal) {
		this.id = id;
		this.numeroRetencion = numeroRetencion;
		this.fechaRetencion = fechaRetencion;
		this.proveedor = proveedor;
		this.numeroComprobanteRetenido = numeroComprobanteRetenido;
		this.fechaComprobanteRetenido = fechaComprobanteRetenido;
		this.impuesto = impuesto;
		this.tarifa = tarifa;
		this.baseImponible = baseImponible;
		this.porcentajeRetencion = porcentajeRetencion;
		this.subTotal = subTotal;
	}

	public BigDecimal getBaseImponible() {
		return baseImponible;
	}

	public Date getFechaComprobanteRetenido() {
		return fechaComprobanteRetenido;
	}

	public Date getFechaRetencion() {
		return fechaRetencion;
	}

	public Integer getId() {
		return id;
	}

	public ImpuestoRetencion getImpuesto() {
		return impuesto;
	}

	public String getNumeroComprobanteRetenido() {
		return numeroComprobanteRetenido;
	}

	public String getNumeroRetencion() {
		return numeroRetencion;
	}

	public BigDecimal getPorcentajeRetencion() {
		return porcentajeRetencion;
	}

	public String getProveedor() {
		return proveedor;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public TarifaRetencion getTarifa() {
		return tarifa;
	}

	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
	}

	public void setFechaComprobanteRetenido(Date fechaComprobanteRetenido) {
		this.fechaComprobanteRetenido = fechaComprobanteRetenido;
	}

	public void setFechaRetencion(Date fechaRetencion) {
		this.fechaRetencion = fechaRetencion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImpuesto(ImpuestoRetencion impuesto) {
		this.impuesto = impuesto;
	}

	public void setNumeroComprobanteRetenido(String numeroComprobanteRetenido) {
		this.numeroComprobanteRetenido = numeroComprobanteRetenido;
	}

	public void setNumeroRetencion(String numeroRetencion) {
		this.numeroRetencion = numeroRetencion;
	}

	public void setPorcentajeRetencion(BigDecimal porcentajeRetencion) {
		this.porcentajeRetencion = porcentajeRetencion;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public void setTarifa(TarifaRetencion tarifa) {
		this.tarifa = tarifa;
	}

}
