package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.UsuarioBean;
import br.com.projeto.daos.UsuarioDAO;
import br.com.projeto.enums.PerfilEnum;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class UsuarioBusiness {
	
	private static UsuarioBusiness instance = null;
	private String urlRetorno = "";

	public static UsuarioBusiness getInstance() {
		if ( instance == null ) {
			instance = new UsuarioBusiness();
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
				inserirUsuario(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterarUsuario(request, response);
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluirUsuario(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR_EMPREGADO")) {
				detalharUsuario(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodosUsuarios(request, response);
				preencheRetorno(request, response, null, URLs.URL_ERRO_VALIDACAO_USU);
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
//		preencheRetorno(request, response, null, URLs.URL_CADASTRAR_USU);
	}
	
	private void detalharUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			UsuarioBean usuarioBean = new UsuarioDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("usuarioBean", usuarioBean);
			preencheRetorno(request, response, null, "/paginas/empregados/detalheUsuario.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluirUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			new UsuarioDAO().excluir(Integer.parseInt(request.getParameter("id")));
			preencheRetorno(request, response, "Usuario excluido com sucesso", URLs.URL_ERRO_VALIDACAO_USU);
			listarTodosUsuarios(request, response);		
		} catch (Exception e) {
			throw e;
		}
	}

	private void listarTodosUsuarios(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<UsuarioBean> listaUsuarios = new UsuarioDAO().listaTodos();
			request.setAttribute("listaUsuarios", listaUsuarios);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void alterarUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			UsuarioBean usuarioBean = retornaDadosUsuario(request, true);
			usuarioBean.setSenha(Util.criptografa(usuarioBean.getSenha()));
			new UsuarioDAO().alterar(usuarioBean);
			preencheRetorno(request, response, "Usuario atualizado com sucesso", URLs.URL_ERRO_VALIDACAO_USU);
			listarTodosUsuarios(request, response);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			UsuarioBean usuarioBean = retornaDadosUsuario(request, false);
			usuarioBean.setSenha(Util.criptografa(usuarioBean.getSenha()));
			new UsuarioDAO().inserir(usuarioBean);
			preencheRetorno(request, response, "Usuario inserido com sucesso", URLs.URL_ERRO_VALIDACAO_USU);
			listarTodosUsuarios(request, response);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public UsuarioBean retornaDadosUsuario(HttpServletRequest request, boolean pegaId) {
		int id	=	0;
		
		if(pegaId)
			id			=	Integer.parseInt(request.getParameter("id"));
		
		String cpf		=	request.getParameter("cpf");
		String email	=	request.getParameter("email");
		String login	=	request.getParameter("login");
		String nome		=	request.getParameter("nome");
		String rg		=	request.getParameter("rg");
		String senha 	=	request.getParameter("senha");
		String sexo 	=	request.getParameter("sexo");
		
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.setId(id);
		usuarioBean.setPerfil(PerfilEnum.ADMINISTRADOR_ESTACIONAMENTO.getCodigo());
		usuarioBean.setCpf(cpf);
		usuarioBean.setEmail(email);
		usuarioBean.setLogin(login);
		usuarioBean.setNome(nome);
		usuarioBean.setRg(rg);
		usuarioBean.setSenha(senha);
		usuarioBean.setSexo(sexo);
		
		return usuarioBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}
