package ec.com.redepronik.negosys.service;

import static ec.com.redepronik.negosys.utils.Utils.validacion;
import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.dao.CiudadDao;
import ec.com.redepronik.negosys.entity.Ciudad;
import ec.com.redepronik.negosys.entity.Provincia;

@Service
public class CiudadServiceImpl extends GenericServiceImpl<Ciudad, Integer>
		implements CiudadService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CiudadDao ciudadDao;

	public void insertar(Provincia provincia, Ciudad ciudad) {
		ciudad.setActivo(true);
		if (validar(provincia, ciudad))
			ciudadDao.insertar(ciudad);
	}

	public Ciudad obtenerPorId(int id) {
		return ciudadDao.obtenerPorId(Ciudad.class, id);
	}

	public Ciudad obtenerPorProvinciaCiudad(Provincia provincia, Ciudad ciudad) {
		List<Ciudad> lista = ciudadDao
				.obtenerLista(
						"select c from Ciudad c where c.provincia like ?1 and c.nombre like ?2",
						new Object[] { provincia, ciudad.getNombre() }, false,
						new Object[] {});
		if (!lista.isEmpty())
			return lista.get(0);
		return null;
	}

	public Boolean validar(Provincia provincia, Ciudad ciudad) {
		if (!validacion(ciudad.getNombre(), "[A-Za-z ñÑ]{3,25}",
				"EL NOMBRE DE LA CIUDAD ES OBLIGATORIA (3 A 25 LETRAS)"))
			return false;
		else if (obtenerPorProvinciaCiudad(provincia, ciudad) != null) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"LA CIUDAD YA EXISTE EN ESTA PROVINCIA");
			return false;
		}
		return true;
	}
}