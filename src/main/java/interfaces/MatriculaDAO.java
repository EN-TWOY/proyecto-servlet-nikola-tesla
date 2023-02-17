package interfaces;

import beans.MatriculaDTO;
import java.util.List;

public interface MatriculaDAO {
	
	public List<MatriculaDTO> listarMatriculas();
	public MatriculaDTO buscarCodigo(int cod);
	public int registrarMatricula(MatriculaDTO obj);
	public int actualizarMatricula(MatriculaDTO obj);
	public int eliminarMatricula(int cod);
}
