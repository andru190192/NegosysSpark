package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.redireccionar;
import static ec.com.redepronik.negosys.utils.UtilsMath.newBigDecimal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.BajaInventario;
import ec.com.redepronik.negosys.entity.DetalleBajaInventario;
import ec.com.redepronik.negosys.entityAux.BajaInventarioReporte;
import ec.com.redepronik.negosys.service.BajaInventarioService;

@Controller
@Scope("session")
public class ListadoBajaInventarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BajaInventarioService bajaInventarioService;

	@Autowired
	private InventarioReportes egresoReport;

	private List<BajaInventario> listaBajaInventarios;
	private String criterioBusquedaNumeroBaja;
	private Date criterioBusquedaFechaDocumento;

	private List<BajaInventarioReporte> listaBajaInventariosDetalle;
	private BajaInventario bajaInventario;
	private BigDecimal total;

	public ListadoBajaInventarioBean() {
		bajaInventario = new BajaInventario();
	}

	public void generarListaDetalle() {
		listaBajaInventariosDetalle = new ArrayList<BajaInventarioReporte>();
		total = newBigDecimal();
		for (DetalleBajaInventario de : bajaInventario
				.getDetallesBajasInventarios())
			listaBajaInventariosDetalle.add(bajaInventarioService.asignar(de));
		total = bajaInventarioService
				.sumarCantidadFinal(listaBajaInventariosDetalle);
	}

	public BajaInventario getBajaInventario() {
		return bajaInventario;
	}

	public Date getCriterioBusquedaFechaDocumento() {
		return criterioBusquedaFechaDocumento;
	}

	public String getCriterioBusquedaNumeroBaja() {
		return criterioBusquedaNumeroBaja;
	}

	public List<BajaInventario> getListaBajaInventarios() {
		return listaBajaInventarios;
	}

	public List<BajaInventarioReporte> getListaBajaInventariosDetalle() {
		return listaBajaInventariosDetalle;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void obtenerBajaInvetario() {
		listaBajaInventarios = bajaInventarioService.obtener(
				criterioBusquedaNumeroBaja, criterioBusquedaFechaDocumento);
	}

	public void redirecionarBajaInventario() {
		redireccionar("bajaInventario.jsf");
	}

	public void setBajaInventario(BajaInventario bajaInventario) {
		this.bajaInventario = bajaInventario;
	}

	public void setCriterioBusquedaFechaDocumento(
			Date criterioBusquedaFechaDocumento) {
		this.criterioBusquedaFechaDocumento = criterioBusquedaFechaDocumento;
	}

	public void setCriterioBusquedaNumeroBaja(String criterioBusquedaNumeroBaja) {
		this.criterioBusquedaNumeroBaja = criterioBusquedaNumeroBaja;
	}

	public void setListaBajaInventarios(
			List<BajaInventario> listaBajaInventarios) {
		this.listaBajaInventarios = listaBajaInventarios;
	}

	public void setListaBajaInventariosDetalle(
			List<BajaInventarioReporte> listaBajaInventariosDetalle) {
		this.listaBajaInventariosDetalle = listaBajaInventariosDetalle;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}