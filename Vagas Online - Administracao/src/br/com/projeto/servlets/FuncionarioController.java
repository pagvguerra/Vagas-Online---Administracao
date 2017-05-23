package br.com.projeto.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.business.FuncionarioBusiness;

public class FuncionarioController extends HttpServlet {

	private static final long serialVersionUID = -1497796037020181950L;

	public FuncionarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlRedirecinamento = FuncionarioBusiness.getInstance().execute(request, response);
		request.getRequestDispatcher(urlRedirecinamento).forward(request,response);
	}

}
