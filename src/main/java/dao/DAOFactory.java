package dao;

import interfaces.AdministradorDAO;
import interfaces.MateriaDAO;
import interfaces.ProfesorDAO;
import interfaces.CarreraDAO;
import interfaces.EstudianteDAO;
import interfaces.MatriculaDAO;
import interfaces.ContratoDAO;

// abstract
public abstract class DAOFactory {
	
	// atributosEstaticosDeGestoresDBs
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int DB2 = 3;
	public static final int SQLSERVER = 4;
	public static final int XML = 5;

	/* 
	 * *************************************************
	 * DAO's
	 * *************************************************
	 * */
	// administradorDao
	public abstract AdministradorDAO getAdministrador();
	// MateriaDao
	public abstract MateriaDAO getMateria();
	// ProfesorDao
	public abstract ProfesorDAO getProfesor();
	// CarrerasDao
	public abstract CarreraDAO getCarrera();
	// EstudianteDao
	public abstract EstudianteDAO getEstudiante();
	// MatriculaDao
	public abstract MatriculaDAO getMatricula();
	// ContratoDao
	public abstract ContratoDAO getContrato();
	/*
	 ****************************************************
	 * */

	// metodoStatic
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL:
			// crearUnaClaseMySqlDAOFactory()
			return new MySqlDAOFactory();
		case ORACLE:
			// return new OracleDAOFactory();
		case SQLSERVER:
			// return new SqlDAOFactory();
		case XML:
			// return new XmlDAOFactory();
		}
		return null;
	}
	
}
