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
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private CiudadService ciudadService;

	private List<Persona> clientes;
	private Persona cliente;
	private String criterioBusqueda;
	private List<Ciudad> ciudades;

	public ClienteBean() {

	}

	public void actualizar(ActionEvent actionEvent) {
		if (personaService.actualizar(cliente)) {
			clientes = new ArrayList<Persona>();
			clientes.add(personaService.obtenerObjeto(
					"select p from Persona p where p.id=?1",
					new Object[] { cliente.getId() }, false, new Object[] {}));
			redireccionar("cliente.jsf");
		}
	}

	public void cargarActualizar() {
		cargarCiudades();
		redireccionar("clienteActualizar.jsf");
	}

	public void cargarCiudades() {
		ciudades = new ArrayList<Ciudad>();
		ciudades = ciudadService.obtenerLista(
				"select c from Ciudad c where c.provincia like ?1",
				new Object[] { cliente.getCiudad().getProvincia() }, false,
				new Object[] {});
	}

	public void cargarInsertar() {
		limpiarObjetos();
		redireccionar("clienteInsertar.jsf");
	}

	public void comprobarCliente() {
		String numeroDocumento = cliente.getNumeroDocumento().trim();
		cliente = personaService.obtenerObjeto(
				"select p from Persona p inner join p.permisosPersonas pp "
						+ "where p.numeroDocumento=?1 and pp.rol=?2",
				new Object[] { numeroDocumento, Rol.CLIE }, false,
				new Object[] {});

		if (cliente != null) {
			limpiarObjetos();
			presentaMensaje(FacesMessage.SEVERITY_WARN, "EL CLIENTE YA EXISTE");
		} else {
			cliente = personaService.obtenerObjeto(
					"select p from Persona p inner join p.permisosPersonas "
							+ "where p.numeroDocumento=?1",
					new Object[] { numeroDocumento }, false,
					new Object[] { "getPermisosPersonas" });
			if (cliente != null)
				cargarCiudades();
			else {
				limpiarObjetos();
				cliente.setNumeroDocumento(numeroDocumento);
			}
		}
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public Persona getCliente() {
		return cliente;
	}

	public List<Persona> getClientes() {
		return clientes;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda;
	}

	public Provincia[] getProvincias() {
		return Provincia.values();
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
	}

	public void insertar(ActionEvent actionEvent) {
		personaService.insertar(cliente, Rol.CLIE);
		if (cliente.getId() != null) {
			clientes = new ArrayList<Persona>();
			clientes.add(personaService.obtenerObjeto(
					"select p from Persona p where p.id=?1",
					new Object[] { cliente.getId() }, false, new Object[] {}));
			redireccionar("cliente.jsf");
		}
	}

	public void limpiarObjetos() {
		cliente = new Persona();
		cliente.setCiudad(new Ciudad());
	}

	public void obtener() {
		clientes = new ArrayList<Persona>();
		clientes = personaService
				.obtenerLista(
						"select distinct p from Persona p "
								+ "inner join p.permisosPersonas pp "
								+ "where p.visible=true and (p.numeroDocumento like ?1 or p.razonSocial like ?1) and pp.rol=?2 "
								+ "order by p.razonSocial", new Object[] {
								"%" + criterioBusqueda + "%", Rol.CLIE }, true,
						new Object[] {});
	}

	public void regresar() {
		redireccionar("cliente.jsf");
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}

	public void setClientes(List<Persona> clientes) {
		this.clientes = clientes;
	}

	public void setCriterioBusqueda(String criterioBusqueda) {
		this.criterioBusqueda = criterioBusqueda;
	}

}
