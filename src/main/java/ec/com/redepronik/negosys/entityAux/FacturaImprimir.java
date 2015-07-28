package ec.com.redepronik.negosys.entityAux;

import java.io.Serializable;
import java.util.List;

import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.Persona;

public class FacturaImprimir implements Serializable {

	private static final long serialVersionUID = 1L;

	private Factura egreso;
	private Persona persona;
	private String telefono;
	private String totalLetras;
	private CantidadFactura cantidadFactura;
	private List<FacturaReporte> listFacturaReporte;

	public FacturaImprimir() {

	}

	public FacturaImprimir(Factura egreso, Persona persona, String telefono,
			String totalLetras, CantidadFactura cantidadFactura,
			List<FacturaReporte> listFacturaReporte) {
		this.egreso = egreso;
		this.persona = persona;
		this.telefono = telefono;
		this.totalLetras = totalLetras;
		this.cantidadFactura = cantidadFactura;
		this.listFacturaReporte = listFacturaReporte;
	}

	public CantidadFactura getCantidadFactura() {
		return cantidadFactura;
	}

	public Factura getEgreso() {
		return egreso;
	}

	public List<FacturaReporte> getListFacturaReporte() {
		return listFacturaReporte;
	}

	public Persona getPersona() {
		return persona;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getTotalLetras() {
		return totalLetras;
	}

	public void setCantidadFactura(CantidadFactura cantidadFactura) {
		this.cantidadFactura = cantidadFactura;
	}

	public void setEgreso(Factura egreso) {
		this.egreso = egreso;
	}

	public void setListFacturaReporte(List<FacturaReporte> listFacturaReporte) {
		this.listFacturaReporte = listFacturaReporte;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setTotalLetras(String totalLetras) {
		this.totalLetras = totalLetras;
	}

}
