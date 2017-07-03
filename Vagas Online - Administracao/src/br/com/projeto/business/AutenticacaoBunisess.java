package br.com.projeto.business;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.beans.AutenticacaoBean;
import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.daos.AutenticacaoDAO;
import br.com.projeto.daos.UsuarioDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class AutenticacaoBunisess {

	private static final String CAMPO_LOGIN = "LOGIN";
	private static final String CAMPO_SENHA = "SENHA";
	
	private static AutenticacaoBunisess instance = null;
	private String urlRetorno = "";

	public static AutenticacaoBunisess getInstance() {
		if ( instance == null ) {
			instance = new AutenticacaoBunisess();
		}
		return instance;
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try { 

			String acao = request.getParameter("acao");
				
			if(Util.isEmpty(acao) || acao.equalsIgnoreCase("LOGIN")) {
				login(request, response);
			} else if(acao.equalsIgnoreCase("LOGOUT")) {
				logout(request, response);
			} else if(acao.equalsIgnoreCase("ENTRADA")) {
				preencheRetorno(request, response, "", URLs.URL_LOGIN);	
			} else if(acao.equalsIgnoreCase("FORM_REDEFINIR_SENHA")) {
				preencheRetorno(request, response, null, URLs.URL_REDEFINIR_SENHA);	
			} else if(acao.equalsIgnoreCase("REDEFINIR_SENHA")) {
				redefinirSenha(request, response, null, URLs.URL_REDEFINIR_SENHA);	
			}
			
		} catch (Exception e) {
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO_BASICO + " " + e.getMessage(), URLs.URL_ERRO_GENERICO);
		}	
		
		return urlRetorno;
	}
	
	private void redefinirSenha(HttpServletRequest request, HttpServletResponse response, Object object, String redefinirSenha) throws Exception {
		UsuarioBean usuarioBean =	null;

		try {

			usuarioBean = new UsuarioBean();
			usuarioBean.setLogin(request.getParameter(CAMPO_LOGIN).trim());
			
			usuarioBean = new UsuarioDAO().buscarPorLogin(usuarioBean.getLogin());
			
			if(usuarioBean == null) {
				preencheRetorno(request, response, Mensagens.USUARIO_NAO_ENCONTRADO, URLs.URL_ERRO_REDEFINIR_SENHA);
				return;
			}
			
			usuarioBean.setLogin(request.getParameter(CAMPO_LOGIN).trim());
			usuarioBean.setResposta1(request.getParameter("resposta1").trim());
			usuarioBean.setResposta2(request.getParameter("resposta2").trim());
			usuarioBean.setResposta3(request.getParameter("resposta3").trim());
			usuarioBean.setResposta4(request.getParameter("resposta4").trim());
			
			boolean validou = new UsuarioDAO().validaRespostasUsuario(usuarioBean);
			
			if(!validou) {
				preencheRetorno(request, response, Mensagens.USUARIO_NAO_ENCONTRADO, URLs.URL_ERRO_REDEFINIR_SENHA);
				return;
			}
			
			String senha = request.getParameter(CAMPO_SENHA).trim();
			usuarioBean.setSenha(Util.criptografa(senha));
			boolean alterouSenha = new AutenticacaoDAO().redefineSenhaPorLogin(usuarioBean);
			
			if(alterouSenha) {
				//Envio de email a administrador na redefinição de senha
				enviarEmail(usuarioBean.getEmail(), senha);
				preencheRetorno(request, response, null, URLs.URL_REPOSTA_REDEFINIR_SENHA);
			} else {
				preencheRetorno(request, response, Mensagens.USUARIO_NAO_ENCONTRADO, URLs.URL_ERRO_REDEFINIR_SENHA);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AutenticacaoBean autenticacaoBean =	null;

		try {
			
			String login = request.getParameter(CAMPO_LOGIN);
			String senha = request.getParameter(CAMPO_SENHA);
			
			autenticacaoBean = new AutenticacaoBean();
			autenticacaoBean.setLogin(login.trim());
			autenticacaoBean.setSenha(Util.criptografa(senha.trim()));
			
			UsuarioBean usuarioBean = new AutenticacaoDAO().existeUsuarioPorLoginESenhaInformados(autenticacaoBean);
			
			if(usuarioBean!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usuarioBean);
				
				EstacionamentoBusiness.getInstance().listarTodosEstacionamentosPorAdministrador(usuarioBean.getId(), request, response);
				preencheRetorno(request, response, null, URLs.URL_LISTA_ESTACIONAMENTO);
			} else {
				preencheRetorno(request, response, Mensagens.USUARIO_NAO_ENCONTRADO, URLs.URL_ERRO_VALIDACAO);
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().setAttribute("usuario", null);
		request.getSession().invalidate();
		preencheRetorno(request, response, null, URLs.URL_LOGOUT);
	}
	
	public boolean validar(HttpServletRequest request, HttpServletResponse response, String campo, String msgCampoVazio, String msgTamanhoMinimo, int tam) throws IOException {
		if(Util.isEmpty(campo)) {
			preencheRetorno(request, response, msgCampoVazio, URLs.URL_ERRO_VALIDACAO);
			return false;
		}
		if(campo.length() >= tam) {
			preencheRetorno(request, response, msgTamanhoMinimo, URLs.URL_ERRO_VALIDACAO);
			return false;
		}
		return true;
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