package ec.com.redepronik.negosys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.dao.ProductoDao;
import ec.com.redepronik.negosys.entity.Producto;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Long>
		implements ProductoService {

	@Autowired
	private ProductoDao productoDao;

	public void insertar(Producto producto) {
		producto.setActivo(true);
		if (validar(producto))
			productoDao.insertar(producto);
	}

	public boolean actualizar(Producto producto) {
		if (validar(producto))
			return productoDao.actualizar(producto);
		return false;
	}

	public boolean validar(Producto producto) {
		// if (violationsProducto.size() > 0)
		// for (ConstraintViolation<Producto> cv : violationsProducto)
		// presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());
		// else if (comprobarUnidadesVacias(producto))
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "TIENE UNIDADES VACIAS O CON VALOR CERO");
		// else if (producto.getEan().compareTo("") == 0)
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "INGRESE UN CODIGO PRINCIPAL");
		// else if (producto.getCodigo().compareTo("") == 0)
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "INGRESE UN CODIGO AUXILIAR");
		// else if (producto.getGrupo().getId() == 0)
		// presentaMensaje(FacesMessage.SEVERITY_INFO, "ESCOJA UN GRUPO");
		// else if (comprobarTarifasVacias(producto))
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "TIENE IMPUESTOS REPETIDOS PARA EL PRODUCTO");
		// else if (comprobarInclusionIva(producto))
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "TODO PRODUCTO DEBE CONTENER IVA");
		// else if (comprobarPreciosVacias(listaPrecioProductos))
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "TIENE TIPOS DE PRECIOS VACIOS O NO A ESCOGIDO EL PVP");
		// else {
		// for (int i = 0; i < listaPrecioProductos.size(); i++) {
		// if (listaPrecioProductos.get(i).getPrecioProducto()
		// .getPorcentajePrecioFijo()) {
		// producto.getPrecios()
		// .get(i)
		// .setValor(
		// listaPrecioProductos.get(i).getPorcentaje());
		// } else {
		// producto.getPrecios().get(i)
		// .setValor(listaPrecioProductos.get(i).getPrecio());
		// }
		// producto.getPrecios().get(i).setOrden(i + 1);
		// }
		// int c = 1;
		// for (ProductoUnidad pu : producto.getProductosUnidades())
		// pu.setOrden(c++);
		//
		// c = 1;
		// for (ProductoTarifa pt : producto.getProductosTarifas()) {
		// pt.setOrden(c++);
		// }
		//
		// productoDao.actualizar(producto);
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "SE ACTUALIZÓ EL PRODUCTO CORRECTAMENTE", "error", true);
		// }
		return true;
	}
}