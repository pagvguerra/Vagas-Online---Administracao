package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.CidadeBean;
import br.com.projeto.db.DB;

public class CidadeDAO {

	private static final String LISTAR_TODOS 				=	"SELECT * FROM CIDADE";
	private static final String LISTAR_CIDADE_POR_ESTADO 	=	"SELECT * FROM CIDADE WHERE ID_ESTADO = ?";
	private static final String BUSCA_POR_ID 				=	"SELECT * FROM CIDADE WHERE ID = ?";

	public String buscaCidadePorId(int id) {
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
			System.out.println("Erro no metodo buscaCidadePorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return nome;
	}
	
	public List<CidadeBean> listaTodos() {
		
		Connection conn						=	null;
		PreparedStatement pstmt				=	null;
		ResultSet rs						=	null;
		List<CidadeBean> listaCidadeBean	=	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS);
			rs		=	pstmt.executeQuery();
			
			listaCidadeBean 				=	new ArrayList<CidadeBean>();

			while(rs.next()) {
				CidadeBean cidadeBean		=	new CidadeBean();
				cidadeBean.setId(rs.getInt("ID"));
				cidadeBean.setNome(rs.getString("NOME"));
				listaCidadeBean.add(cidadeBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaCidadeBean;
	}
	
	public List<CidadeBean> listaCidadePorEstado(int idEstado) {
		
		Connection conn						=	null;
		PreparedStatement pstmt				=	null;
		ResultSet rs						=	null;
		List<CidadeBean> listaCidadeBean	=	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_CIDADE_POR_ESTADO);
			pstmt.setInt(1, idEstado);
			rs		=	pstmt.executeQuery();
			
			listaCidadeBean 				=	new ArrayList<CidadeBean>();

			while(rs.next()) {
				CidadeBean cidadeBean		=	new CidadeBean();
				cidadeBean.setId(rs.getInt("ID"));
				cidadeBean.setNome(rs.getString("NOME"));
				listaCidadeBean.add(cidadeBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaCidadePorEstado. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaCidadeBean;
	}
	
}
