package ec.com.redepronik.negosys.dao;

import java.math.BigInteger;

import ec.com.redepronik.negosys.entity.Producto;

public interface ProductoDao extends GenericDao<Producto, Long> {

	public BigInteger obtenerId();
}