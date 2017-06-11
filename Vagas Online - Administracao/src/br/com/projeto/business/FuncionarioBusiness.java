package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.EstacionamentoBean;
import br.com.projeto.beans.FuncionarioBean;
import br.com.projeto.daos.FuncionarioDAO;
import br.com.projeto.enums.PerfilEnum;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class FuncionarioBusiness {
	
	private static FuncionarioBusiness instance = null;
	private String urlRetorno = "";

	public static FuncionarioBusiness getInstance() {
		if ( instance == null ) {
			instance = new FuncionarioBusiness();
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
			} else if(acao.equalsIgnoreCase("DETALHAR_FUNCIONARIO")) {
				detalhar(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodos(request, response);
				preencheRetorno(request, response, null, URLs.URL_ERRO_VALIDACAO_FUNCIONARIO);
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
		preencheRetorno(request, response, null, URLs.URL_CADASTRAR_FUNCIONARIO);
	}
	
	private void detalhar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			FuncionarioBean funcionarioBean = new FuncionarioDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("funcionarioBean", funcionarioBean);
			preencheRetorno(request, response, null, "/paginas/estacionamento/funcionarios/detalheFuncionario.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//Recuperando o Id Estacionamento
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);

			//Exclui o funcionario
			new FuncionarioDAO().excluir(Integer.parseInt(request.getParameter("id")));

			//Primeiro Busco se é o ultimo funcionario do estacionamento, se for então altero o status do estacionamento para a fase anterior
			boolean existeFuncionarioNoEstacionamento = new FuncionarioDAO().verificaSeExisteAlgumFuncionarioNoEstacionamento(idEstacionamento);
			
			if(!existeFuncionarioNoEstacionamento) {
				EstacionamentoBean.alteraStatusEstacionamentoParaOStatusAnterior(idEstacionamento);
			}
			
			//Listar Funcionarios
			List<FuncionarioBean> listaFuncionarios = new FuncionarioDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaFuncionarios", listaFuncionarios);

		} catch (Exception e) {
			throw e;
		}
	}

	private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			List<FuncionarioBean> listaFuncionarios = new FuncionarioDAO().listaTodos(idEstacionamento);
			request.setAttribute("listaFuncionarios", listaFuncionarios);
			request.setAttribute("idEstacionamento", idEstacionamento);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void alterar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			FuncionarioBean funcionarioBean = retornaDadosFuncionario(request, true);
			new FuncionarioDAO().alterar(funcionarioBean);
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			preencheRetorno(request, response, "Funcionario atualizado com sucesso", URLs.URL_SUCESSO_ALTERAR_FUNCIONARIO);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void inserir(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			int idEstacionamento = Integer.parseInt(request.getParameter("idEstacionamento"));
			request.setAttribute("idEstacionamento", idEstacionamento);
			
			FuncionarioBean funcionarioBean = retornaDadosFuncionario(request, false);
			funcionarioBean.setSenha(Util.criptografa(Util.geraSenhaAleatoria()));
			boolean cadastrou = new FuncionarioDAO().inserir(funcionarioBean);
			
			if(!cadastrou) {
				throw new Exception("Erro ao inserir funcionário");
			}

			//Primeiro Busco se já existe pelo menos um funcionário no estacionamento, somente se não existir então altero o status do estacionamento para a próxima fase
			boolean existeFuncionarioNoEstacionamento = new FuncionarioDAO().verificaSeExisteAlgumFuncionarioNoEstacionamento(idEstacionamento);
			
			if(!existeFuncionarioNoEstacionamento) {
				EstacionamentoBean.alteraStatusEstacionamentoParaOStatusSeguinte(idEstacionamento);
			}
			
			//Atualiza estacionamento no funcionário
			funcionarioBean.setIdEstacionamento(idEstacionamento);
			funcionarioBean.setId(new FuncionarioDAO().buscarIdFuncionarioPorCPFdoFuncionario(funcionarioBean.getCpf()));
			new FuncionarioDAO().atualizarEstacionamentoNoFuncionario(funcionarioBean);
			
			preencheRetorno(request, response, "Funcionario inserido com sucesso", URLs.URL_SUCESSO_CADASTRAR_FUNCIONARIO);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public FuncionarioBean retornaDadosFuncionario(HttpServletRequest request, boolean pegaId) {
		int id	=	0;
		
		if(pegaId)
			id			=	Integer.parseInt(request.getParameter("id"));
		
		String cpf		=	request.getParameter("cpf").trim();
		String email	=	request.getParameter("email").trim();
		String login	=	request.getParameter("login").trim();
		String nome		=	request.getParameter("nome").trim();
		String rg		=	request.getParameter("rg").trim();
		String sexo 	=	request.getParameter("sexo").trim();
		
		FuncionarioBean funcionarioBean = new FuncionarioBean();
		funcionarioBean.setId(id);
		funcionarioBean.setPerfil(PerfilEnum.FUNCIONARIO.getCodigo());
		funcionarioBean.setCpf(cpf);
		funcionarioBean.setEmail(email);
		funcionarioBean.setLogin(login);
		funcionarioBean.setNome(nome);
		funcionarioBean.setRg(rg);
		funcionarioBean.setSexo(sexo);
		
		return funcionarioBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}
