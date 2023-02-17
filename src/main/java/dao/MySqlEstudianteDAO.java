package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.EstudianteDTO;
import interfaces.EstudianteDAO;
import utils.MysqlDBConexion;

public class MySqlEstudianteDAO implements EstudianteDAO {

	@Override
	public List<EstudianteDTO> listarEstudiantes() {
		
		List<EstudianteDTO> data = new ArrayList<EstudianteDTO>();
		EstudianteDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from estudiante";
			System.out.println("Se realizo la consulta: " + sql);
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new EstudianteDTO();
				obj.setIdeEst(rs.getInt(1));
				obj.setNomEst(rs.getString(2));
				obj.setApeEst(rs.getString(3));
				obj.setDniEst(rs.getString(4));
				obj.setGenEst(rs.getString(5));
				obj.setFecEst(rs.getString(6));
				obj.setEmaEst(rs.getString(7));
				obj.setTelEst(rs.getString(8));
				obj.setDirEst(rs.getString(9));
				data.add(obj);
			}
			System.out.println("Listado de estudiantes correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al listar estudiantes");
		} finally {
			try {
				if(cn != null) {
					cn.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}

	@Override
	public EstudianteDTO buscasCodigo(int cod) {
		
		EstudianteDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from estudiante where ide_est=?";
			System.out.println("Se realizo la consulta: " + sql);
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new EstudianteDTO();
				obj.setIdeEst(rs.getInt(1));
				obj.setNomEst(rs.getString(2));
				obj.setApeEst(rs.getString(3));
				obj.setDniEst(rs.getString(4));
				obj.setGenEst(rs.getString(5));
				obj.setFecEst(rs.getString(6));
				obj.setEmaEst(rs.getString(7));
				obj.setTelEst(rs.getString(8));
				obj.setDirEst(rs.getString(9));
			}
			System.out.println("Codigo de estudiante buscado correctamente");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al buscar codigo de estudiante");
		} finally {
			try {
				if(cn != null) {
					cn.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return obj;
	}

	@Override
	public int registrarEstudiante(EstudianteDTO obj) {
		
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "insert into estudiante values (null,?,?,?,?,?,?,?,?)";
			System.out.println("Se realizo la consulta: " + sql);
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getNomEst());
			pstm.setString(2, obj.getApeEst());
			pstm.setString(3, obj.getDniEst());
			pstm.setString(4, obj.getGenEst());
			pstm.setString(5, obj.getFecEst());
			pstm.setString(6, obj.getEmaEst());
			pstm.setString(7, obj.getTelEst());
			pstm.setString(8, obj.getDirEst());
			estado = pstm.executeUpdate();
			
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return estado;
	}

	@Override
	public int actualizarEstudiante(EstudianteDTO obj) {
		
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {	
			cn = MysqlDBConexion.getConexion();
			String sql = "update estudiante set nom_est=?,ape_est=?,dni_est=?,gen_est=?,fec_est=?,ema_est=?,tel_est=?,dir_est=? where ide_est=?";
			System.out.println("Se realizo la consulta: " + sql);
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getNomEst());
			pstm.setString(2, obj.getApeEst());
			pstm.setString(3, obj.getDniEst());
			pstm.setString(4, obj.getGenEst());
			pstm.setString(5, obj.getFecEst());
			pstm.setString(6, obj.getEmaEst());
			pstm.setString(7, obj.getTelEst());
			pstm.setString(8, obj.getDirEst());
			pstm.setInt(9, obj.getIdeEst());
			estado = pstm.executeUpdate();	
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
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
		
		return estado;
	}

	@Override
	public int eliminarEstudiante(int cod) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "delete from estudiante where ide_est=?";
			System.out.println("Se realizo la consulta: " + sql);
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			estado = pstm.executeUpdate();			
			
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return estado;
	}

}
