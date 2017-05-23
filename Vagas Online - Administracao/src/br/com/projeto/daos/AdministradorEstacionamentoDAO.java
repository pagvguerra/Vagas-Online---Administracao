package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.projeto.beans.AdministradorEstacionamentoBean;
import br.com.projeto.db.DB;
import br.com.projeto.interfaces.GenericDAO;

public class AdministradorEstacionamentoDAO implements GenericDAO<AdministradorEstacionamentoBean> {

	private static final String INSERIR_USUARIO_ADMINISTRADOR_ESTACIONAMENTO		=	"INSERT INTO USUARIO(CPF, EMAIL, LOGIN, NOME, ID_PERFIL, RG, SENHA, SEXO, STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, 'A')";
	private static final String ATUALIZAR_USUARIO_ADMINISTRADOR_ESTACIONAMENTO		=	"UPDATE USUARIO SET CPF = ?, EMAIL = ?, NOME = ?, ID_PERFIL = ?, RG = ?, SEXO = ? WHERE ID = ?";
	private static final String DESATIVAR_USUARIO_ADMINISTRADOR_ESTACIONAMENTO		=	"UPDATE USUARIO SET STATUS = 'E' WHERE ID = ?";
	private static final String BUSCA_USUARIO_ADMINISTRADOR_ESTACIONAMENTO_POR_ID 	=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, ID_PERFIL, RG, SENHA, SEXO, STATUS FROM USUARIO WHERE ID = ?";

	@Override
	public boolean inserir(AdministradorEstacionamentoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_USUARIO_ADMINISTRADOR_ESTACIONAMENTO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getLogin());					
			pstmt.setString(4, obj.getNome());
			pstmt.setInt(5, obj.getPerfil());
			pstmt.setString(6, obj.getRg());
			pstmt.setString(7, obj.getSenha());
			pstmt.setString(8, obj.getSexo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo inserir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;
	}

	@Override
	public boolean alterar(AdministradorEstacionamentoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_USUARIO_ADMINISTRADOR_ESTACIONAMENTO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getNome());
			pstmt.setInt(4, obj.getPerfil());
			pstmt.setString(5, obj.getRg());
			pstmt.setString(6, obj.getSexo());
			pstmt.setInt(7, obj.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo alterar. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return true;
	}

	public boolean desativar(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(DESATIVAR_USUARIO_ADMINISTRADOR_ESTACIONAMENTO);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo desativar. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;	
	}

	@Override
	public AdministradorEstacionamentoBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		AdministradorEstacionamentoBean administradorEstacionamentoBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_USUARIO_ADMINISTRADOR_ESTACIONAMENTO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				administradorEstacionamentoBean		=	new AdministradorEstacionamentoBean();
				administradorEstacionamentoBean.setId(rs.getInt("ID"));
				administradorEstacionamentoBean.setCpf(rs.getString("CPF"));
				administradorEstacionamentoBean.setNome(rs.getString("NOME"));
				administradorEstacionamentoBean.setLogin(rs.getString("LOGIN"));
				administradorEstacionamentoBean.setSenha(rs.getString("SENHA"));
				administradorEstacionamentoBean.setEmail(rs.getString("EMAIL"));
				administradorEstacionamentoBean.setSexo(rs.getString("SEXO"));
				administradorEstacionamentoBean.setPerfil(rs.getInt("ID_PERFIL"));
				administradorEstacionamentoBean.setRg(rs.getString("RG"));
				administradorEstacionamentoBean.setStatus(rs.getString("STATUS"));
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return administradorEstacionamentoBean;	
	}

	@Override
	public List<AdministradorEstacionamentoBean> listaTodos() {
		return null;
	}

	@Override
	public boolean excluir(int id) {
		return false;
	}

}

