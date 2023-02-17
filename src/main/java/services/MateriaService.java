package services;

import java.util.List;

import beans.MateriaDTO;
import dao.DAOFactory;
import interfaces.MateriaDAO;
import utils.Constantes;
import beans.ModalidadDTO;

public class MateriaService {
	// llamandoMySqlFactory
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	
	// llamandoMateriaMySql
	MateriaDAO objMateriaDAO = fabrica.getMateria();
	
	/*
	 * ************************
	 * llamarAndRetornarMetodos
	 * ************************
	 * */
	public List<MateriaDTO> listarMateria(){
		return objMateriaDAO.listarMateria();
	}
	
	public int actualizarMateria(MateriaDTO obj) {
		return objMateriaDAO.actualizarMateria(obj);
	}
	
	public MateriaDTO buscarMateria(int cod) {
		return objMateriaDAO.buscarMateria(cod);
	}
	
	public int registrarMateria(MateriaDTO obj) {
		return objMateriaDAO.registrarMateria(obj);
	}
	
	public int eliminarMateria(int cod) {
		return objMateriaDAO.eliminarMateria(cod);
	}
	
	public List<ModalidadDTO> listaModalidad(){
		return objMateriaDAO.listarModalidad();
	}
}
