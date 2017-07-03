package br.com.projeto.business;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.beans.AdministradorEstacionamentoBean;
import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.daos.AdministradorEstacionamentoDAO;
import br.com.projeto.enums.PerfilEnum;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class AdministradorEstacionamentoBusiness {

	private static AdministradorEstacionamentoBusiness instance = null;
	private String urlRetorno = "";

	public static AdministradorEstacionamentoBusiness getInstance() {
		if ( instance == null ) {
			instance = new AdministradorEstacionamentoBusiness();
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
				inserirAdministradorEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterarAdministradorEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("DESATIVAR")) {
				desativarAdministradorEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR_ADMINISTRADOR_ESTACIONAMENTO")) {
				detalharAdministradorEstacionamento(request, response);
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodos(request, response);
			}
		
		} catch (Exception e) {
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO_BASICO + " " + e.getMessage(), URLs.URL_ERRO_GENERICO);
		}	

		return urlRetorno;
	}
	
	private void listarTodos(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		try{
			HttpSession session = request.getSession();
			UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute("usuario");
			EstacionamentoBusiness.getInstance().listarTodosEstacionamentosPorAdministrador(usuarioBean.getId(), request, response);
			preencheRetorno(request, response, null, URLs.URL_LISTA_ESTACIONAMENTO);
		} catch (Exception e) {
			throw e;
		}
	}

	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		preencheRetorno(request, response, null, URLs.URL_CADASTRAR_ADM_EST);
	}
	
	private void detalharAdministradorEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			AdministradorEstacionamentoBean administradorEstacionamentoBean = new AdministradorEstacionamentoDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("administradorEstacionamentoBean", administradorEstacionamentoBean);
			preencheRetorno(request, response, null, "/paginas/estacionamento/administrador/detalheAdministradorEstacionamento.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void desativarAdministradorEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			new AdministradorEstacionamentoDAO().desativar(Integer.parseInt(request.getParameter("id")));
			preencheRetorno(request, response, "Administrador de Estacionamento desativar com sucesso", URLs.URL_SUCESSO_EXCLUIR_ADM_ESTACIONAMENTO);
		} catch (Exception e) {
			throw e;
		}
	}

	private void alterarAdministradorEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			//Alterando o administrador do estacionamento
			AdministradorEstacionamentoBean administradorEstacionamentoBean = retornaDadosAdministradorEstacionamento(request, true);
			new AdministradorEstacionamentoDAO().alterar(administradorEstacionamentoBean);
			
			//Recriando o usuário da session (que é o administrador do estacionamento que acabou de ser alterado)
			HttpSession session = request.getSession();
			UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute("usuario");

			usuarioBean.setCpf(administradorEstacionamentoBean.getCpf());
			usuarioBean.setEmail(administradorEstacionamentoBean.getEmail());
			usuarioBean.setNome(administradorEstacionamentoBean.getNome());
			usuarioBean.setSexo(administradorEstacionamentoBean.getSexo());
			session.setAttribute("usuario", usuarioBean);

			preencheRetorno(request, response, "Administrador de Estacionamento atualizado com sucesso", URLs.URL_SUCESSO_ALTERAR_ADM_ESTACIONAMENTO);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void inserirAdministradorEstacionamento(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			AdministradorEstacionamentoBean administradorEstacionamentoBean = retornaDadosAdministradorEstacionamento(request, false);
			String senha = administradorEstacionamentoBean.getSenha();
			administradorEstacionamentoBean.setSenha(Util.criptografa(administradorEstacionamentoBean.getSenha()));
			boolean cadastrou = new AdministradorEstacionamentoDAO().inserir(administradorEstacionamentoBean);
			
			if(!cadastrou) {
				throw new Exception("Erro ao inserir o Administrador de Estacionamento " + administradorEstacionamentoBean.getNome());
			}
						
			//Envio de email a administrador
			enviarEmail(administradorEstacionamentoBean.getEmail(), senha);
			preencheRetorno(request, response, "Administrador de Estacionamento inserido com sucesso", URLs.URL_SUCESSO_CADASTRO_ADM_ESTACIONAMENTO);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public AdministradorEstacionamentoBean retornaDadosAdministradorEstacionamento(HttpServletRequest request, boolean pegaId) {
		int id				=	0;
		
		String login		=	"";
		String senha 		=	"";
		
		if(pegaId) {
			id				=	Integer.parseInt(request.getParameter("id"));
		} else {
			login			=	request.getParameter("login").trim();
			senha 			=	request.getParameter("senha").trim();
		}
		
		String cpf			=	request.getParameter("cpf").trim();
		String email		=	request.getParameter("email").trim();
		String nome			=	request.getParameter("nome").trim();
		String rg			=	request.getParameter("rg").trim();
		String sexo 		=	request.getParameter("sexo");
		String resposta1	=	request.getParameter("resposta1").trim();
		String resposta2	=	request.getParameter("resposta2").trim();
		String resposta3	=	request.getParameter("resposta3").trim();
		String resposta4	=	request.getParameter("resposta4").trim();
		
		AdministradorEstacionamentoBean administradorEstacionamentoBean = new AdministradorEstacionamentoBean();
		administradorEstacionamentoBean.setId(id);
		administradorEstacionamentoBean.setPerfil(PerfilEnum.ADMINISTRADOR_ESTACIONAMENTO.getCodigo());
		administradorEstacionamentoBean.setCpf(cpf);
		administradorEstacionamentoBean.setEmail(email);

		if(!login.equals("")) {
			administradorEstacionamentoBean.setLogin(login);
		}
			
		if(!senha.equals("")) {
			administradorEstacionamentoBean.setSenha(senha);
		}
		
		administradorEstacionamentoBean.setNome(nome);
		administradorEstacionamentoBean.setRg(rg);
		administradorEstacionamentoBean.setSexo(sexo);
		administradorEstacionamentoBean.setResposta1(resposta1);
		administradorEstacionamentoBean.setResposta2(resposta2);
		administradorEstacionamentoBean.setResposta3(resposta3);
		administradorEstacionamentoBean.setResposta4(resposta4);

		return administradorEstacionamentoBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem != null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}

	private void enviarEmail(String emailDestinatario, String senha) {
		try {
			String emailServidor = "pagvguerra@gmail.com";
			Util.enviaEmail(emailServidor, emailDestinatario, senha);
		} catch(Exception e) {
			System.out.println("Erro ao enviar email");
		}
	}
}
