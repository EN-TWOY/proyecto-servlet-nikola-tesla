package interfaces;

import java.util.List;

import beans.AdministradorDTO;

public interface AdministradorDAO {
	
	public AdministradorDTO iniciarSesion(String login, String contra);
	public List<AdministradorDTO> listarAdministrador();
	public int eliminarAdministrador(int cod);
	public AdministradorDTO buscarAdministrador(int cod);
	public int actualizarAdministrador(AdministradorDTO obj);
	public int registrarAdministrador(AdministradorDTO obj);
	
}
