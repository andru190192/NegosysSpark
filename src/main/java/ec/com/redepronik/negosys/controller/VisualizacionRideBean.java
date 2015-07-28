package ec.com.redepronik.negosys.controller;

import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.redepronik.negosys.utils.UtilsArchivos.convertirString;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entityAux.Anio;
import ec.com.redepronik.negosys.entityAux.DocumentoElectronico;
import ec.com.redepronik.negosys.entityAux.Mes;
import ec.com.redepronik.negosys.service.DocumentosElectronicosService;
import ec.com.redepronik.negosys.service.PersonaService;

@Controller
@Scope("session")
public class VisualizacionRideBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DocumentosElectronicosService documentosElectronicosService;

	@Autowired
	private PersonaService personaService;

	private List<DocumentoElectronico> listaDocumentosAutorizados;
	private DocumentoElectronico documentoElectronico;
	private StreamedContent documento;
	private Mes mes;
	private Anio anio;
	private String persona;
	private String cedulaRuc;

	public VisualizacionRideBean() {
	}

	public void cargarPersona() {
		// cedulaRuc = personaService.obtenerPorNumeroDocumento(
		// persona.split(" - ")[0]).getNumeroDocumento();
		// persona = persona.split(" - ")[1];
	}

	public void descargarXML(DocumentoElectronico documentoElectronico) {
		InputStream is = new ByteArrayInputStream(
				convertirString(documentoElectronico.getDocumento()));
		documento = new DefaultStreamedContent(is, "text/xml",
				documentoElectronico.getNombreDocumento());
	}

	public void enviarCorreo(DocumentoElectronico documentoElectronico) {
		documentosElectronicosService.envioCorreo(true, documentoElectronico,
				null);
	}

	public Anio getAnio() {
		return anio;
	}

	public String getCedulaRuc() {
		return cedulaRuc;
	}

	public StreamedContent getDocumento() {
		return documento;
	}

	public DocumentoElectronico getDocumentoElectronico() {
		return documentoElectronico;
	}

	public Anio[] getListaAnio() {
		return Anio.values();
	}

	public List<DocumentoElectronico> getListaDocumentosAutorizados() {
		return listaDocumentosAutorizados;
	}

	public Mes[] getListaMes() {
		return Mes.values();
	}

	public Mes getMes() {
		return mes;
	}

	public String getPersona() {
		return persona;
	}

	public void obtener() {
		if (cedulaRuc.compareTo("") == 0 || cedulaRuc == null)
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"DEBE INGRESAR UNA CEDULA O RUC PARA REALIZAR LA BUSQUEDA");
		else if (mes == null)
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"DEBE INGRESAR UN MES PARA REALIZAR LA BUSQUEDA");
		else if (anio == null)
			presentaMensaje(FacesMessage.SEVERITY_WARN,
					"DEBE INGRESAR UN AÑO PARA REALIZAR LA BUSQUEDA");
		else
			listaDocumentosAutorizados = documentosElectronicosService
					.documentosAutorizados(cedulaRuc, mes, anio);
	}

	public List<String> obtenerPersonaPorBusqueda(String criterioPersonaBusqueda) {
		List<String> lista = null;
		// ersonaService
		// .obtenerListaPersonaAutoComplete(criterioPersonaBusqueda);
		if (lista.size() == 1) {
			persona = lista.get(0);
			cargarPersona();
		}
		return lista;
	}

	public void setAnio(Anio anio) {
		this.anio = anio;
	}

	public void setCedulaRuc(String cedulaRuc) {
		this.cedulaRuc = cedulaRuc;
	}

	public void setDocumento(StreamedContent documento) {
		this.documento = documento;
	}

	public void setDocumentoElectronico(
			DocumentoElectronico documentoElectronico) {
		this.documentoElectronico = documentoElectronico;
	}

	public void setListaDocumentosAutorizados(
			List<DocumentoElectronico> listaDocumentosAutorizados) {
		this.listaDocumentosAutorizados = listaDocumentosAutorizados;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public void verRide(DocumentoElectronico documentoElectronico) {
		documentosElectronicosService.generarRIDE(documentoElectronico);
	}

}