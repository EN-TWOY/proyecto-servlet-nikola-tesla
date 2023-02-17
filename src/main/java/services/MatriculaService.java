package services;

import dao.DAOFactory;
import interfaces.MatriculaDAO;
import utils.Constantes;
import java.util.List;
import beans.MatriculaDTO;

public class MatriculaService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	MatriculaDAO objmat = fabrica.getMatricula();
	
	public List<MatriculaDTO> listaMatriculas(){
		return objmat.listarMatriculas();
	}
	public MatriculaDTO buscaCodigo(int cod) {
		return objmat.buscarCodigo(cod);
	}
	public int registraMatricula(MatriculaDTO obj) {
		return objmat.registrarMatricula(obj);
	}
	public int actualizaMatricula(MatriculaDTO obj) {
		return objmat.actualizarMatricula(obj);
	}
	public int eliminaMatricula(int cod) {
		return objmat.eliminarMatricula(cod);
	}
}