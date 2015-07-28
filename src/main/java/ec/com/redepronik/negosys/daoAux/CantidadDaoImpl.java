package ec.com.redepronik.negosys.daoAux;

import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.dao.GenericDaoImpl;
import ec.com.redepronik.negosys.entityAux.Cantidad;

@Repository
public class CantidadDaoImpl extends GenericDaoImpl<Cantidad, Integer>
		implements CantidadDao {

}