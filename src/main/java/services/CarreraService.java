package services;

import java.util.List;

import beans.CarreraDTO;
import dao.DAOFactory;
import interfaces.CarreraDAO;
import utils.Constantes;

public class CarreraService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	CarreraDAO objCarrera = fabrica.getCarrera();

	public List<CarreraDTO> listaCarreras() {
		return objCarrera.listarCarreras();
	}

	public CarreraDTO buscaCodigo(int cod) {
		return objCarrera.buscarCodigo(cod);
	}

	public int registraCarrera(CarreraDTO obj) {
		return objCarrera.registrarCarrera(obj);
	}

	public int actualizaCarrera(CarreraDTO obj) {
		return objCarrera.actualizarCarrera(obj);
	}

	public int eliminaCarrera(int cod) {
		return objCarrera.eliminarCarrera(cod);
	}
}
