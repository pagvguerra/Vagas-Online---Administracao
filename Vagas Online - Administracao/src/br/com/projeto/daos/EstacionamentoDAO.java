package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.StatusBean;
import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.db.DB;

import com.mysql.jdbc.Statement;

public class EstacionamentoDAO {

	private static final String INSERIR_ESTACIONAMENTO						=	"INSERT INTO ESTACIONAMENTO(ID_STATUS, ID_ENDERECO, ID_ADM_ESTACIONAMENTO, RAZAO_SOCIAL, NOME_FANTASIA, CNPJ, INSCRICAO_MUNICIPAL, INSCRICAO_ESTADUAL, AVALIACAO, QTD_AVALIACAO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
	private static final String ATUALIZAR_ESTACIONAMENTO_COM_ADMINISTRADOR	=	"UPDATE ESTACIONAMENTO SET ID_ADM_ESTACIONAMENTO = ? WHERE ID = ?";
	private static final String LISTAR_TODOS_ESTACIONAMENTOS_POR_ADM		=	"SELECT * FROM ESTACIONAMENTO WHERE ID_ADM_ESTACIONAMENTO = ?";
	private static final String LISTAR_QTD_VAGAS_POR_ESTACIONAMENTO 		=	"SELECT COUNT(*) AS QTD_VAGA FROM VAGA WHERE ID_ESTACIONAMENTO = ?";
	private static final String LISTAR_QTD_FUNCIONARIOS_POR_ESTACIONAMENTO 	=	"SELECT COUNT(*) AS QTD_FUNCIONARIO FROM USUARIO WHERE ID_ESTACIONAMENTO_FUNC = ?";
	private static final String ATUALIZAR_STATUS_ESTACIONAMENTO				=	"UPDATE ESTACIONAMENTO SET ID_STATUS = ? WHERE ID = ?";
	private static final String BUSCA_ESTACIONAMENTO_POR_ID					=	"SELECT * FROM ESTACIONAMENTO WHERE ID = ?";
	private static final String EXCLUIR_ESTACIONAMENTO						=	"DELETE FROM ESTACIONAMENTO WHERE ID = ?";
	private static final String ATUALIZAR_ESTACIONAMENTO 					=	"UPDATE ESTACIONAMENTO SET NOME_FANTASIA = ?, RAZAO_SOCIAL = ?, CNPJ = ?, INSCRICAO_MUNICIPAL = ?, INSCRICAO_ESTADUAL = ? WHERE ID = ?";
		
