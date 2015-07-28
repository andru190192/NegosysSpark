package ec.com.redepronik.negosys.daoAux;

import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.dao.GenericDaoImpl;
import ec.com.redepronik.negosys.entityAux.VolumenVentaFactura;

@Repository
public class VolumenVentaFacturaDaoImpl extends
		GenericDaoImpl<VolumenVentaFactura, Integer> implements
		VolumenVentaFacturaDao {

}