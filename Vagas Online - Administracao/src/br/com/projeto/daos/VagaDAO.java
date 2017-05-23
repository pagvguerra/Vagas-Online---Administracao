package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.TipoVagaBean;
import br.com.projeto.beans.VagaBean;
import br.com.projeto.db.DB;
import br.com.projeto.interfaces.GenericDAO;

public class VagaDAO implements GenericDAO<VagaBean> {

	private static final String INSERIR_VAGA 		=	"INSERT INTO VAGA(ID_ESTACIONAMENTO, ID_TIPO_VAGA, LARGURA, ALTURA, CODIGO) VALUES(?, ?, ?, ?, ?)";
	private static final String ATUALIZAR_VAGA	 	=	"UPDATE VAGA SET CODIGO = ?, ID_TIPO_VAGA = ?, LARGURA = ?, ALTURA = ? WHERE ID = ?";
	private static final String EXCLUIR_VAGA 		=	"DELETE FROM VAGA WHERE ID = ?";
	private static final String BUSCA_VAGA_POR_ID 	=	"SELECT ID, CODIGO, ID_ESTACIONAMENTO, ID_TIPO_VAGA, LARGURA, ALTURA FROM VAGA WHERE ID = ?";

	private static final String LISTAR_TODOS_VAGAS_POR_ESTACIONAMENTO 			=	"SELECT ID, CODIGO, ID_ESTACIONAMENTO, ID_TIPO_VAGA, LARGURA, ALTURA  FROM VAGA WHERE ID_ESTACIONAMENTO = ?";
	private static final String BUSCA_SE_EXISTE_ALGUMA_VAGA_NO_ESTACIONAMENTO	=	"SELECT ID_ESTACIONAMENTO FROM VAGA";
	private static final String EXCLUIR_VAGA_POR_ID_ESTACIONAMENTO				=	"DELETE FROM VAGA WHERE ID_ESTACIONAMENTO = ?";
	
	@Override
	public boolean inserir(VagaBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_VAGA);
			pstmt.setInt(1, obj.getIdEstacionamento());
			pstmt.setInt(2, obj.getTipoVagaBean().getId());
			pstmt.setInt(3, obj.getLargura());
			pstmt.setInt(4, obj.getAltura());					
			pstmt.setString(5, obj.getCodigo());
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
	public boolean alterar(VagaBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_VAGA);
			pstmt.setString(1, obj.getCodigo());
			pstmt.setInt(2, obj.getTipoVagaBean().getId());
			pstmt.setInt(3, obj.getLargura());
			pstmt.setInt(4, obj.getAltura());					
			pstmt.setInt(5, obj.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo alterar. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_VAGA);
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
	public VagaBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		VagaBean vagaBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_VAGA_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();

			if(rs.next()) {
				
				TipoVagaBean tipoVagaBean = new TipoVagaBean();
				tipoVagaBean.setId(rs.getInt("ID_TIPO_VAGA"));
				tipoVagaBean.setNome(new TipoVagaDAO().buscarPorId((rs.getInt("ID_TIPO_VAGA"))).getNome());
				
				vagaBean		=	new VagaBean();
				vagaBean.setId(rs.getInt("ID"));
				vagaBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
				vagaBean.setTipoVagaBean(tipoVagaBean);
				vagaBean.setCodigo(rs.getString("CODIGO"));
				vagaBean.setLargura(rs.getInt("LARGURA"));
				vagaBean.setAltura(rs.getInt("ALTURA"));
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return vagaBean;	
	}


	public List<VagaBean> listaTodos(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<VagaBean> listaVagaBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_VAGAS_POR_ESTACIONAMENTO);
			pstmt.setInt(1, idEstacionamento);
			rs		=	pstmt.executeQuery();

			listaVagaBean = new ArrayList<VagaBean>();
			
			while(rs.next()) {
				
				TipoVagaBean tipoVagaBean = new TipoVagaBean();
				tipoVagaBean.setId(rs.getInt("ID_TIPO_VAGA"));
				tipoVagaBean.setNome(new TipoVagaDAO().buscarPorId((rs.getInt("ID_TIPO_VAGA"))).getNome());
				
				VagaBean vagaBean		=	new VagaBean();
				vagaBean.setId(rs.getInt("ID"));
				vagaBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
				vagaBean.setTipoVagaBean(tipoVagaBean);
				vagaBean.setCodigo(rs.getString("CODIGO"));
				vagaBean.setLargura(rs.getInt("LARGURA"));
				vagaBean.setAltura(rs.getInt("ALTURA"));
				listaVagaBean.add(vagaBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaVagaBean;	
	}

	@Override
	public List<VagaBean> listaTodos() {
		return null;
	}

	public boolean verificaSeExisteAlgumaVagaNoEstacionamento(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		boolean tem 				=	false; 

		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_SE_EXISTE_ALGUMA_VAGA_NO_ESTACIONAMENTO);
			rs		=	pstmt.executeQuery();
			
			List<Integer> listaIdsEstacionamentosDasVagas = new ArrayList<Integer>();
			
			while(rs.next()) {
				listaIdsEstacionamentosDasVagas.add(new Integer(rs.getInt("ID_ESTACIONAMENTO")));
			}
		
			main: if(!listaIdsEstacionamentosDasVagas.isEmpty()) {
				for(int idEstacionamentosDasVagas : listaIdsEstacionamentosDasVagas) {
					if(idEstacionamentosDasVagas == idEstacionamento) {
						tem = true;
						break main;
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo verificaSeExisteAlgumaVagaNoEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return tem;
	}

	public boolean excluirVagaPorEstacionamento(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_VAGA_POR_ID_ESTACIONAMENTO);
			pstmt.setInt(1, idEstacionamento);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo excluirVagaPorEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;		
	}

}