package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.PaisBean;
import br.com.projeto.db.DB;

public class PaisDAO {

	private static final String LISTAR_TODOS 	=	"SELECT * FROM PAIS";
	private static final String BUSCA_POR_ID 	=	"SELECT * FROM PAIS WHERE ID = ?";

	public String buscaPaisPorId(int id) {
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
			System.out.println("Erro no metodo buscaPaisPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return nome;
	}
	
	public List<PaisBean> listaTodos() {
		
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<PaisBean> listaPaisBean =	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS);
			rs		=	pstmt.executeQuery();
			
			listaPaisBean 		=	new ArrayList<PaisBean>();

			while(rs.next()) {
				PaisBean paisBean		=	new PaisBean();
				paisBean.setId(rs.getInt("ID"));
				paisBean.setNome(rs.getString("NOME"));
				listaPaisBean.add(paisBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaPaisBean;
	}
	
}
