package interfaces;

import java.util.List;
import beans.ProfesorDTO;

public interface ProfesorDAO {

	public List<ProfesorDTO> listarProfesores();

	public ProfesorDTO buscarCodigo(int cod);

	public int registrarProfesor(ProfesorDTO obj);

	public int actualizarProfesor(ProfesorDTO obj);

	public int eliminarProfesor(int cod);
}
