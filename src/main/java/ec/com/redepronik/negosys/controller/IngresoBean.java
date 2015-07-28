package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.DetalleIngreso;
import ec.com.redepronik.negosys.entity.Ingreso;
import ec.com.redepronik.negosys.entity.Local;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entity.Producto;
import ec.com.redepronik.negosys.entity.Rol;
import ec.com.redepronik.negosys.service.IngresoService;
import ec.com.redepronik.negosys.service.LocalService;
import ec.com.redepronik.negosys.service.PersonaService;
import ec.com.redepronik.negosys.service.ProductoService;
import ec.com.redepronik.negosys.utils.UtilsAplicacion;

@Controller
@Scope("session")
public class IngresoBean {

	@Autowired
	private IngresoService ingresoService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private PersonaService proveedorService;

	@Autowired
	private LocalService localService;

	private Ingreso ingreso;
	private List<Persona> proveedores;

	private String criterioBusquedaProducto;
	private List<Producto> productos;
	private Producto producto;

	private DetalleIngreso detalleIngreso;

	private List<Local> locales;

	public IngresoBean() {
	}

	public void busquedaProductos() {
		productos = new ArrayList<Producto>();
		productos = productoService
				.obtenerLista(
						"select p from Producto p where p.nombre like ?1 order by p.nombre",
						new Object[] { "%" + criterioBusquedaProducto + "%" },
						true, new Object[] {});
	}

	public void eliminarDetalle(DetalleIngreso detalleIngreso) {
		ingresoService.eliminarDetalle(ingreso, detalleIngreso);
	}

	public void escojerProducto(SelectEvent selectEvent) {
		detalleIngreso.setProducto((Producto) selectEvent.getObject());
	}

	public String getCriterioBusquedaProducto() {
		return criterioBusquedaProducto;
	}

	public DetalleIngreso getDetalleIngreso() {
		return detalleIngreso;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public IngresoService getIngresoService() {
		return ingresoService;
	}

	public List<Local> getLocales() {
		return locales;
	}

	public Producto getProducto() {
		return producto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public List<Persona> getProveedores() {
		return proveedores;
	}

	@PostConstruct
	public void init() {
		proveedores = proveedorService
				.obtenerLista(
						"select p from Persona p inner join p.permisosPersonas pp where pp.rol=?1",
						new Object[] { Rol.PROV }, false, new Object[] {});

		locales = localService.obtenerLista(
				"select l from Local l order by l.nombre", new Object[] {},
				false, new Object[] {});

		ingreso = new Ingreso();
		ingreso.setProveedor(new Persona());
		ingreso.setPagado(true);
		ingreso.setDetallesIngresos(new ArrayList<DetalleIngreso>());

		detalleIngreso = new DetalleIngreso();
		detalleIngreso.setLocal(new Local());

		producto = new Producto();
	}

	public void insertar() {
		// ingreso.setBodeguero(empleadoService
		// .obtenerEmpleadoCargoPorCedulaAndCargo(SecurityContextHolder
		// .getContext().getAuthentication().getName(), 3));
		// if (ingresoService.insertar(ingreso, listaLocal))
		// limpiarIngreso();

	}

	public void insertarDetalleIngreso() {
		ingreso.addDetallesIngresos(detalleIngreso);
		detalleIngreso = new DetalleIngreso();
		detalleIngreso.setLocal(new Local());

		presentaMensaje(FacesMessage.SEVERITY_INFO,
				"INSERTO EL PRODUCTO AL INGRESO");
	}

	public void irIngreso() {
		UtilsAplicacion.redireccionar("ingreso.jsf");
	}

	public void irIngresoInsertar() {
		UtilsAplicacion.redireccionar("ingresoInsertar.jsf");
	}

	public void setCriterioBusquedaProducto(String criterioBusquedaProducto) {
		this.criterioBusquedaProducto = criterioBusquedaProducto;
	}

	public void setDetalleIngreso(DetalleIngreso detalleIngreso) {
		this.detalleIngreso = detalleIngreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public void setIngresoService(IngresoService ingresoService) {
		this.ingresoService = ingresoService;
	}

	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public void setProveedores(List<Persona> proveedores) {
		this.proveedores = proveedores;
	}

}