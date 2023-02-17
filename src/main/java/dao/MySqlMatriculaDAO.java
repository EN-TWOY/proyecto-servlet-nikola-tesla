package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.MatriculaDTO;
import interfaces.MatriculaDAO;
import utils.MysqlDBConexion;

public class MySqlMatriculaDAO implements MatriculaDAO {

	@Override
	public List<MatriculaDTO> listarMatriculas() {
		
		List<MatriculaDTO> temporal = new ArrayList<MatriculaDTO>();
		MatriculaDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm  = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select m.num_mat, m.fec_mat, concat(a.nom_adm,space(1),a.ape_adm), concat(e.nom_est,space(1),e.ape_est), c.des_car, t.des_tur, s.des_sed from matricula m"
					+ "		inner join administrador a on m.ide_adm = a.ide_adm"
					+ "    inner join estudiante e on m.ide_est = e.ide_est"
					+ "    inner join carrera c on m.ide_car = c.ide_car"
					+ "    inner join turno t on m.ide_tur = t.ide_tur"
					+ "    inner join sede s on m.ide_sed = s.ide_sed";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new MatriculaDTO();
				obj.setNumMatricula(rs.getInt(1));
				obj.setFecMatricula(rs.getString(2));
				obj.setNomAdmin(rs.getString(3));
				obj.setNomEstudiante(rs.getString(4));
				obj.setNomCarrera(rs.getString(5));
				obj.setNomTurno(rs.getString(6));
				obj.setNomSede(rs.getString(7));
				temporal.add(obj);
			}
			System.out.println("Listado de matricula exitosamente");
			
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
				if(rs != null) {
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return temporal;
	}

	@Override
	public MatriculaDTO buscarCodigo(int cod) {
		MatriculaDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from matricula where num_mat=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				obj = new MatriculaDTO();
				obj.setNumMatricula(rs.getInt(1));
				obj.setFecMatricula(rs.getString(2));
				obj.setCodAdmin(rs.getInt(3));
				obj.setCodEstudiante(rs.getInt(4));
				obj.setCodCarrera(rs.getInt(5));
				obj.setCodTurno(rs.getInt(6));
				obj.setCodSede(rs.getInt(7));
			}
			System.out.println("Codigo de matricula encontrado");
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
	public int registrarMatricula(MatriculaDTO obj) {
		
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			 cn = MysqlDBConexion.getConexion();
			 String sql = "insert into matricula values (null,?,?,?,?,?,?)";
			 pstm = cn.prepareStatement(sql);
			 pstm.setString(1, obj.getFecMatricula());
			 pstm.setInt(2, obj.getCodAdmin());
			 pstm.setInt(3, obj.getCodEstudiante());
			 pstm.setInt(4, obj.getCodCarrera());
			 pstm.setInt(5, obj.getCodTurno());
			 pstm.setInt(6, obj.getCodSede());
			 estado = pstm.executeUpdate();
			 System.out.println("Matricula registrado exitosamente");
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
	public int actualizarMatricula(MatriculaDTO obj) {

		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			 cn = MysqlDBConexion.getConexion();
			 String sql = "update matricula set fec_mat=?, ide_adm=?, ide_est=?, ide_car=?, ide_tur=?, ide_sed=? where num_mat=?";
			 pstm = cn.prepareStatement(sql);
			 pstm.setString(1, obj.getFecMatricula());
			 pstm.setInt(2, obj.getCodAdmin());
			 pstm.setInt(3, obj.getCodEstudiante());
			 pstm.setInt(4, obj.getCodCarrera());
			 pstm.setInt(5, obj.getCodTurno());
			 pstm.setInt(6, obj.getCodSede());
			 pstm.setInt(7, obj.getNumMatricula());
			 estado = pstm.executeUpdate();
			 System.out.println("Matricula actualizada correctamente");
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
	public int eliminarMatricula(int cod) {

		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			 cn = MysqlDBConexion.getConexion();
			 String sql = "delete from matricula where num_mat=? ";
			 pstm = cn.prepareStatement(sql);
			 pstm.setInt(1, cod);
			 estado = pstm.executeUpdate();
			 System.out.println("Matricula eliminada correctamente");
			
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
