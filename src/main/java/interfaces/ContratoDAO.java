package interfaces;

import java.util.List;
import beans.ContratoDTO;

public interface ContratoDAO {
	
	public List<ContratoDTO> listarContratos();
	public ContratoDTO buscarCodigo(int cod);
	public int registrarContrato(ContratoDTO obj);
 	public int actualizarContrato(ContratoDTO obj);
	public int eliminarContrato(int cod);
}
