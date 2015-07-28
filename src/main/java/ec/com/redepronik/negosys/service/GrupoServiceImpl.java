package ec.com.redepronik.negosys.service;

import static ec.com.redepronik.negosys.utils.Utils.validacion;
import static ec.com.redepronik.negosys.utils.UtilsAplicacion.presentaMensaje;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.redepronik.negosys.dao.GrupoDao;
import ec.com.redepronik.negosys.entity.Grupo;

@Service
public class GrupoServiceImpl extends GenericServiceImpl<Grupo, Integer>
		implements GrupoService {

	@Autowired
	private GrupoDao grupoDao;

	public boolean actualizar(Grupo grupo) {
		if (validar(grupo))
			return grupoDao.actualizar(grupo);
		return false;
	}

	public void insertar(Grupo grupo) {
		grupo.setActivo(true);
		if (validar(grupo))
			grupoDao.insertar(grupo);
	}

	public Boolean validar(Grupo grupo) {
		if (!validacion(grupo.getNombre(), "[A-Za-z ñÑ]{3,25}",
				"EL NOMBRE ES OBLIGATORIO (3 A 25 LETRAS)"))
			return false;
		else if (!grupoDao.comprobarIndiceUnico(grupo, "nombre",
				grupo.getNombre())) {
			presentaMensaje(FacesMessage.SEVERITY_INFO, "EL NOMBRE YA EXISTE");
			return false;
		}
		return true;
	}
}