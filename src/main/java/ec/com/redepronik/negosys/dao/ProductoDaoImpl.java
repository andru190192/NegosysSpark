package ec.com.redepronik.negosys.dao;

import java.math.BigInteger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.com.redepronik.negosys.entity.Producto;

@Repository
public class ProductoDaoImpl extends GenericDaoImpl<Producto, Long> implements
		ProductoDao {

	@Autowired
	public SessionFactory sessionFactory;

	public BigInteger obtenerId() {
		return (BigInteger) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select currval('invfac.producto_productoid_seq')")
				.list().get(0);
	}
}