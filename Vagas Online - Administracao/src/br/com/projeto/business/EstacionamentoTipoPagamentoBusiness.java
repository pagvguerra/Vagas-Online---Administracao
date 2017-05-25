package br.com.projeto.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.EstacionamentoTipoPagamentoBean;
import br.com.projeto.beans.TipoPagamentoBean;
import br.com.projeto.daos.EstacionamentoTipoPagamentoDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class EstacionamentoTipoPagamentoBusiness {

	private static EstacionamentoTipoPagamentoBusiness instance = null;
	private String urlRetorno = "";

	public static EstacionamentoTipoPagamentoBusiness getInstance() {
		if ( instance == null ) {
			instance = new EstacionamentoTipoPagamentoBusiness();
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
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluir(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodos(request, response);
				preencheRetorno(request, response, null, "/paginas/estacionamento/estacionamento_tipo_pagamento/listarEstacionamentoTipoPagamento.jsp");
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
		
		List<EstacionamentoTipoPagamentoBean> listaEstacionamentoTipoPagamento = new EstacionamentoTipoPagamentoDAO().listaTiposPagamentosPorEstacionamento(idEstacionamento);
		
		if(listaEstacionamentoTipoPagamento != null && !listaEstacionamentoTipoPagamento.isEmpty()) {
			boolean estacionamentoPossuiVisa					=	false;
			boolean estacionamentoPossuiMaster					=	false;
			boolean estacionamentoPossuiAmex					=	false;
			
			for(EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoBean: listaEstacionamentoTipoPagamento) {
				if(estacionamentoTipoPagamentoBean.getTipoPagamentoBean().getId()==2) {
					estacionamentoPossuiVisa					=	true;
				}
				if(estacionamentoTipoPagamentoBean.getTipoPagamentoBean().getId()==3) {
					estacionamentoPossuiMaster					=	true;
				}
				if(estacionamentoTipoPagamentoBean.getTipoPagamentoBean().getId()==4) {
					estacionamentoPossuiAmex					=	true;
				}
			}
			
			request.setAttribute("estacionamentoPossuiVisa", estacionamentoPossuiVisa);
			request.setAttribute("estacionamentoPossuiMaster", estacionamentoPossuiMaster);
			request.setAttribute("estacionamentoPossuiAmex", estacionamentoPossuiAmex);
		}

		preencheRetorno(request, response, null, "/paginas/estacionamento/estacionamento_tipo_pagamento/cadastrarEstacionamentoTipoPagamento.jsp");
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			
			int idTipoPagamento = Integer.parseInt(request.getParameter("idTipoPagamento"));
			
			new EstacionamentoTipoPagamentoDAO().excluir(idEstacionamento, idTipoPagamento);

			preencheRetorno(request, response, "Tipo Pagamento excluido com sucesso", "/paginas/estacionamento/estacionamento_tipo_pagamento/sucessoExcluirEstacionamentoTipoPagamento.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);

			List<EstacionamentoTipoPagamentoBean> listaEstacionamentoTipoPagamento = new EstacionamentoTipoPagamentoDAO().listaTiposPagamentosPorEstacionamento(idEstacionamento);
			request.setAttribute("listaEstacionamentoTipoPagamento", listaEstacionamentoTipoPagamento);
			
			boolean estacionamentoPossuiTodosOsTiposDePagamento	=	false;
			if(listaEstacionamentoTipoPagamento.size() == 4) {
				estacionamentoPossuiTodosOsTiposDePagamento = true;
			}
			request.setAttribute("estacionamentoPossuiTodosOsTiposDePagamento", estacionamentoPossuiTodosOsTiposDePagamento);

		} catch (Exception e) {
			throw e;
		}		
	}
	
	private void inserir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<EstacionamentoTipoPagamentoBean> listaEstacionamentoTipoPagamentoBean 	=	new ArrayList<EstacionamentoTipoPagamentoBean>();
		
		EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoDinheiroBean		=	new EstacionamentoTipoPagamentoBean();
		int	idTipoPagamentoDinheiro													=	1;
		
		EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoVisaBean			=	new EstacionamentoTipoPagamentoBean();
		int	idTipoPagamentoVisa														=	0;

		EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoMasterBean		=	new EstacionamentoTipoPagamentoBean();
		int	idTipoPagamentoMaster													=	0;

		EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoAmexBean			=	new EstacionamentoTipoPagamentoBean();
		int	idTipoPagamentoAmex														=	0;

		
		try {
			
			int idEstacionamento													=	Integer.parseInt(request.getParameter("idEstacionamento"));
			EstacionamentoBean estacionamentoBean									=	new EstacionamentoBean();
			estacionamentoBean.setId(idEstacionamento);

			//Cadastrando Dinheiro
			TipoPagamentoBean tipoPagamentoDinheiroBean								=	new TipoPagamentoBean();
			tipoPagamentoDinheiroBean.setId(idTipoPagamentoDinheiro);
			estacionamentoTipoPagamentoDinheiroBean.setTipoPagamentoBean(tipoPagamentoDinheiroBean);
			estacionamentoTipoPagamentoDinheiroBean.setEstacionamentoBean(estacionamentoBean);
			
			//Cadastrando Visa
			try {
				idTipoPagamentoVisa 												=	Integer.parseInt(request.getParameter("idTipoPagamentoVisa"));
				TipoPagamentoBean tipoPagamentoVisaBean								=	new TipoPagamentoBean();
				tipoPagamentoVisaBean.setId(idTipoPagamentoVisa);
				estacionamentoTipoPagamentoVisaBean.setTipoPagamentoBean(tipoPagamentoVisaBean);
				estacionamentoTipoPagamentoVisaBean.setEstacionamentoBean(estacionamentoBean);
			} catch (Exception e) {}	
			
			
			//Cadastrando Master
			try {
				idTipoPagamentoMaster												=	Integer.parseInt(request.getParameter("idTipoPagamentoMaster"));
				TipoPagamentoBean tipoPagamentoMasterBean							=	new TipoPagamentoBean();
				tipoPagamentoMasterBean.setId(idTipoPagamentoMaster);
				estacionamentoTipoPagamentoMasterBean.setTipoPagamentoBean(tipoPagamentoMasterBean);
				estacionamentoTipoPagamentoMasterBean.setEstacionamentoBean(estacionamentoBean);
			} catch(Exception e) {}

			
			//Cadastrando Amex
			try {
				idTipoPagamentoAmex													=	Integer.parseInt(request.getParameter("idTipoPagamentoAmex"));
				TipoPagamentoBean tipoPagamentoAmexBean								=	new TipoPagamentoBean();
				tipoPagamentoAmexBean.setId(idTipoPagamentoAmex);
				estacionamentoTipoPagamentoAmexBean.setTipoPagamentoBean(tipoPagamentoAmexBean);
				estacionamentoTipoPagamentoAmexBean.setEstacionamentoBean(estacionamentoBean);
			} catch(Exception e) {}
			

			listaEstacionamentoTipoPagamentoBean.add(estacionamentoTipoPagamentoDinheiroBean);

			if(idTipoPagamentoVisa != 0) {
				listaEstacionamentoTipoPagamentoBean.add(estacionamentoTipoPagamentoVisaBean);
			}

			if(idTipoPagamentoMaster != 0) {
				listaEstacionamentoTipoPagamentoBean.add(estacionamentoTipoPagamentoMasterBean);
			}

			if(idTipoPagamentoAmex != 0) {
				listaEstacionamentoTipoPagamentoBean.add(estacionamentoTipoPagamentoAmexBean);
			}
			
			boolean cadastrou												=	new EstacionamentoTipoPagamentoDAO().inserir(listaEstacionamentoTipoPagamentoBean);
			
			if(!cadastrou) {
				throw new Exception("Erro ao inserir o(s) tipo(s) de pagamento(s) para este estacionamento");
			}
			
			request.setAttribute("idEstacionamento", idEstacionamento);
			preencheRetorno(request, response, "Tipo de Pagamento inserido com sucesso", "/paginas/estacionamento/estacionamento_tipo_pagamento/sucessoCadastrarEstacionamentoTipoPagamento.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}