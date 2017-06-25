package br.com.projeto.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.projeto.beans.BairroBean;
import br.com.projeto.beans.CidadeBean;
import br.com.projeto.beans.EnderecoBean;
import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.EstacionamentoTipoPagamentoBean;
import br.com.projeto.beans.EstadoBean;
import br.com.projeto.beans.PaisBean;
import br.com.projeto.beans.TipoLogradouroBean;
import br.com.projeto.beans.TipoPagamentoBean;
import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.daos.BairroDAO;
import br.com.projeto.daos.CidadeDAO;
import br.com.projeto.daos.EnderecoDAO;
import br.com.projeto.daos.EstacionamentoDAO;
import br.com.projeto.daos.EstacionamentoTipoPagamentoDAO;
import br.com.projeto.daos.EstadoDAO;
import br.com.projeto.daos.FuncionarioDAO;
import br.com.projeto.daos.PaisDAO;
import br.com.projeto.daos.TipoLogradouroDAO;
import br.com.projeto.daos.VagaDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class EstacionamentoBusiness {
	
	private static EstacionamentoBusiness instance = null;
	private String urlRetorno = "";

	public static EstacionamentoBusiness getInstance() {
		if ( instance == null ) {
			instance = new EstacionamentoBusiness();
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
				inserirEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterarEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluirEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR_ESTACIONAMENTO")) {
				detalharEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("BUSCAR_ENDERECO_ESTACIONAMENTO")) {
				buscarEnderecoEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				HttpSession session = request.getSession();
				UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute("usuario");
				listarTodosEstacionamentosPorAdministrador(usuarioBean.getId(),request, response);
				preencheRetorno(request, response, null, URLs.URL_ERRO_VALIDACAO_USU);
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			}else if(acao.equalsIgnoreCase("BUSCA_ESTADOS")) {	
				buscaEstadosPorPais(request, response);
			}
		
		} catch (Exception e) {
			System.out.println("Erro. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}	

		return urlRetorno;
	}
	
	private void buscarEnderecoEstacionamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			EstacionamentoBean estacionamentoBean = new EstacionamentoDAO().buscarPorId(idEstacionamento);
			request.setAttribute("estacionamentoBean", estacionamentoBean);
			preencheRetorno(request, response, null, URLs.URL_DETALHE_ENDERECO_ESTACIONAMENTO);
		} catch (Exception e) {
			throw e;
		}
	}

	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<PaisBean> listaPais 		=	new PaisDAO().listaTodos();
		List<EstadoBean> listaEstado	=	new EstadoDAO().listaTodos();
		List<CidadeBean> listaCidade	=	new CidadeDAO().listaTodos();
		List<BairroBean> listaBairro	=	new BairroDAO().listaTodos();
		List<TipoLogradouroBean> listaTipoLogradouroBean = new TipoLogradouroDAO().listaTodos();

		request.setAttribute("listaPais", listaPais);
		request.setAttribute("listaEstado", listaEstado);
		request.setAttribute("listaCidade", listaCidade);
		request.setAttribute("listaBairro", listaBairro);
		request.setAttribute("listaTipoLogradouro", listaTipoLogradouroBean);
				
		preencheRetorno(request, response, null, URLs.URL_CADASTRAR_ESTACIONAMENTO);
	}
	
	private void detalharEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<PaisBean> listaPais 		=	new PaisDAO().listaTodos();
			List<EstadoBean> listaEstado	=	new EstadoDAO().listaTodos();
			List<CidadeBean> listaCidade	=	new CidadeDAO().listaTodos();
			List<BairroBean> listaBairro	=	new BairroDAO().listaTodos();
			List<TipoLogradouroBean> listaTipoLogradouroBean = new TipoLogradouroDAO().listaTodos();
			EstacionamentoBean estacionamentoBean = new EstacionamentoDAO().buscarPorId(Integer.parseInt(request.getParameter("idEstacionamento")));

			request.setAttribute("listaPais", listaPais);
			request.setAttribute("listaEstado", listaEstado);
			request.setAttribute("listaCidade", listaCidade);
			request.setAttribute("listaBairro", listaBairro);
			request.setAttribute("listaTipoLogradouro", listaTipoLogradouroBean);
			request.setAttribute("estacionamentoBean", estacionamentoBean);
			
			preencheRetorno(request, response, null, "/paginas/estacionamento/detalheEstacionamento.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluirEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			
			//Exclui Funcionário
			boolean excluiuFuncionario = new FuncionarioDAO().excluirFuncionarioPorEstacionamento(idEstacionamento);
			
			if(!excluiuFuncionario) {
				throw new Exception("Erro ao excluir os funcionarios do estacionamento");
			}
			
			//Exclui Vaga
			boolean excluiuVaga = new VagaDAO().excluirVagaPorEstacionamento(idEstacionamento);

			if(!excluiuVaga) {
				throw new Exception("Erro ao excluir as vagas do estacionamento");
			}

			//Exclui estacionamento
			boolean excluiuEstacionamento = new EstacionamentoDAO().excluir(idEstacionamento);

			if(!excluiuEstacionamento) {
				throw new Exception("Erro ao excluir o estacionamento");
			}
		
			//Chamando a página de listagem
			HttpSession session = request.getSession();
			UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute("usuario");
			listarTodosEstacionamentosPorAdministrador(usuarioBean.getId(),request, response);
			
			preencheRetorno(request, response, null, URLs.URL_LISTA_ESTACIONAMENTO);
		
		} catch (Exception e) {
			throw e;
		}
	}

	public void listarTodosEstacionamentosPorAdministrador(int idAdministrador, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			UsuarioBean administrador = new UsuarioBean();
			administrador.setId(idAdministrador);
			List<EstacionamentoBean> listaEstacionamento = new EstacionamentoDAO().listaTodos(administrador);
			request.setAttribute("listaEstacionamento", listaEstacionamento);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void alterarEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Alterar o endereco
		EnderecoBean enderecoBean = EnderecoBusiness.getInstance().retornaDadosEndereco(request, true);
		boolean alterouEndereco = new EnderecoDAO().alterar(enderecoBean);
		
		if(!alterouEndereco) {
			throw new Exception("Erro ao alterar o endereço do estacionamento");
		}
		
		//Alterando o estacionamento
		EstacionamentoBean estacionamentoBean = retornaDadosEstacionamento(request, true);
		estacionamentoBean.setEnderecoBean(enderecoBean);
		boolean alterouEstacionamento = new EstacionamentoDAO().alterar(estacionamentoBean);
		
		if(!alterouEstacionamento) {
			throw new Exception("Erro ao alterar o estacionamento");
		}
		
		preencheRetorno(request, response, "Estacionamento atualizado com sucesso", URLs.URL_SUCESSO_ALTERAR_ESTACIONAMENTO);
	}

	private void inserirEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			
			HttpSession session = request.getSession();
			UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute("usuario");
			
			//Cadastrando o endereco
			EnderecoBean enderecoBean = EnderecoBusiness.getInstance().retornaDadosEndereco(request, false);
			enderecoBean.setId(new EnderecoDAO().inserir(enderecoBean));

			if(enderecoBean.getId()==0) {
				throw new Exception("Erro ao cadastrar o endereço do estacionamento");
			}

			//Cadastrando o estacionamento
			EstacionamentoBean estacionamentoBean = retornaDadosEstacionamento(request, false);
			estacionamentoBean.setEnderecoBean(enderecoBean);
			estacionamentoBean.setId(new EstacionamentoDAO().inserir(estacionamentoBean));
			
			if(estacionamentoBean.getId()==0) {
				throw new Exception("Erro ao cadastrar o estacionamento");
			}

			//Colocando o Administrador do sistema logado como propietario do estacionamento
			boolean atualizou = new EstacionamentoDAO().atualizarAdministradorDoEstacionamentoNoNovoEstacionamentoCriado(estacionamentoBean, usuarioBean.getId());
			
			if(!atualizou) {
				throw new Exception("Erro ao adicionar o estacionamento ao administrador do sistema");
			}
			
			//Colocando o tipo de pagamento dinheiro para o estacionamento
			TipoPagamentoBean tipoPagamentoBean = new TipoPagamentoBean();
			tipoPagamentoBean.setId(1);

			EstacionamentoTipoPagamentoBean estacionamentoTipoPagamentoBean = new EstacionamentoTipoPagamentoBean();
			estacionamentoTipoPagamentoBean.setEstacionamentoBean(estacionamentoBean);
			estacionamentoTipoPagamentoBean.setTipoPagamentoBean(tipoPagamentoBean);
			
			List<EstacionamentoTipoPagamentoBean> listaEstacionamentoTipoPagamentoBean = new ArrayList<EstacionamentoTipoPagamentoBean>();
			listaEstacionamentoTipoPagamentoBean.add(estacionamentoTipoPagamentoBean);
			
			boolean incluiTipoPagamento = new EstacionamentoTipoPagamentoDAO().inserir(listaEstacionamentoTipoPagamentoBean);
			
			if(!incluiTipoPagamento) {
				throw new Exception("Erro ao incluir o tipo de pagamento ao cadastrar o estacionamento");
			}
			
			preencheRetorno(request, response, "Estacionamento inserido com sucesso", URLs.URL_SUCESSO_CADASTRO_ESTACIONAMENTO);
					
		} catch (Exception e) {
			throw e;
		}
	}
	
	public EstacionamentoBean retornaDadosEstacionamento(HttpServletRequest request, boolean pegaId) {

		EstacionamentoBean estacionamentoBean = new EstacionamentoBean();
		int id	=	0;
		
		if(pegaId) {
			id			=	Integer.parseInt(request.getParameter("id"));
			estacionamentoBean.setId(id);
		}

		String nomeFantasia = request.getParameter("nomeFantasia").trim();
		estacionamentoBean.setNomeFantasia(nomeFantasia);

		String razaoSocial = request.getParameter("razaoSocial").trim();
		estacionamentoBean.setRazaoSocial(razaoSocial);
		
		String cnpj = request.getParameter("cnpj").trim();
		estacionamentoBean.setCnpj(cnpj);

		String inscricaoMunicipal = request.getParameter("inscricaoMunicipal").trim();
		estacionamentoBean.setInscricaoMunicipal(inscricaoMunicipal);

		String inscricaoEstadual = request.getParameter("inscricaoEstadual").trim();
		estacionamentoBean.setInscricaoEstadual(inscricaoEstadual);
		
		return estacionamentoBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
	private void buscaEstadosPorPais(HttpServletRequest request,HttpServletResponse response) {
		
		String strIdPais 	= request.getParameter("idPais");
		int idPais 			= "".equals(strIdPais) ? 0 : Integer.parseInt(strIdPais);
		
		List<EstadoBean> listaEstado	=	new EstadoDAO().listaEstadosPorPais(idPais );
		
		JSONObject json 	= new JSONObject();
		JSONArray jsonItems = new JSONArray();
		try {
			
			for (EstadoBean e : listaEstado) {
				JSONObject j = new JSONObject();
				j.put("id", e.getId());
				j.put("nome", e.getNome());
				jsonItems.put(j);
			}
			json.put("itens", jsonItems);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				printJSON(response, json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void printJSON(HttpServletResponse response, Object json) throws IOException {
		PrintWriter writer = null;
		try {
			response.setContentType("text/json;charset=ISO-8859-1");
			writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			throw (e);
		} finally {
			if (writer != null) {
				writer.close();
			}
			writer = null;
		}
	}
	
}
