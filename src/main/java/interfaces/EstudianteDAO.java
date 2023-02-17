package interfaces;
import beans.EstudianteDTO;
import java.util.List;

public interface EstudianteDAO {
	
	public List<EstudianteDTO> listarEstudiantes();
	public EstudianteDTO buscasCodigo(int cod);
	public int registrarEstudiante(EstudianteDTO obj);
	public int actualizarEstudiante(EstudianteDTO obj);
	public int eliminarEstudiante(int cod);
}
