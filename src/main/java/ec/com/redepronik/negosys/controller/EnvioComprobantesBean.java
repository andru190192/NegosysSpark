package ec.com.redepronik.negosys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.redepronik.negosys.entityAux.DocumentoElectronico;
import ec.com.redepronik.negosys.service.DocumentosElectronicosService;

@Controller
@Scope("session")
public class EnvioComprobantesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DocumentosElectronicosService documentosElectronicosService;

	private List<DocumentoElectronico> listaDocumentosFirmados;
	private DocumentoElectronico documentoElectronico;

	public EnvioComprobantesBean() {
	}

	public void enviarAutorizar(DocumentoElectronico documentoFirmado) {
		if (documentoFirmado.getNombreDocumento() != null
				&& documentoFirmado.getNombreDocumento()
						.compareToIgnoreCase("") != 0) {
			documentosElectronicosService.enviarAutorizarXML(true,
					documentoFirmado);
			listaDocumentosFirmados.remove(documentoFirmado);
		}
	}

	public void enviarAutorizarTodos() {
		if (listaDocumentosFirmados != null)
			for (DocumentoElectronico docFirmado : listaDocumentosFirmados)
				documentosElectronicosService.enviarAutorizarXML(false,
						docFirmado);
		listaDocumentosFirmados = new ArrayList<DocumentoElectronico>();
	}

	public DocumentoElectronico getDocumentoElectronico() {
		return documentoElectronico;
	}

	public List<DocumentoElectronico> getListaDocumentosFirmados() {
		return listaDocumentosFirmados;
	}

	@PostConstruct
	public void init() {
		obtener();
	}

	public void obtener() {
		listaDocumentosFirmados = documentosElectronicosService
				.documentosFirmados();
	}

	public void setDocumentoElectronico(
			DocumentoElectronico documentoElectronico) {
		this.documentoElectronico = documentoElectronico;
	}

	public void setListaDocumentosFirmados(
			List<DocumentoElectronico> listaDocumentosFirmados) {
		this.listaDocumentosFirmados = listaDocumentosFirmados;
	}

}