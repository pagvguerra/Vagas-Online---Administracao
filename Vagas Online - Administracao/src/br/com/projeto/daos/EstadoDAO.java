package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.EstadoBean;
import br.com.projeto.db.DB;

public class EstadoDAO {

	private static final String LISTAR_TODOS 			=	"SELECT * FROM ESTADO";
	private static final String LISTAR_ESTADO_POR_PAIS 	=	"SELECT * FROM ESTADO WHERE ID_PAIS = ?";
	private static final String BUSCA_POR_ID 			=	"SELECT * FROM ESTADO WHERE ID = ?";

	public String buscaEstadoPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		String nome 				=	"";

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				nome = rs.getString("NOME");
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaEstadoPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return nome;
	}
	
	public List<EstadoBean> listaTodos() {
		
		Connection conn						=	null;
		PreparedStatement pstmt				=	null;
		ResultSet rs						=	null;
		List<EstadoBean> listaEstadoBean	=	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS);
			rs		=	pstmt.executeQuery();
			
			listaEstadoBean 				=	new ArrayList<EstadoBean>();

			while(rs.next()) {
				EstadoBean estadoBean		=	new EstadoBean();
				estadoBean.setId(rs.getInt("ID"));
				estadoBean.setNome(rs.getString("NOME"));
				listaEstadoBean.add(estadoBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaEstadoBean;
	}
	
	public List<EstadoBean> listaEstadosPorPais(int idPais) {
		
		Connection conn						=	null;
		PreparedStatement pstmt				=	null;
		ResultSet rs						=	null;
		List<EstadoBean> listaEstadoBean	=	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_ESTADO_POR_PAIS);
			pstmt.setInt(1, idPais);
			rs		=	pstmt.executeQuery();
			
			listaEstadoBean 				=	new ArrayList<EstadoBean>();

			while(rs.next()) {
				EstadoBean estadoBean		=	new EstadoBean();
				estadoBean.setId(rs.getInt("ID"));
				estadoBean.setNome(rs.getString("NOME"));
				listaEstadoBean.add(estadoBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaEstadosPorPais. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaEstadoBean;
	}

}
