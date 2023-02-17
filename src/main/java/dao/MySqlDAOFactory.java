package dao;

import interfaces.AdministradorDAO;
import interfaces.CarreraDAO;
import interfaces.ContratoDAO;
import interfaces.EstudianteDAO;
import interfaces.MateriaDAO;
import interfaces.MatriculaDAO;
import interfaces.ProfesorDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public AdministradorDAO getAdministrador() {
		return new MySqlAdministradorDAO();
	}
	
	@Override
	public MateriaDAO getMateria() {
		return new MySqlMateriaDAO();
	}

	@Override
	public ProfesorDAO getProfesor() {
		return new MySqlProfesorDAO();
	}

	@Override
	public CarreraDAO getCarrera() {
		return new MySqlCarreraDAO();
	}

	@Override
	public EstudianteDAO getEstudiante() {
		return new MySqlEstudianteDAO();
	}

	@Override
	public MatriculaDAO getMatricula() {
		return new MySqlMatriculaDAO();
	}

	@Override
	public ContratoDAO getContrato() {
		return new MySqlContratoDAO();
	}

}
