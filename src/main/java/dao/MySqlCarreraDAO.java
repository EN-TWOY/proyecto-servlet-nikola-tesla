package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.CarreraDTO;
import interfaces.CarreraDAO;
import utils.MysqlDBConexion;

public class MySqlCarreraDAO implements CarreraDAO {

	@Override
	public List<CarreraDTO> listarCarreras() {
		
		List<CarreraDTO> data = new ArrayList<CarreraDTO>();
		CarreraDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from carrera";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new CarreraDTO();
				obj.setIdeCarrera(rs.getInt(1));
				obj.setDesCarrera(rs.getString(2));
				data.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return data;
	}

	@Override
	public CarreraDTO buscarCodigo(int cod) {
		
		CarreraDTO obj = null;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from carrera where ide_car=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new CarreraDTO();
				obj.setIdeCarrera(rs.getInt(1));
				obj.setDesCarrera(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return obj;
	}

	@Override
	public int registrarCarrera(CarreraDTO obj) {
		
		int estado = -1;	
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "insert into carrera values (null,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getDesCarrera());
			estado = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return estado;
	}

	@Override
	public int actualizarCarrera(CarreraDTO obj) {
		
		int estado = -1;	
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "update carrera set des_car=? where ide_car=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, obj.getDesCarrera());
			pstm.setInt(2, obj.getIdeCarrera());		
			estado = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int eliminarCarrera(int cod) {
		
		int estado = -1;	
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "delete from carrera where ide_car=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			estado = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

}