	public int inserir(EstacionamentoBean obj) {
		
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		int idEstacionamento		=	0;
		
		try {
			
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_ESTACIONAMENTO, Statement.RETURN_GENERATED_KEYS);

			//Cadastro o Estacionamento em Modo Inicial - Estado pendente (sem funcionario, sem vaga e sem administrador)
			pstmt.setInt(1, 0);
			pstmt.setInt(2, obj.getEnderecoBean().getId());
			pstmt.setInt(3, 0);
			pstmt.setString(4, obj.getRazaoSocial());
			pstmt.setString(5, obj.getNomeFantasia());
			pstmt.setString(6, obj.getCnpj());
			pstmt.setString(7, obj.getInscricaoMunicipal());
			pstmt.setString(8, obj.getInscricaoEstadual());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
			    idEstacionamento = rs.getInt(1);
			}
				
		} catch (Exception e) {
			System.out.println("Erro no metodo inserir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return 0;
		} finally {
			DB.close(conn, pstmt, null);
		}
		
		return idEstacionamento;
	}


	public boolean alterar(EstacionamentoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_ESTACIONAMENTO);
			pstmt.setString(1, obj.getNomeFantasia());
			pstmt.setString(2, obj.getRazaoSocial());
			pstmt.setString(3, obj.getCnpj());					
			pstmt.setString(4, obj.getInscricaoMunicipal());
			pstmt.setString(5, obj.getInscricaoEstadual());
			pstmt.setInt(6, obj.getId());
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


	public boolean excluir(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_ESTACIONAMENTO);
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


	public EstacionamentoBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		EstacionamentoBean estacionamentoBean =	null;

		try {
		
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_ESTACIONAMENTO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				estacionamentoBean		=	new EstacionamentoBean();
				estacionamentoBean.setId(rs.getInt("ID"));
				estacionamentoBean.setNomeFantasia(rs.getString("NOME_FANTASIA"));
				estacionamentoBean.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
				estacionamentoBean.setCnpj(rs.getString("CNPJ"));
				estacionamentoBean.setInscricaoMunicipal(rs.getString("INSCRICAO_MUNICIPAL"));
				estacionamentoBean.setInscricaoEstadual(rs.getString("INSCRICAO_ESTADUAL"));
				
				StatusBean statusBean = new StatusBean();
				statusBean.setId(rs.getInt("ID_STATUS"));
				estacionamentoBean.setStatusBean(statusBean);
				estacionamentoBean.setEnderecoBean(EnderecoDAO.buscaEnderecoPorEstacionamento(rs.getInt("ID_ENDERECO")));
			}

		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return estacionamentoBean;	
	}
	

	public List<EstacionamentoBean> listaTodos(UsuarioBean administrador) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<EstacionamentoBean> listaEstacionamentoBean =	null;

		PreparedStatement pstmtF	=	null;
		ResultSet rsF				=	null;
		
		PreparedStatement pstmtV	=	null;
		ResultSet rsV				=	null;
		
		
		try {

			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_ESTACIONAMENTOS_POR_ADM);
			pstmt.setInt(1, administrador.getId());
			rs		=	pstmt.executeQuery();
			listaEstacionamentoBean 		=	new ArrayList<EstacionamentoBean>();

			while(rs.next()) {
				EstacionamentoBean estacionamentoBean		=	new EstacionamentoBean();
				estacionamentoBean.setId(rs.getInt("ID"));
				estacionamentoBean.setNomeFantasia(rs.getString("NOME_FANTASIA"));
				estacionamentoBean.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
				estacionamentoBean.setCnpj(rs.getString("CNPJ"));
				estacionamentoBean.setInscricaoMunicipal(rs.getString("INSCRICAO_MUNICIPAL"));
				estacionamentoBean.setInscricaoEstadual(rs.getString("INSCRICAO_ESTADUAL"));
				
				StatusBean statusBean = new StatusBean();
				statusBean.setId(rs.getInt("ID_STATUS"));
				estacionamentoBean.setStatusBean(statusBean);
				estacionamentoBean.setEnderecoBean(EnderecoDAO.buscaEnderecoPorEstacionamento(rs.getInt("ID_ENDERECO")));

				//PEGANDO QTD DE FUNCIONARIOS POR ESTACIONAMENTO
				pstmtF	=	conn.prepareStatement(LISTAR_QTD_FUNCIONARIOS_POR_ESTACIONAMENTO);
				pstmtF.setInt(1, estacionamentoBean.getId());
				rsF		=	pstmtF.executeQuery();
				
				if(rsF.next()) {
					estacionamentoBean.setQuantidadeFuncionarios(rsF.getInt(1));
				} else {
					estacionamentoBean.setQuantidadeFuncionarios(0);
				}
				
				rsF.close();
				pstmtF.close();
				
				//PEGANDO QTD DE VAGAS POR ESTACIONAMENTO
				pstmtV	=	conn.prepareStatement(LISTAR_QTD_VAGAS_POR_ESTACIONAMENTO);
				pstmtV.setInt(1, estacionamentoBean.getId());
				rsV		=	pstmtV.executeQuery();
				
				if(rsV.next()) {
					estacionamentoBean.setQuantidadeVagas(rsV.getInt(1));
				} else {
					estacionamentoBean.setQuantidadeVagas(0);
				}
				
				rsV.close();
				pstmtV.close();
				
				listaEstacionamentoBean.add(estacionamentoBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaEstacionamentoBean;
	}

	public boolean atualizarAdministradorDoEstacionamentoNoNovoEstacionamentoCriado(EstacionamentoBean estacionamentoBean, int idUsuario) {

		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
	
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_ESTACIONAMENTO_COM_ADMINISTRADOR);
			pstmt.setInt(1, idUsuario);
			pstmt.setInt(2, estacionamentoBean.getId());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("Erro no metodo atualizarAdministradorDoEstacionamentoNoNovoEstacionamentoCriado. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
	}


	public void atualizarStatusEstacionamento(int codigo, int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
	
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_STATUS_ESTACIONAMENTO);
			pstmt.setInt(1, codigo);
			pstmt.setInt(2, idEstacionamento);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo atualizarStatusEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, null);
		}
		
	}

}