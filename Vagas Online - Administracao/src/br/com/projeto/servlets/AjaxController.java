package br.com.projeto.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.projeto.beans.AjaxBeanGenerico;
import br.com.projeto.daos.BairroDAO;
import br.com.projeto.daos.CidadeDAO;
import br.com.projeto.daos.EstadoDAO;

public class AjaxController extends HttpServlet {

	private static final long serialVersionUID = 6113071850622243135L;
	
	public AjaxController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			
			String acao = request.getParameter("acao");

			if(acao.equalsIgnoreCase("BUSCA_ESTADOS")) {	
				buscaEstadosPorPais(request, response);
			} else if(acao.equalsIgnoreCase("BUSCA_CIDADES")) {	
				buscaCidadesPorEstado(request, response);
			} else if(acao.equalsIgnoreCase("BUSCA_BAIRROS")) {	
				buscaBairrosPorCidade(request, response);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
	}
	
	private void buscaEstadosPorPais(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strIdPais 	= request.getParameter("idPais");
		int idPais 			= "".equals(strIdPais) ? 0 : Integer.parseInt(strIdPais);
		List<AjaxBeanGenerico> listaEstado	=	new EstadoDAO().listaEstadosPorPais(idPais);
		populaListaGenericaAjax(listaEstado, response);
	}

	private void buscaCidadesPorEstado(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strIdEstado	= request.getParameter("idEstado");
		int idEstado 		= "".equals(strIdEstado) ? 0 : Integer.parseInt(strIdEstado);
		List<AjaxBeanGenerico> listaCidade	=	new CidadeDAO().listaCidadesPorEstado(idEstado);
		populaListaGenericaAjax(listaCidade, response);
	}
	
	private void buscaBairrosPorCidade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strIdCidade	= request.getParameter("idCidade");
		int idCidade 		= "".equals(strIdCidade) ? 0 : Integer.parseInt(strIdCidade);
		List<AjaxBeanGenerico> listaBairro	=	new BairroDAO().listaBairrosPorCidade(idCidade);
		populaListaGenericaAjax(listaBairro, response);
	}

	private void populaListaGenericaAjax(List<AjaxBeanGenerico> lista, HttpServletResponse response) throws Exception {
		JSONObject json 	= new JSONObject();
		JSONArray jsonItems = new JSONArray();
		
		try {
			for (AjaxBeanGenerico e : lista) {
				JSONObject j = new JSONObject();
				j.put("id", e.getId());
				j.put("nome", e.getNome());
				jsonItems.put(j);
			}
			json.put("itens", jsonItems);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				printJSON(response, json);
			} catch (IOException e) {
				throw e;
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