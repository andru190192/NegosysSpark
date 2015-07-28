package ec.com.redepronik.negosys.daoAux;

import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.dao.GenericDaoImpl;
import ec.com.redepronik.negosys.entityAux.VolumenVentaProducto;

@Repository
public class VolumenVentaProductoDaoImpl extends
		GenericDaoImpl<VolumenVentaProducto, String> implements
		VolumenVentaProductoDao {

}