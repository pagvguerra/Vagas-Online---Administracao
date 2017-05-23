package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.TipoLogradouroBean;
import br.com.projeto.db.DB;

public class TipoLogradouroDAO {

	private static final String LISTAR_TODOS 	=	"SELECT * FROM TIPO_LOGRADOURO";
	private static final String BUSCA_POR_ID 	=	"SELECT * FROM TIPO_LOGRADOURO WHERE ID = ?";

	public String buscaNomeLogradouroPorId(int id) {
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
			System.out.println("Erro no metodo buscaNomeLogradouroPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return nome;
	}

	public List<TipoLogradouroBean> listaTodos() {
		
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<TipoLogradouroBean> listaTipoLogradouroBean =	null;

		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS);
			rs		=	pstmt.executeQuery();
			
			listaTipoLogradouroBean 		=	new ArrayList<TipoLogradouroBean>();

			while(rs.next()) {
				TipoLogradouroBean tipoLogradouroBean		=	new TipoLogradouroBean();
				tipoLogradouroBean.setId(rs.getInt("ID"));
				tipoLogradouroBean.setNome(rs.getString("NOME"));
				listaTipoLogradouroBean.add(tipoLogradouroBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaTipoLogradouroBean;
	}
	
}
