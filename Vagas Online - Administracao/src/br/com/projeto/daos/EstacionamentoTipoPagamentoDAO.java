package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.EstacionamentoTipoPagamentoBean;
import br.com.projeto.beans.TipoPagamentoBean;
import br.com.projeto.db.DB;

public class EstacionamentoTipoPagamentoDAO {

	private static final String LISTAR_TIPOS_PAGAMENTO_POR_ESTACIONAMENTO	=	"SELECT e.id as id_estacionamento, e.nome_fantasia as nome_fantasia, tp.id as id_tipo_pagamento, tp.nome as nome_tipo_pagamento FROM estacionamento e, estacionamento_tp_pagamento etp, tipo_pagamento tp WHERE etp.id_estacionamento = e.id AND etp.id_tipo_pagamento = tp.id AND e.id = ?;";
	private static final String EXCLUIR										=	"DELETE FROM estacionamento_tp_pagamento WHERE id_estacionamento = ? AND id_tipo_pagamento = ?";
	private static final String EXCLUIR_TODA_A_LISTA_DE_TIPOS_DE_PAGAMENTOS	=	"DELETE FROM estacionamento_tp_pagamento WHERE id_estacionamento = ?";
	private static final String INSERIR										=	"INSERT INTO estacionamento_tp_pagamento(id_estacionamento, id_tipo_pagamento) VALUES (?, ?)";
	
	public List<EstacionamentoTipoPagamentoBean> listaTiposPagamentosPorEstacionamento(int idEstacionamento) {
		Connection conn																=	null;
		PreparedStatement pstmt														=	null;
		ResultSet rs																=	null;
		List<EstacionamentoTipoPagamentoBean> listaEstacionamentoTipoPagamentoBean 	=	null;
		
		try {
			
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TIPOS_PAGAMENTO_POR_ESTACIONAMENTO);
			pstmt.setInt(1, idEstacionamento);
			rs		=	pstmt.executeQuery();

			listaEstacionamentoTipoPagamentoBean									=	new ArrayList<EstacionamentoTipoPagamentoBean>();
			
			while(rs.next()) {
				
				EstacionamentoBean estacionamentoBean 								=	new EstacionamentoBean();
				estacionamentoBean.setId(rs.getInt("ID_ESTACIONAMENTO"));
				estacionamentoBean.setNomeFantasia(rs.getString("NOME_FANTASIA"));
				
				TipoPagamentoBean tipoPagamentoBean									=	new TipoPagamentoBean();
				tipoPagamentoBean.setId(rs.getInt("ID_TIPO_PAGAMENTO"));
				tipoPagamentoBean.setNome(rs.getString("NOME_TIPO_PAGAMENTO"));
				
				EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoBean 	=	new EstacionamentoTipoPagamentoBean();
				estacionamentoTipoPagamentoBean.setEstacionamentoBean(estacionamentoBean);
				estacionamentoTipoPagamentoBean.setTipoPagamentoBean(tipoPagamentoBean);
				
				listaEstacionamentoTipoPagamentoBean.add(estacionamentoTipoPagamentoBean);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTiposPagamentosPorEstacionamento. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return listaEstacionamentoTipoPagamentoBean;	
	}
	
	public boolean excluir(int idEstacionamento, int idTipoPagamento) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR);
			pstmt.setInt(1, idEstacionamento);
			pstmt.setInt(2, idTipoPagamento);
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

	public boolean inserir(List<EstacionamentoTipoPagamentoBean> listaEstacionamentoTipoPagamentoBean) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();

			//PRIMEIRO APAGO A TABELA FULL 
			pstmt	=	conn.prepareStatement(EXCLUIR_TODA_A_LISTA_DE_TIPOS_DE_PAGAMENTOS);
			pstmt.setInt(1, listaEstacionamentoTipoPagamentoBean.get(0).getEstacionamentoBean().getId());
			pstmt.executeUpdate();
			pstmt.close();
			
			//DEPOIS CADASTRO A LISTA DE TIPOS DE PAGAMENTOS
			for(EstacionamentoTipoPagamentoBean obj: listaEstacionamentoTipoPagamentoBean){
				pstmt	=	conn.prepareStatement(INSERIR);
				pstmt.setInt(1, obj.getEstacionamentoBean().getId());
				pstmt.setInt(2, obj.getTipoPagamentoBean().getId());
				pstmt.executeUpdate();
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo inserir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;
	}

}