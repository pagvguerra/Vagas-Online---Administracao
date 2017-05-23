package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.BairroBean;
import br.com.projeto.db.DB;

public class BairroDAO {
	
	private static final String LISTAR_TODOS 				=	"SELECT * FROM BAIRRO";
	private static final String LISTAR_BAIRRO_POR_CIDADE 	=	"SELECT * FROM BAIRRO WHERE ID_CIDADE = ?";
	private static final String BUSCA_POR_ID 				=	"SELECT * FROM BAIRRO WHERE ID = ?";

	public String buscaBairroPorId(int id) {
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
			System.out.println("Erro no metodo buscaBairroPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return nome;
	}
		
	public List<BairroBean> listaTodos() {
		
		Connection conn						=	null;
		PreparedStatement pstmt				=	null;
		ResultSet rs						=	null;
		List<BairroBean> listaBairroBean	=	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS);
			rs		=	pstmt.executeQuery();
			
			listaBairroBean 				=	new ArrayList<BairroBean>();

			while(rs.next()) {
				BairroBean bairroBean		=	new BairroBean();
				bairroBean.setId(rs.getInt("ID"));
				bairroBean.setNome(rs.getString("NOME"));
				listaBairroBean.add(bairroBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaBairroBean;
	}
	
	public List<BairroBean> listaBairroPorCidade(int idCidade) {
		
		Connection conn						=	null;
		PreparedStatement pstmt				=	null;
		ResultSet rs						=	null;
		List<BairroBean> listaBairroBean	=	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_BAIRRO_POR_CIDADE);
			pstmt.setInt(1, idCidade);
			rs		=	pstmt.executeQuery();
			
			listaBairroBean 				=	new ArrayList<BairroBean>();

			while(rs.next()) {
				BairroBean bairroBean		=	new BairroBean();
				bairroBean.setId(rs.getInt("ID"));
				bairroBean.setNome(rs.getString("NOME"));
				listaBairroBean.add(bairroBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaBairroPorCidade. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaBairroBean;
	}

}
