package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.TipoPagamentoBean;
import br.com.projeto.db.DB;

public class TipoPagamentoDAO {

	private static final String LISTAR_TODOS_TIPO_PAGAMENTO		=	"SELECT * FROM TIPO_VAGA WHERE ID_ESTACIONAMENTO = ?";
	private static final String BUSCA_TIPO_PAGAMENTO_POR_ID 	=	"SELECT * FROM TIPO_VAGA WHERE ID = ?";

	private static final String INSERIR_TIPO_PAGAMENTO 			=	"INSERT INTO TIPO_VAGA(NOME, PRECO, ID_ESTACIONAMENTO) VALUES(?, ?, ?)";
	private static final String ATUALIZAR_TIPO_PAGAMENTO 		=	"UPDATE TIPO_VAGA SET NOME = ?, PRECO = ? WHERE ID = ?";
	private static final String EXCLUIR_TIPO_PAGAMENTO 			=	"DELETE FROM TIPO_VAGA WHERE ID = ?";


	public TipoPagamentoBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		TipoPagamentoBean tipoPagamentoBean 	=	null;
		
		try {
			conn	=	DB.getMyqslConnection();
//			pstmt	=	conn.prepareStatement(BUSCA_TIPO_VAGA_POR_ID);
//			pstmt.setInt(1, id);
//			rs		=	pstmt.executeQuery();
//
//			if(rs.next()) {
//				tipoPagamentoBean = new TipoPagamentoBean();
//				tipoPagamentoBean.setId(rs.getInt("ID"));
//				tipoPagamentoBean.setNome(rs.getString("NOME"));
//				tipoPagamentoBean.setPreco(rs.getInt("PRECO"));
//				tipoPagamentoBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
//			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return tipoPagamentoBean;	
	}


	public List<TipoPagamentoBean> listaTodos(int idEstacionamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<TipoPagamentoBean> listaTipoPagamentoBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
//			pstmt	=	conn.prepareStatement(LISTAR_TODOS_TIPOS_VAGAS);
//			pstmt.setInt(1, idEstacionamento);
//			rs		=	pstmt.executeQuery();
//
//			listaTipoPagamentoBean = new ArrayList<TipoPagamentoBean>();
//			
//			while(rs.next()) {
//				TipoPagamentoBean tipoPagamentoBean = new TipoPagamentoBean();
//				tipoPagamentoBean.setId(rs.getInt("ID"));
//				tipoPagamentoBean.setNome(rs.getString("NOME"));
//				tipoPagamentoBean.setPreco(rs.getInt("PRECO"));
//				tipoPagamentoBean.setIdEstacionamento(rs.getInt("ID_ESTACIONAMENTO"));
//				listaTipoPagamentoBean.add(tipoPagamentoBean);
//			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaTipoPagamentoBean;	
	}

	public boolean excluir(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
//			pstmt	=	conn.prepareStatement(EXCLUIR_TIPO_VAGA);
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


	public boolean inserir(TipoPagamentoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement("SELECT");
//			pstmt.setString(1, obj.getNome());
//			pstmt.setInt(2, obj.getPreco());
//			pstmt.setInt(3, obj.getIdEstacionamento());
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

	public boolean alterar(TipoPagamentoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
//			pstmt	=	conn.prepareStatement(ATUALIZAR_TIPO_VAGA);
//			pstmt.setString(1, obj.getNome());
//			pstmt.setInt(2, obj.getPreco());
			pstmt.setInt(3, obj.getId());
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



}