package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ProfesorDTO;
import interfaces.ProfesorDAO;
import utils.MysqlDBConexion;

public class MySqlProfesorDAO implements ProfesorDAO {

	@Override
	public List<ProfesorDTO> listarProfesores() {
		
		List<ProfesorDTO> data = new ArrayList<ProfesorDTO>();
		ProfesorDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from profesor";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new ProfesorDTO();
				obj.setIdepro(rs.getInt(1));
				obj.setNompro(rs.getString(2));
				obj.setApepro(rs.getString(3));
				obj.setDnipro(rs.getString(4));
				obj.setGenpro(rs.getString(5));
				obj.setFecpro(rs.getString(6));
				obj.setEmapro(rs.getString(7));
				obj.setTelpro(rs.getString(8));
				obj.setDirpro(rs.getString(9));
				data.add(obj);	
			}
			System.out.println("Listado exitosamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al listar");
		}
		return data;
	}

	@Override
	public ProfesorDTO buscarCodigo(int cod) {
		
		ProfesorDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from profesor where ide_pro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new ProfesorDTO();
				obj.setIdepro(rs.getInt(1));
				obj.setNompro(rs.getString(2));
				obj.setApepro(rs.getString(3));
				obj.setDnipro(rs.getString(4));
				obj.setGenpro(rs.getString(5));
				obj.setFecpro(rs.getString(6));
				obj.setEmapro(rs.getString(7));
				obj.setTelpro(rs.getString(8));
				obj.setDirpro(rs.getString(9));	
			}	
			System.out.println("Buscado exitosamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al buscar");
		}
		return obj;
	}

	@Override
	public int registrarProfesor(ProfesorDTO obj) {
		
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "insert into profesor values (null,?,?,?,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getNompro());
			pstm.setString(2, obj.getApepro());
			pstm.setString(3, obj.getDnipro());
			pstm.setString(4, obj.getGenpro());
			pstm.setString(5, obj.getFecpro());
			pstm.setString(6, obj.getEmapro());
			pstm.setString(7, obj.getTelpro());
			pstm.setString(8, obj.getDirpro());
			estado = pstm.executeUpdate();
			System.out.println("Registrado exitosamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al registrar");
		}
		return estado;
	}

	@Override
	public int actualizarProfesor(ProfesorDTO obj) {
		
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "update profesor set nom_pro=?,ape_pro=?,dni_pro=?,gen_pro=?,fec_pro=?,ema_pro=?,tel_pro=?,dir_pro=? where ide_pro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getNompro());
			pstm.setString(2, obj.getApepro());
			pstm.setString(3, obj.getDnipro());
			pstm.setString(4, obj.getGenpro());
			pstm.setString(5, obj.getFecpro());
			pstm.setString(6, obj.getEmapro());
			pstm.setString(7, obj.getTelpro());
			pstm.setString(8, obj.getDirpro());
			pstm.setInt(9, obj.getIdepro());
			estado = pstm.executeUpdate();
			System.out.println("Actualizado exitosamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al actualizar");
		}
		return estado;
	}

	@Override
	public int eliminarProfesor(int cod) {
		
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "delete from profesor where ide_pro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			estado = pstm.executeUpdate();	
			System.out.println("Eliminado exitosamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al eliminar");
		}
		return estado;
	}
}
