package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.TipoVagaBean;
import br.com.projeto.beans.VagaBean;
import br.com.projeto.daos.TipoVagaDAO;
import br.com.projeto.daos.VagaDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class VagaBusiness {

	private static VagaBusiness instance = null;
	private String urlRetorno = "";

	public static VagaBusiness getInstance() {
		if ( instance == null ) {
			instance = new VagaBusiness();
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
				preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/listarVaga.jsp");
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			}
		
		} catch (Exception e) {
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO_BASICO + " " + e.getMessage(), URLs.URL_ERRO_GENERICO);
		}	

		return urlRetorno;
	}
	
	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//busco para ver se já existem tipo de vagas cadastradas, se não, jogo para tela de cadastramento de tipo de vaga
		int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
		request.setAttribute("idEstacionamento", idEstacionamento);
		boolean existeTipoVagaJaCadastrada = new TipoVagaDAO().existeTipoVagaJaCadastrada(idEstacionamento) ;
		
		if(existeTipoVagaJaCadastrada) {
			List<TipoVagaBean> listaTipoVagaBean = new TipoVagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaTipoVagaBean", listaTipoVagaBean);
			preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/cadastrarVaga.jsp");
		} else {
			request.setAttribute("msg", "Nenhum tipo de vaga foi cadastrado ainda!<br>Primeiro cadastre um tipo de vaga para depois cadastrar uma vaga");
			preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/tipo_vagas/cadastrarTipoVaga.jsp");
		}
		
	}
	
	private void detalhar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			
			List<TipoVagaBean> listaTipoVagaBean = new TipoVagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaTipoVagaBean", listaTipoVagaBean);
			
			VagaBean vagaBean = new VagaDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("vagaBean", vagaBean);
			
			preencheRetorno(request, response, null, "/paginas/estacionamento/vagas/detalheVaga.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//TODO Fazer tratamento status estacionamento aqui
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			new VagaDAO().excluir(Integer.parseInt(request.getParameter("id")));
			
			//Primeiro Busco se é a ultima vaga do estacionamento, se for então altero o status do estacionamento para a fase anterior
			boolean existeVagaNoEstacionamento = new VagaDAO().verificaSeExisteAlgumaVagaNoEstacionamento(idEstacionamento);
			
			if(!existeVagaNoEstacionamento) {
				EstacionamentoBean.alteraStatusEstacionamentoParaOStatusAnterior(idEstacionamento);
			}
			
			//Listar Vagas
			List<VagaBean> listaVagas = new VagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaVagas", listaVagas);
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			VagaBean vagaBean = retornaDados(request, true);
			new VagaDAO().alterar(vagaBean);
			preencheRetorno(request, response, "Vaga atualizada com sucesso", "/paginas/estacionamento/vagas/sucessoAlterarVaga.jsp");
		} catch (Exception e) {
			throw e;
		}		
	}

	private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			List<VagaBean> listaVagas = new VagaDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaVagas", listaVagas);
			request.setAttribute("idEstacionamento", idEstacionamento);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	private void inserir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));

			//Primeiro Busco se já existe pelo menos uma vaga no estacionamento, somente se não existir então altero o status do estacionamento para a próxima fase
			boolean jaExisteVagaNoEstacionamento = new VagaDAO().verificaSeExisteAlgumaVagaNoEstacionamento(idEstacionamento);

			if(!jaExisteVagaNoEstacionamento) {
				EstacionamentoBean.alteraStatusEstacionamentoParaOStatusSeguinte(idEstacionamento);
			}

			VagaBean vagaBean = retornaDados(request, false);
			vagaBean.setIdEstacionamento(idEstacionamento);
			boolean cadastrou = new VagaDAO().inserir(vagaBean);
			
			if(!cadastrou) {
				throw new Exception("Erro ao inserir a vaga");
			}
			
			request.setAttribute("idEstacionamento", idEstacionamento);
			preencheRetorno(request, response, "Vaga inserida com sucesso", "/paginas/estacionamento/vagas/sucessoIncluirVaga.jsp");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public VagaBean retornaDados(HttpServletRequest request, boolean pegaId) {
		int id	=	0;
		
		if(pegaId)
			id			=	Integer.parseInt(request.getParameter("id"));
		
		String codigo	=	request.getParameter("codigo").trim();
		int tipoVaga	=	Integer.parseInt(request.getParameter("tipoVaga"));
		int largura		=	Integer.parseInt(request.getParameter("largura").trim());
		int altura		=	Integer.parseInt(request.getParameter("altura").trim());
		int comprimento	=	Integer.parseInt(request.getParameter("comprimento").trim());
		
		TipoVagaBean tipoVagaBean = new TipoVagaBean();
		tipoVagaBean.setId(tipoVaga);
	
		VagaBean vagaBean = new VagaBean();
		vagaBean.setId(id);
		vagaBean.setCodigo(codigo);
		vagaBean.setTipoVagaBean(tipoVagaBean);
		vagaBean.setLargura(largura);
		vagaBean.setAltura(altura);
		vagaBean.setComprimento(comprimento);
		
		return vagaBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}