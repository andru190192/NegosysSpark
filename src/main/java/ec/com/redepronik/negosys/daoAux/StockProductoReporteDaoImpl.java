package ec.com.redepronik.negosys.daoAux;

import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.dao.GenericDaoImpl;
import ec.com.redepronik.negosys.entityAux.StockProductoReporte;

@Repository
public class StockProductoReporteDaoImpl extends
		GenericDaoImpl<StockProductoReporte, Long> implements
		StockProductoReporteDao {

}