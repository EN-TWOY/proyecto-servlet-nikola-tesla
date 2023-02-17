package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.AdministradorDTO;
import interfaces.AdministradorDAO;
import utils.MysqlDBConexion;

public class MySqlAdministradorDAO implements AdministradorDAO {

	/*
	 * **************************
	 * iniciarSesionAdministrador
	 * **************************
	 * */
	@Override
	public AdministradorDTO iniciarSesion(String login, String contra) {
		AdministradorDTO obj = null;
		
		// plantillaIniciarSesion
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from administrador where usuario = ? and contra = ? ";
			pstm = cn.prepareStatement(sql);
			System.out.println("Se ejecuto el comando " + sql);

			pstm.setString(1, login);
			pstm.setString(2, contra);

			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new AdministradorDTO();
				obj.setIdAdmin(rs.getInt(1));
				obj.setNomAdmin(rs.getString(2));
				obj.setApeAdmin(rs.getString(3));
				obj.setDniAdmin(rs.getString(4));
				obj.setGenAdmin(rs.getString(5));
				obj.setFecAdmin(rs.getString(6));
				obj.setEmaAdmin(rs.getString(7));
				obj.setTelAdmin(rs.getString(8));
				obj.setDirAdmin(rs.getString(9));
				obj.setUsuAdmin(rs.getString(10));
				obj.setContraAdmin(rs.getString(11));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		// endPlantilla
		
		return obj;
	}

	/*
	 * *******************
	 * listarAdministrador
	 * *******************
	 * */
	@Override
	public List<AdministradorDTO> listarAdministrador() {
		// variableLocal
		List<AdministradorDTO> data = new ArrayList<AdministradorDTO>();
		
		// plantillaListarAdministrador
		AdministradorDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from administrador ";
			System.out.println("Se ejecuto la consulta: " + sql);
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				obj = new AdministradorDTO();
				obj.setIdAdmin(rs.getInt(1));
				obj.setNomAdmin(rs.getString(2));
				obj.setApeAdmin(rs.getString(3));
				obj.setDniAdmin(rs.getString(4));;
				obj.setGenAdmin(rs.getString(5));
				obj.setFecAdmin(rs.getString(6));
				obj.setEmaAdmin(rs.getString(7));
				obj.setTelAdmin(rs.getString(8));
				obj.setDirAdmin(rs.getString(9));
				obj.setUsuAdmin(rs.getString(10));
				obj.setContraAdmin(rs.getString(11));
				data.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		// endPlantilla
		
		return data;
	}

	/*
	 * *********************
	 * eliminarAdministrador
	 * *********************
	 * */
	@Override
	public int eliminarAdministrador(int cod) {
		int estado = -1;

		// plantillaEliminarAdministrador
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "delete from administrador where ide_adm=?";
			System.out.println("Se ejecuto la consulta: " + sql);
			pstm = cn.prepareStatement(sql);

			pstm.setInt(1, cod);

			estado = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		// endPlantilla

		return estado;
	}

	/*
	 * *********************
	 * buscarAdministrador
	 * *********************
	 * */
	@Override
	public AdministradorDTO buscarAdministrador(int cod) {
		AdministradorDTO obj = null;
		
		//
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from administrador where ide_adm = ? ";
			System.out.println("Se ejecuto la consulta: " + sql);
			
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				obj = new AdministradorDTO();
				obj.setIdAdmin(rs.getInt(1));
				obj.setNomAdmin(rs.getString(2));
				obj.setApeAdmin(rs.getString(3));
				obj.setDniAdmin(rs.getString(4));
				obj.setGenAdmin(rs.getString(5));
				obj.setFecAdmin(rs.getString(6));
				obj.setEmaAdmin(rs.getString(7));
				obj.setTelAdmin(rs.getString(8));
				obj.setDirAdmin(rs.getString(9));
				obj.setUsuAdmin(rs.getString(10));
				obj.setContraAdmin(rs.getString(11));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cn != null) cn.close();
				if(pstm != null) pstm.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//
		
		return obj;
	}

	/*
	 * ***********************
	 * actualizarAdministrador
	 * ***********************
	 * */
	@Override
	public int actualizarAdministrador(AdministradorDTO obj) {
		int estado = - 1;
		
		//
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "update administrador set nom_adm=?, ape_adm=?, dni_adm=?, gen_adm=?, fec_adm=?, ema_adm=?, tel_adm=?, dir_adm=?, usuario=?, contra=? where ide_adm=? ";
			System.out.println("Se ejecuto la consulta: " + sql);
			
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getNomAdmin());
			pstm.setString(2, obj.getApeAdmin());
			pstm.setString(3, obj.getDniAdmin());
			pstm.setString(4, obj.getGenAdmin());
			pstm.setString(5, obj.getFecAdmin());
			pstm.setString(6, obj.getEmaAdmin());
			pstm.setString(7, obj.getTelAdmin());
			pstm.setString(8, obj.getDirAdmin());
			pstm.setString(9, obj.getUsuAdmin());			
			pstm.setString(10, obj.getContraAdmin());			
			pstm.setInt(11, obj.getIdAdmin());
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//
		
		return estado;
	}

	/*
	 * **********************
	 * registrarAdministrador
	 * **********************
	 * */
	@Override
	public int registrarAdministrador(AdministradorDTO obj) {
		int estado = - 1;

		//
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "insert into administrador values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			System.out.println("Se ejecuto la consulta: " + sql);
			
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getNomAdmin());
			pstm.setString(2, obj.getApeAdmin());
			pstm.setString(3, obj.getDniAdmin());
			pstm.setString(4, obj.getGenAdmin());
			pstm.setString(5, obj.getFecAdmin());
			pstm.setString(6, obj.getEmaAdmin());
			pstm.setString(7, obj.getTelAdmin());
			pstm.setString(8, obj.getDirAdmin());
			pstm.setString(9, obj.getUsuAdmin());
			pstm.setString(10, obj.getContraAdmin());
			estado = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//
		
		return estado;
	}


	

}
