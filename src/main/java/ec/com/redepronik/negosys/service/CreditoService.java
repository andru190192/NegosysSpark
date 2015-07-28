package ec.com.redepronik.negosys.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import ec.com.redepronik.negosys.entity.DetalleCredito;
import ec.com.redepronik.negosys.entityAux.CantidadesCreditoReporte;

public interface CreditoService {

	@Transactional
	public CantidadesCreditoReporte calcularCuota(
			DetalleCredito detallesCredito,
			CantidadesCreditoReporte cantidadesCreditoReporte,
			Date fechaEgreso, Date fecha);

}