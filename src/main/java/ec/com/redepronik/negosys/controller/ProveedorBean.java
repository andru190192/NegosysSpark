package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsAplicacion.redireccionar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Provincia;
import ec.com.redepronik.negosys.entity.Rol;
import ec.com.redepronik.negosys.service.CiudadService;
import ec.com.redepronik.negosys.service.PersonaService;

@Controller
@Scope("session")
public class ProveedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private CiudadService ciudadService;

	private List<Persona> proveedores;
	private Persona proveedor;
	private String criterioBusqueda;
	private List<Ciudad> ciudades;

	public ProveedorBean() {

	}

	public void actualizar(ActionEvent actionEvent) {
		if (proveedor.getReferencia() == null
				|| proveedor.getReferencia().isEmpty())
			proveedor.setReferencia("");

		if (personaService.actualizar(proveedor)) {
			proveedores = new ArrayList<Persona>();
			proveedores
					.add(personaService.obtenerObjeto(
							"select p from Persona p where p.id=?1",
							new Object[] { proveedor.getId() }, false,
							new Object[] {}));
			redireccionar("proveedor.jsf");
		}
	}

	public void cargarCiudades() {
		ciudades = new ArrayList<Ciudad>();
		ciudades = ciudadService.obtenerLista(
				"select c from Ciudad c where c.provincia like ?1",
				new Object[] { proveedor.getCiudad().getProvincia() }, false,
				new Object[] {});
	}

	public void cargarActualizar() {
		cargarCiudades();
		redireccionar("proveedorActualizar.jsf");
	}

	public void cargarInsertar() {
		limpiarObjetos();
		redireccionar("proveedorInsertar.jsf");
	}

	public void comprobarProveedor() {
		String numeroDocumento = proveedor.getNumeroDocumento().trim();
		proveedor = personaService.obtenerObjeto(
				"select p from Persona p inner join p.permisosPersonas pp "
						+ "where p.numeroDocumento=?1 and pp.rol=?2",
				new Object[] { numeroDocumento, Rol.PROV }, false,
				new Object[] {});
		if (proveedor != null) {
			limpiarObjetos();
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"EL PROVEEDOR YA EXISTE");
		} else {
			proveedor = personaService.obtenerObjeto(
					"select p from Persona p left join p.permisosPersonas "
							+ "where p.numeroDocumento=?1",
					new Object[] { numeroDocumento }, false,
					new Object[] { "getPermisosPersonas" });
			if (proveedor != null)
				cargarCiudades();
			else {
				limpiarObjetos();
				proveedor.setNumeroDocumento(numeroDocumento);
			}
		}
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public Provincia[] getProvincias() {
		return Provincia.values();
	}

	public Persona getProveedor() {
		return proveedor;
	}

	public List<Persona> getProveedores() {
		return proveedores;
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
	}

	public void insertar(ActionEvent actionEvent) {
		personaService.insertar(proveedor, Rol.PROV);
		if (proveedor.getId() != null) {
			proveedores = new ArrayList<Persona>();
			proveedores
					.add(personaService.obtenerObjeto(
							"select p from Persona p where p.id=?1",
							new Object[] { proveedor.getId() }, false,
							new Object[] {}));
			redireccionar("proveedor.jsf");
		}
	}

	public void regresar() {
		redireccionar("proveedor.jsf");
	}

	public void limpiarObjetos() {
		proveedor = new Persona();
		proveedor.setReferencia("");
		proveedor.setCiudad(new Ciudad());
	}

	public void obtener() {
		proveedores = new ArrayList<Persona>();
		proveedores = personaService
				.obtenerLista(
						"select distinct p from Persona p "
								+ "inner join p.permisosPersonas pp "
								+ "where p.visible=true and (p.numeroDocumento like ?1 or p.razonSocial like ?1) and pp.rol=?2 "
								+ "order by p.razonSocial", new Object[] {
								"%" + criterioBusqueda + "%", Rol.PROV }, true,
						new Object[] {});
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

	public void setProveedor(Persona proveedor) {
		this.proveedor = proveedor;
	}

	public void setProveedores(List<Persona> proveedores) {
		this.proveedores = proveedores;
	}

}
