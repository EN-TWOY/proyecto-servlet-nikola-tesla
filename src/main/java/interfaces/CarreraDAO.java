package interfaces;

import java.util.List;

import beans.CarreraDTO;

public interface CarreraDAO {
	
	public List<CarreraDTO> listarCarreras();
	public CarreraDTO buscarCodigo(int cod);
	public int registrarCarrera(CarreraDTO obj);
	public int actualizarCarrera(CarreraDTO obj);
	public int eliminarCarrera(int cod);
}
