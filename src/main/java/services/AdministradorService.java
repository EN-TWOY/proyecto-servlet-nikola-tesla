package services;

import java.util.List;

import beans.AdministradorDTO;
import dao.DAOFactory;
import interfaces.AdministradorDAO;

public class AdministradorService {
	// llamandoMySqlFactory
	DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	
	// llamandoAdministradorMySql
	AdministradorDAO objAdministradorDAO = fabrica.getAdministrador();
	
	// llamarMetodoyRetornar
	public AdministradorDTO iniciarSesion(String login, String contra) {
		return objAdministradorDAO.iniciarSesion(login, contra);
	}
	
	public List<AdministradorDTO> listarAdministrador(){
		return objAdministradorDAO.listarAdministrador();
	}
	
	public int eliminarAdministrador(int cod) {
		return objAdministradorDAO.eliminarAdministrador(cod);
	}
	
	public int registrarAdministrador(AdministradorDTO obj) {
		return objAdministradorDAO.registrarAdministrador(obj);
	}
	
	public int actualizarAdministrador(AdministradorDTO obj) {
		return objAdministradorDAO.actualizarAdministrador(obj);
	}
	
	public AdministradorDTO buscarAdministrador(int cod) {
		return objAdministradorDAO.buscarAdministrador(cod);
	}
}
