package services;

import dao.DAOFactory;
import utils.Constantes;
import beans.ContratoDTO;
import interfaces.ContratoDAO;
import java.util.List;

public class ContratoService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	ContratoDAO objcon = fabrica.getContrato();
	
	public List<ContratoDTO> listaContratos(){
		return objcon.listarContratos();
	}
	
	public ContratoDTO buscaCodigo(int cod) {
		return objcon.buscarCodigo(cod);
	}
	public int registraContrato(ContratoDTO obj) {
		return objcon.registrarContrato(obj);
	}
	public int actualizaContrato(ContratoDTO obj) {
		return objcon.actualizarContrato(obj);
	}
	public int eliminaContrato(int cod) {
		return objcon.eliminarContrato(cod);
		
	}
}
