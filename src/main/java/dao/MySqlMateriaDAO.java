package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.MateriaDTO;
import beans.ModalidadDTO;
import interfaces.MateriaDAO;
import utils.MysqlDBConexion;

public class MySqlMateriaDAO implements MateriaDAO {

	@Override
	public List<MateriaDTO> listarMateria() {
		List<MateriaDTO> data = new ArrayList<MateriaDTO>();
		
		// plantillaListar
		MateriaDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select m.ide_mat,m.des_mat,md.des_mod from materia m inner join modalidad md on m.ide_mod=md.ide_mod";
			System.out.println("Se ejecuto la consulta: " + sql);
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				obj = new MateriaDTO();
				obj.setIdMateria(rs.getInt(1));
				obj.setDesMateria(rs.getString(2));
				obj.setDesModalidad(rs.getString(3));
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
	 * *****************
	 * actualizarMateria
	 * *****************
	 * */
	@Override
	public int actualizarMateria(MateriaDTO obj) {
		int estado = -1;
		
		// 
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "update materia set des_mat=?,ide_mod=? where ide_mat =?";
			System.out.println("Se ejecuto la consulta: " + sql);
			
			pstm = cn.prepareStatement(sql);
			
			pstm.setString(1, obj.getDesMateria());
			pstm.setInt(2, obj.getIdModalidad());
			pstm.setInt(3, obj.getIdMateria());
			
			
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
		
		return estado ;
	}

	/*
	 * *************
	 * buscarMateria
	 * *************
	 * */
	@Override
	public MateriaDTO buscarMateria(int cod) {
		MateriaDTO obj = null;
		
		// 
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from materia where ide_mat = ? ";
			System.out.println("Se ejecuto la consulta: " + sql);
			
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();

			if (rs.next()) {
				obj = new MateriaDTO();
				obj.setIdMateria(rs.getInt(1));
				obj.setDesMateria(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null)
					cn.close();
				if (pstm != null)
					pstm.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//
		
		return obj;
	}

	/*
	 * ****************
	 * registrarMateria
	 * ****************
	 * */
	@Override
	public int registrarMateria(MateriaDTO obj) {
		int estado = -1;
		
		// 
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "insert into materia values(null,?,?) ";
			System.out.println("Se ejecuto la consulta: " + sql);
			
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getDesMateria());
			pstm.setInt(2, obj.getIdModalidad());
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
		
		return estado ;
	}

	/*
	 * ****************
	 * eliminarMateria
	 * ****************
	 * */
	@Override
	public int eliminarMateria(int cod) {
		int estado = -1;
		
		//
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "delete from materia where ide_mat = ? ";
			
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
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
		
		return estado ;
	}
	
	@Override
	public List<ModalidadDTO> listarModalidad(){
		
		List<ModalidadDTO> modalidades = new ArrayList<ModalidadDTO>();
		ModalidadDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql= "select * from modalidad";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new ModalidadDTO();
				obj.setIdeModalidad(rs.getInt(1));
				obj.setDesModalidad(rs.getString(2));
				modalidades.add(obj);
			}
			System.out.println("Modalidades de materia cargadas");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cn != null) {
					cn.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return modalidades;
	}

}
