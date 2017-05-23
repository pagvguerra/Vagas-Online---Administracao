package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.TipoPagamentoBean;
import br.com.projeto.daos.TipoPagamentoDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class TipoPagamentoBusiness {

	private static TipoPagamentoBusiness instance = null;
	private String urlRetorno = "";

	public static TipoPagamentoBusiness getInstance() {
		if ( instance == null ) {
			instance = new TipoPagamentoBusiness();
		}
		return instance;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try { 
			
			String acao = request.getParameter("acao");
			if(Util.isEmpty(acao)) {
				System.out.println("Ação nao definida");
				preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
			} else if(acao.equalsIgnoreCase("INSERIR")) {
				inserir(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterar(request, response);
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluir(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR")) {
				detalhar(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodos(request, response);
				preencheRetorno(request, response, null, "/paginas/estacionamento/tipo_pagamento/listarTipoPagamento.jsp");
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			}
		
		} catch (Exception e) {
			System.out.println("Erro. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}	

		return urlRetorno;
	}
	
	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
		request.setAttribute("idEstacionamento", idEstacionamento);
		preencheRetorno(request, response, null, "/paginas/estacionamento/tipo_pagamento/cadastrarTipoPagamento.jsp");
	}
	
	private void detalhar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			
			List<TipoPagamentoBean> listaTipoPagamentoBean = new TipoPagamentoDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaTipoPagamentoBean", listaTipoPagamentoBean);
			
			preencheRetorno(request, response, null, "/paginas/estacionamento/tipo_pagamento/detalheTipoPagamento.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			new TipoPagamentoDAO().excluir(Integer.parseInt(request.getParameter("id")));
		} catch (Exception e) {
			throw e;
		}
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			TipoPagamentoBean tipoPagamentoBean = retornaDados(request, true);
			new TipoPagamentoDAO().alterar(tipoPagamentoBean);
			preencheRetorno(request, response, "Tipo Pagamento atualizado com sucesso", "/paginas/estacionamento/tipo_pagamento/sucessoAlterarTipoPagamento.jsp");
		} catch (Exception e) {
			throw e;
		}		
	}

	private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);

			List<TipoPagamentoBean> listaTipoPagamento = new TipoPagamentoDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaTipoPagamento", listaTipoPagamento);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	private void inserir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			
			TipoPagamentoBean tipoPagamentoBean = retornaDados(request, false);
			boolean cadastrou = new TipoPagamentoDAO().inserir(tipoPagamentoBean);
			
			if(!cadastrou) {
				throw new Exception("Erro ao inserir o tipo de pagamento");
			}
			
			preencheRetorno(request, response, "Tipo de Pagamento inserido com sucesso", "/paginas/estacionamento/tipo_pagamento/sucessoCadastrarTipoPagamento.jsp");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public TipoPagamentoBean retornaDados(HttpServletRequest request, boolean pegaId) {
		int id						=	0;
		
		if(pegaId)
			id						=	Integer.parseInt(request.getParameter("id"));

		int idEstacionamento		=	Integer.parseInt(request.getParameter("idEstacionamento"));
		int tipoPagamentoDinheiro	=	Integer.parseInt(request.getParameter("tipoPagamentoDinheiro"));
		int tipoPagamentoVisa		=	Integer.parseInt(request.getParameter("tipoPagamentoVisa"));
		int tipoPagamentoMaster		=	Integer.parseInt(request.getParameter("tipoPagamentoMaster"));
		int tipoPagamentoAmex		=	Integer.parseInt(request.getParameter("tipoPagamentoAmex"));

//		if()
//		
		TipoPagamentoBean tipoPagamentoBean = new TipoPagamentoBean();
//		tipoPagamentoBean.setId(id);
//		tipoPagamentoBean.setIdEstacionamento(idEstacionamento);
//		tipoPagamentoBean.setTipoPagamentoDinheiro(tipoPagamentoDinheiro);
//		tipoPagamentoBean.setTipoPagamentoVisa(tipoPagamentoVisa);
//		tipoPagamentoBean.setTipoPagamentoMaster(tipoPagamentoMaster);
//		tipoPagamentoBean.setTipoPagamentoAmex(tipoPagamentoAmex);
//		
		return tipoPagamentoBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}