package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.db.DB;

public class UsuarioDAO {

	private static final String BUSCA_USUARIO_POR_LOGIN 	= 	"SELECT ID, EMAIL FROM USUARIO WHERE LOGIN = ?";
	private static final String VALIDAR_RESPOSTAS_USUARIO 	=	"SELECT 1 FROM USUARIO_RESPOSTA WHERE ID_USUARIO = ? AND RESPOSTA1 = ? AND RESPOSTA2 = ? AND RESPOSTA3 = ? AND RESPOSTA4 = ?";

	public UsuarioBean buscarPorLogin(String login) {
		
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		UsuarioBean usuarioBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_USUARIO_POR_LOGIN);
			pstmt.setString(1, login);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				usuarioBean		=	new UsuarioBean();
				usuarioBean.setId(rs.getInt("ID"));
				usuarioBean.setEmail(rs.getString("EMAIL"));
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorLogin. Pilha: " + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return usuarioBean;	
	}

	public boolean validaRespostasUsuario(UsuarioBean usuarioBean) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(VALIDAR_RESPOSTAS_USUARIO);
			pstmt.setInt(1, usuarioBean.getId());
			pstmt.setString(2, usuarioBean.getResposta1());
			pstmt.setString(3, usuarioBean.getResposta2());
			pstmt.setString(4, usuarioBean.getResposta3());
			pstmt.setString(5, usuarioBean.getResposta4());
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo validaRespostasUsuario. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, rs);
		}
	}

}