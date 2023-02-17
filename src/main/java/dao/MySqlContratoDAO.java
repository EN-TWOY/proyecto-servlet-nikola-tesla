package dao;

import java.util.List;

import beans.ContratoDTO;
import interfaces.ContratoDAO;
import utils.MysqlDBConexion;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlContratoDAO implements ContratoDAO {

	@Override
	public List<ContratoDTO> listarContratos() {
		
		List<ContratoDTO> temporal = new ArrayList<ContratoDTO>();
		ContratoDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs  = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select c.num_con, c.fec_con, concat(a.nom_adm, space(1), a.ape_adm), concat(p.nom_pro, space(1), p.ape_pro), c.sue_con from contrato c"
					+ "	inner join administrador a on c.ide_adm = a.ide_adm"
					+ " inner join profesor p on c.ide_pro = p.ide_pro";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new ContratoDTO();
				obj.setCodContrato(rs.getInt(1));
				obj.setFecContrato(rs.getString(2));
				obj.setNomAdministrador(rs.getString(3));
				obj.setNomProfesor(rs.getString(4));
				obj.setSueContrato(rs.getDouble(5));
				temporal.add(obj);
			}
			System.out.println("Listado de contratos exitosamente");
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
	public ContratoDTO buscarCodigo(int cod) {
		
		ContratoDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from contrato where num_con=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new ContratoDTO();
				obj.setCodContrato(rs.getInt(1));
				obj.setFecContrato(rs.getString(2));
				obj.setCodAdministrador(rs.getInt(3));
				obj.setCodProfesor(rs.getInt(4));
				obj.setSueContrato(rs.getDouble(5));
			}

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
	public int registrarContrato(ContratoDTO obj) {
		
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "insert into contrato values(null,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getFecContrato());
			pstm.setInt(2, obj.getCodAdministrador());
			pstm.setInt(3, obj.getCodProfesor());
			pstm.setDouble(4, obj.getSueContrato());
			estado = pstm.executeUpdate();	
			System.out.println("Registro de contrato exitoso");
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
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		return estado;
	}

	@Override
	public int actualizarContrato(ContratoDTO obj) {
		
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "update contrato set fec_con=?, ide_adm=?, ide_pro=?, sue_con=? where num_con=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getFecContrato());
			pstm.setInt(2, obj.getCodAdministrador());
			pstm.setInt(3, obj.getCodProfesor());
			pstm.setDouble(4, obj.getSueContrato());
			pstm.setInt(5, obj.getCodContrato());
			estado = pstm.executeUpdate();
			System.out.println("Actualizacion de contrato exitoso");
			
		}catch(Exception e) {
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
	public int eliminarContrato(int cod) {
		
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "delete from contrato where num_con=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			estado = pstm.executeUpdate();
			System.out.println("Contrato eliminado exitosamente");
			
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
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return estado;
	}

}
