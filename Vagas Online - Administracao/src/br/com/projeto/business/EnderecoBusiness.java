package br.com.projeto.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.BairroBean;
import br.com.projeto.beans.CidadeBean;
import br.com.projeto.beans.CoordenadaBean;
import br.com.projeto.beans.EnderecoBean;
import br.com.projeto.beans.EstadoBean;
import br.com.projeto.beans.PaisBean;
import br.com.projeto.daos.BairroDAO;
import br.com.projeto.daos.CidadeDAO;
import br.com.projeto.daos.EnderecoDAO;
import br.com.projeto.daos.EstadoDAO;
import br.com.projeto.daos.PaisDAO;
import br.com.projeto.utils.GoogleAPI;
import br.com.projeto.utils.Util;

public class EnderecoBusiness {

	private static EnderecoBusiness instance = null;

	public static EnderecoBusiness getInstance() {
		if ( instance == null ) {
			instance = new EnderecoBusiness();
		}
		return instance;
	}

	public void inserirEndereco(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			EnderecoBean enderecoBean = retornaDadosEndereco(request, false);
			new EnderecoDAO().inserir(enderecoBean);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public EnderecoBean retornaDadosEndereco(HttpServletRequest request, boolean pegaId) throws Exception {

		EnderecoBean enderecoBean = new EnderecoBean();
		int id 					  = 0;
		
		if(pegaId) {
			id			=	Integer.parseInt(request.getParameter("idEndereco"));
			enderecoBean.setId(id);
		}
		
		String nomeLogradouro = request.getParameter("nomeLogradouro").trim();
		enderecoBean.setNomeLogradouro(nomeLogradouro);
		
		String cep = request.getParameter("cep").trim();
		enderecoBean.setCep(cep);

		int numeroPorta = Integer.parseInt(request.getParameter("numero"));
		enderecoBean.setNumero(numeroPorta);
		
		int bairro = Integer.parseInt(request.getParameter("bairro"));
		BairroBean bairroBean = new BairroBean();
		bairroBean.setId(bairro);
		bairroBean.setNome(new BairroDAO().buscaBairroPorId(bairro));
		enderecoBean.setBairroBean(bairroBean);
		
		int cidade = Integer.parseInt(request.getParameter("cidade"));
		CidadeBean cidadeBean = new CidadeBean();
		cidadeBean.setId(cidade);
		cidadeBean.setNome(new CidadeDAO().buscaCidadePorId(cidade));
		enderecoBean.setCidadeBean(cidadeBean);
		
		int estado = Integer.parseInt(request.getParameter("estado"));
		EstadoBean estadoBean = new EstadoBean();
		estadoBean.setId(estado);
		estadoBean.setNome(new EstadoDAO().buscaEstadoPorId(estado));
		enderecoBean.setEstadoBean(estadoBean);
		
		int pais =  Integer.parseInt(request.getParameter("pais"));
		PaisBean paisBean = new PaisBean();
		paisBean.setId(pais);
		paisBean.setNome(new PaisDAO().buscaPaisPorId(pais));
		enderecoBean.setPaisBean(paisBean);
		
		CoordenadaBean coordenadaBean = obterCoordenadasDoEndereco(enderecoBean);
		enderecoBean.setCoordenadaBean(coordenadaBean);
		
		return enderecoBean;
	}

	private CoordenadaBean obterCoordenadasDoEndereco(EnderecoBean enderecoBean) throws Exception {
		
		CoordenadaBean coordenadaBean = null;
		
		try {
			StringBuilder enderecoPorExtenso = new StringBuilder("");
			enderecoPorExtenso.append(enderecoBean.getNomeLogradouro()).append(" ");
			enderecoPorExtenso.append(enderecoBean.getNumero()).append(" ");
			enderecoPorExtenso.append(enderecoBean.getCep()).append(" ");
			enderecoPorExtenso.append(enderecoBean.getBairroBean().getNome()).append(" ");
			enderecoPorExtenso.append(enderecoBean.getCidadeBean().getNome()).append(" ");
			enderecoPorExtenso.append(enderecoBean.getEstadoBean().getNome()).append(" ");
			enderecoPorExtenso.append(enderecoBean.getPaisBean().getNome()).append(" ");
			String endereco = Util.retiraAcentos(enderecoPorExtenso.toString());
			coordenadaBean = new GoogleAPI().buscaCoordenadasPorEndereco(endereco);
		} catch (Exception e) {
			throw new Exception("Não foi possível obter as coordenadas com o endereço cadastrado. Favor rever o endereço.");
		}
		return coordenadaBean;
	} 

}