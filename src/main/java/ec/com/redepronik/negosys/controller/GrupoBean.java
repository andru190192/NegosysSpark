package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.redireccionar;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entity.Grupo;
import ec.com.redepronik.negosys.service.GrupoService;

@Controller
@Scope("session")
public class GrupoBean {

	@Autowired
	private GrupoService grupoService;

	private List<Grupo> grupos;
	private Grupo grupo;

	public GrupoBean() {
	}

	public void actualizar(ActionEvent actionEvent) {
		if (grupoService.actualizar(grupo)) {
			grupos = new ArrayList<Grupo>();
			grupos = grupoService.obtenerLista(
					"select g from Grupo g order by g.nombre", new Object[] {},
					false, new Object[] {});
			redireccionar("producto.jsf");
		}
	}

	public void cargarActualizar() {
		redireccionar("grupoActualizar.jsf");
	}

	public void cargarInsertar() {
		redireccionar("grupoInsertar.jsf");
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
		grupos = new ArrayList<Grupo>();
		grupos = grupoService.obtenerLista(
				"select g from Grupo g order by g.nombre", new Object[] {},
				false, new Object[] {});
	}

	public void insertar(ActionEvent actionEvent) {
		grupoService.insertar(grupo);
		if (grupo.getId() != null) {
			grupos = new ArrayList<Grupo>();
			grupos = grupoService.obtenerLista(
					"select g from Grupo g order by g.nombre", new Object[] {},
					false, new Object[] {});
			redireccionar("producto.jsf");
		}
	}

	public void limpiarObjetos() {
		grupo = new Grupo();
	}

	public void regresar() {
		redireccionar("producto.jsf");
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
}