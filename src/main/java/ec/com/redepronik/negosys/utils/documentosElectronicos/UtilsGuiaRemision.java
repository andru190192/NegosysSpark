package ec.com.redepronik.negosys.utils.documentosElectronicos;

import static ec.com.redepronik.negosys.utils.UtilsDate.fechaFormatoString;
import static ec.com.redepronik.negosys.utils.UtilsMath.newBigDecimal;
import static ec.com.redepronik.negosys.utils.UtilsMath.parametro;
import static ec.com.redepronik.negosys.utils.documentosElectronicos.UtilsDocumentos.infoAdicional;
import static ec.com.redepronik.negosys.utils.documentosElectronicos.UtilsDocumentos.infoTributaria;

import java.util.List;

import ec.com.redepronik.negosys.entity.DetalleFactura;
import ec.com.redepronik.negosys.entity.Factura;
import ec.com.redepronik.negosys.entity.GuiaRemision;
import ec.com.redepronik.negosys.entity.Local;
import ec.com.redepronik.negosys.entity.Persona;
import ec.com.redepronik.negosys.entityAux.CantidadFactura;
import ec.com.redepronik.negosys.service.LocalService;
import ec.com.redepronik.negosys.service.PersonaService;
import ec.com.redepronik.negosys.utils.documentosElectronicos.entityDocumentosElectronicosXML.InfoTributaria;
import ec.com.redepronik.negosys.utils.documentosElectronicos.entityDocumentosElectronicosXML.guiaRemision.Destinatarios;
import ec.com.redepronik.negosys.utils.documentosElectronicos.entityDocumentosElectronicosXML.guiaRemision.Destinatarios.Destinatario;
import ec.com.redepronik.negosys.utils.documentosElectronicos.entityDocumentosElectronicosXML.guiaRemision.Detalles;
import ec.com.redepronik.negosys.utils.documentosElectronicos.entityDocumentosElectronicosXML.guiaRemision.Detalles.Detalle;
import ec.com.redepronik.negosys.utils.documentosElectronicos.entityDocumentosElectronicosXML.guiaRemision.GuiaRemisionE;
import ec.com.redepronik.negosys.utils.documentosElectronicos.entityDocumentosElectronicosXML.guiaRemision.InfoGuiaRemision;

public class UtilsGuiaRemision {

	private static GuiaRemisionE armarGuiaRemisionXML(
			GuiaRemision guiaRemision, String cAcceso, CantidadFactura cf,
			Local local) {
		Persona p = UtilsGuiaRemision.personaService.obtenerObjeto("",
				new Object[] {}, false, new Object[] {});

		GuiaRemisionE guiaRemisionE = new GuiaRemisionE();
		InfoTributaria infoTributaria = infoTributaria(cAcceso, local);
		infoTributaria.setEstab(guiaRemision.getEstablecimiento());
		infoTributaria.setPtoEmi(guiaRemision.getPuntoEmision());
		infoTributaria.setSecuencial(guiaRemision.getSecuencia());
		guiaRemisionE.setInfoTributaria(infoTributaria);
		guiaRemisionE.setInfoGuiaRemision(infoGuiaRemision(guiaRemision, p, cf,
				local));
		guiaRemisionE.setDestinatarios(destinatarios(guiaRemision));
		guiaRemisionE.setInfoAdicional(infoAdicional(p));

		return guiaRemisionE;
	}

	private static Destinatario destinatario(Persona p, String motivoTraslado,
			Factura f) {

		Destinatario destinatario = new Destinatario();
		destinatario.setIdentificacionDestinatario(p.getNumeroDocumento());
		destinatario.setRazonSocialDestinatario(p.getRazonSocial());
		destinatario.setDirDestinatario(p.getDireccion());
		destinatario.setMotivoTraslado(motivoTraslado);
		destinatario.setCodDocSustento("01");
		destinatario.setNumDocSustento(f.getEstablecimiento() + "-"
				+ f.getPuntoEmision() + "-" + f.getSecuencia());
		destinatario.setNumAutDocSustento("");
		destinatario.setFechaEmisionDocSustento(fechaFormatoString(f
				.getFechaInicio()));

		destinatario.setDetalles(detalles(f.getDetalleFactura()));
		return destinatario;
	}

	private static Destinatarios destinatarios(GuiaRemision gr) {
		Destinatarios destinatarios = new Destinatarios();
		for (Factura f : gr.getFacturas())
			destinatarios.getDestinatario().add(
					destinatario(f.getClienteFactura(), gr.getMotivoTraslado()
							.getNombre(), f));
		return destinatarios;
	}

	private static Detalle detalle(String codigoInterno, String descripcion,
			int cantidad) {
		Detalle detalle = new Detalle();
		detalle.setCodigoInterno(codigoInterno);
		detalle.setDescripcion(descripcion);
		detalle.setCantidad(newBigDecimal(cantidad));
		return detalle;
	}

	private static Detalles detalles(List<DetalleFactura> list) {
		Detalles detalles = new Detalles();
		for (DetalleFactura df : list)
			detalles.getDetalle().add(
					detalle(df.getProducto().getCodigo(), df.getProducto()
							.getNombre(), df.getCantidad()));
		return detalles;
	}

	public static GuiaRemisionE guiaRemisionXML(GuiaRemision guiaRemision,
			CantidadFactura cf, String claveAcceso, LocalService localService,
			PersonaService personaService) {
		UtilsGuiaRemision.localService = localService;
		UtilsGuiaRemision.personaService = personaService;
		Local local = UtilsGuiaRemision.localService.obtenerObjeto("",
				new Object[] {}, false, new Object[] {});
		// .obtenerPorEstablecimiento(guiaRemision.getEstablecimiento());
		return armarGuiaRemisionXML(guiaRemision, claveAcceso, cf, local);
	}

	private static InfoGuiaRemision infoGuiaRemision(GuiaRemision gr,
			Persona p, CantidadFactura cf, Local local) {
		InfoGuiaRemision infoGuiaRemision = new InfoGuiaRemision();
		infoGuiaRemision.setDirEstablecimiento(local.getDireccion());
		infoGuiaRemision.setDirPartida(gr.getPuntoPartida());
		infoGuiaRemision.setRazonSocialTransportista(p.getRazonSocial());
		String tic = "";
		if (p.getNumeroDocumento().length() == 13) {
			if (p.getNumeroDocumento().compareToIgnoreCase("9999999999999") == 0)
				tic = "07";
			else
				tic = "04";
		} else if (p.getNumeroDocumento().length() == 10)
			tic = "05";

		infoGuiaRemision.setTipoIdentificacionTransportista(tic);
		infoGuiaRemision.setRucTransportista(p.getNumeroDocumento());
		infoGuiaRemision
				.setObligadoContabilidad(parametro.getContabilidad() ? "SI"
						: "NO");
		if (parametro.getCodigoContribuyente() != null
				&& parametro.getCodigoContribuyente().compareToIgnoreCase("") != 0)
			infoGuiaRemision.setContribuyenteEspecial(parametro
					.getCodigoContribuyente());

		infoGuiaRemision.setFechaIniTransporte(fechaFormatoString(gr
				.getFechaInicio()));
		infoGuiaRemision.setFechaFinTransporte(fechaFormatoString(gr
				.getFechaTerminacion()));
		infoGuiaRemision.setPlaca(gr.getPlaca());
		return infoGuiaRemision;
	}

	private static LocalService localService;

	private static PersonaService personaService;

}
