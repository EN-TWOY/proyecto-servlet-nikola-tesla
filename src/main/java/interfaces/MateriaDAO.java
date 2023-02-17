package interfaces;

import java.util.List;

import beans.MateriaDTO;
import beans.ModalidadDTO;

public interface MateriaDAO {
	
	// interfacesMateria
	public List<MateriaDTO> listarMateria();
	public int actualizarMateria(MateriaDTO obj);
	public MateriaDTO buscarMateria(int cod);
	public int registrarMateria(MateriaDTO obj);
	public int eliminarMateria(int cod);
	public List<ModalidadDTO> listarModalidad();
	
}
