package ec.com.redepronik.negosys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Provincia;
import ec.com.redepronik.negosys.entity.Rol;
import ec.com.redepronik.negosys.service.CiudadService;
import ec.com.redepronik.negosys.service.PersonaService;
import ec.com.redepronik.negosys.utils.UtilsAplicacion;
import ec.com.redepronik.negosys.utils.service.ReporteService;

@Controller
public class DirectorioReporteBean {

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private CiudadService ciudadService;

	private List<Ciudad> ciudades;
	private Integer ciudad;
	private Provincia provincia;

	public DirectorioReporteBean() {
	}

	public void cargarCiudades() {
		ciudades = new ArrayList<Ciudad>();
		ciudades = ciudadService.obtenerLista(
				"select c from Ciudad c where c.provincia like ?1",
				new Object[] { provincia }, false, new Object[] {});
	}

	public Integer getCiudad() {
		return ciudad;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public Provincia[] getProvincias() {
		return Provincia.values();
	}

	public void reporteCliente(ActionEvent actionEvent) {
		if (ciudad == 0)
			UtilsAplicacion.presentaMensaje(FacesMessage.SEVERITY_INFO,
					"ESCOJA UNA CIUDAD");
		else {
			List<Persona> list = personaService
					.obtenerLista(
							"select p from Persona p inner join p.permisosPersonas pp "
									+ "inner join p.ciudad c where pp.rol=?1 and c.id=?2 order by p.razonSocial",
							new Object[] { Rol.CLIE, ciudad }, true,
							new Object[] {});
			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put(
					"ciudad",
					ciudadService.obtenerObjeto(
							"select c from Ciudad c where c.id=?1",
							new Object[] { ciudad }, false, new Object[] {})
							.getNombre());
			reporteService.generarReportePDF(list, parametro, "Cliente");
			ciudad = 0;
		}
	}

	public void reporteProveedor(ActionEvent actionEvent) {
		List<Persona> list = personaService.obtenerLista(
				"select p from Persona p inner join p.permisosPersonas pp "
						+ "where pp.rol=?1 order by p.razonSocial",
				new Object[] { Rol.PROV }, true, new Object[] {});
		Map<String, Object> parametro = new HashMap<String, Object>();
		reporteService.generarReportePDF(list, parametro, "Proveedor");
	}

	public void setCiudad(Integer ciudad) {
		this.ciudad = ciudad;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}