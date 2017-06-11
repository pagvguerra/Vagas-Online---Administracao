package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.FuncionarioBean;
import br.com.projeto.db.DB;
import br.com.projeto.interfaces.GenericDAO;
import br.com.projeto.utils.Util;

public class FuncionarioDAO implements GenericDAO<FuncionarioBean> {

	private static final String INSERIR_FUNCIONARIO 		=	"INSERT INTO USUARIO(CPF, EMAIL, LOGIN, SENHA, NOME, ID_PERFIL, RG, SEXO, ID_ESTACIONAMENTO_FUNC, STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 'A')";
	private static final String ATUALIZAR_FUNCIONARIO	 	=	"UPDATE USUARIO SET CPF = ?, EMAIL = ?, LOGIN = ?, NOME = ?, ID_PERFIL = ?, RG = ?, SEXO = ?  WHERE ID = ?";
	private static final String EXCLUIR_FUNCIONARIO 		=	"DELETE FROM USUARIO WHERE ID = ?";
	private static final String BUSCA_FUNCIONARIO_POR_ID 	=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, ID_PERFIL, RG, SEXO, SENHA FROM USUARIO WHERE ID = ?";
	
	private static final String LISTAR_TODOS_FUNCIONARIOS_POR_ESTACIONAMENTO 		=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, ID_PERFIL, RG, SEXO, SENHA FROM USUARIO WHERE ID_ESTACIONAMENTO_FUNC = ?";
	private static final String BUSCA_SE_EXISTE_ALGUM_FUNCIONARIO_NO_ESTACIONAMENTO	=	"SELECT ID_ESTACIONAMENTO_FUNC FROM USUARIO";
	private static final String ATUALIZAR_ESTACIONAMENTO_NO_FUNCIONARIO				=	"UPDATE USUARIO SET ID_ESTACIONAMENTO_FUNC = ? WHERE ID = ?";
	private static final String	BUSCA_ID_DO_FUNCIONARIO_POR_CPF_DO_FUNCIONARIO		=	"SELECT ID FROM USUARIO WHERE CPF = ?";
	private static final String	EXCLUIR_FUNCIONARIO_POR_ID_ESTACIONAMENTO			=	"DELETE FROM USUARIO WHERE ID_ESTACIONAMENTO_FUNC = ?";
	
	@Override
	public boolean inserir(FuncionarioBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_FUNCIONARIO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getLogin());					
			pstmt.setString(4, obj.getSenha());
			pstmt.setString(5, obj.getNome());
			pstmt.setInt(6, obj.getPerfil());
			pstmt.setString(7, obj.getRg());
			pstmt.setString(8, obj.getSexo());
			pstmt.setInt(9, obj.getIdEstacionamento());
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
	public boolean alterar(FuncionarioBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_FUNCIONARIO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getLogin());					
			pstmt.setString(4, obj.getNome());
			pstmt.setInt(5, obj.getPerfil());
			pstmt.setString(6, obj.getRg());
			pstmt.setString(7, obj.getSexo());
			pstmt.setInt(8, obj.getId());
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

	@Override
	public boolean excluir(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_FUNCIONARIO);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo excluir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;	
	}

	@Override
	public FuncionarioBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		FuncionarioBean funcionarioBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_FUNCIONARIO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				funcionarioBean		=	new FuncionarioBean();
				funcionarioBean.setId(rs.getInt("ID"));
				funcionarioBean.setCpf(rs.getString("CPF"));
				funcionarioBean.setNome(rs.getString("NOME"));
				funcionarioBean.setLogin(rs.getString("LOGIN"));
				funcionarioBean.setEmail(rs.getString("EMAIL"));
				funcionarioBean.setSexo(rs.getString("SEXO"));
				funcionarioBean.setPerfil(rs.getInt("ID_PERFIL"));
				funcionarioBean.setRg(rs.getString("RG"));
				funcionarioBean.setSenha(Util.descriptografa(rs.getString("SENHA")));
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return funcionarioBean;	
	}

	public List<FuncionarioBean> listaTodos(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<FuncionarioBean> listaFuncionarioBean =	null;
		
		try {
			
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_FUNCIONARIOS_POR_ESTACIONAMENTO);
			pstmt.setInt(1, idEstacionamento);
			
			rs		=	pstmt.executeQuery();
			listaFuncionarioBean 		=	new ArrayList<FuncionarioBean>();
			
			while(rs.next()) {
				FuncionarioBean funcionarioBean		=	new FuncionarioBean();
				funcionarioBean.setId(rs.getInt("ID"));
				funcionarioBean.setCpf(rs.getString("CPF"));
				funcionarioBean.setNome(rs.getString("NOME"));
				funcionarioBean.setLogin(rs.getString("LOGIN"));
				funcionarioBean.setEmail(rs.getString("EMAIL"));
				funcionarioBean.setSexo(rs.getString("SEXO"));
				funcionarioBean.setPerfil(rs.getInt("ID_PERFIL"));
				funcionarioBean.setRg(rs.getString("RG"));
				funcionarioBean.setSenha(Util.descriptografa(rs.getString("SENHA")));
				listaFuncionarioBean.add(funcionarioBean);
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaFuncionarioBean;
	}

	@Override
	public List<FuncionarioBean> listaTodos() {
		return null;
	}

	public boolean verificaSeExisteAlgumFuncionarioNoEstacionamento(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		boolean tem 				=	false; 

		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_SE_EXISTE_ALGUM_FUNCIONARIO_NO_ESTACIONAMENTO);
			rs		=	pstmt.executeQuery();
			
			List<Integer> listaIdsEstacionamentosDosFuncionarios = new ArrayList<Integer>();
			
			while(rs.next()) {
				listaIdsEstacionamentosDosFuncionarios.add(new Integer(rs.getInt("ID_ESTACIONAMENTO_FUNC")));
			}
		
			main: if(!listaIdsEstacionamentosDosFuncionarios.isEmpty()) {
				for(int idEstacionamentosDosFuncionarios : listaIdsEstacionamentosDosFuncionarios) {
					if(idEstacionamentosDosFuncionarios == idEstacionamento) {
						tem = true;
						break main;
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo jaExisteFuncionarioNoEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return tem;
	}

	public void atualizarEstacionamentoNoFuncionario(FuncionarioBean funcionarioBean) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_ESTACIONAMENTO_NO_FUNCIONARIO);
			pstmt.setInt(1, funcionarioBean.getIdEstacionamento());
			pstmt.setInt(2, funcionarioBean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo atualizarEstacionamentoNoFuncionario. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, null);
		}
	}

	public int buscarIdFuncionarioPorCPFdoFuncionario(String cpf) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_ID_DO_FUNCIONARIO_POR_CPF_DO_FUNCIONARIO);
			pstmt.setString(1, cpf);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("ID");
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscarIdFuncionarioPorCPFdoFuncionario. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}

		return 0;
	}

	public boolean excluirFuncionarioPorEstacionamento(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_FUNCIONARIO_POR_ID_ESTACIONAMENTO);
			pstmt.setInt(1, idEstacionamento);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo excluirFuncionarioPorEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;
	}

}