package br.com.projeto.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.business.TipoPagamentoBusiness;

public class TipoPagamentoController extends HttpServlet {

	private static final long serialVersionUID = -5261808852495605817L;

	public TipoPagamentoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlRedirecinamento = TipoPagamentoBusiness.getInstance().execute(request, response);
		request.getRequestDispatcher(urlRedirecinamento).forward(request,response);
	}


	
	
	
}
