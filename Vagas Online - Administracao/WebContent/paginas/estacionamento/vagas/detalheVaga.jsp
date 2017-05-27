<%@page import="br.com.projeto.beans.TipoVagaBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.projeto.beans.VagaBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<%
	VagaBean vagaBean = (VagaBean) request.getAttribute("vagaBean");
	String nomeTipoVagaBean = vagaBean.getTipoVagaBean().getNome();
	
	ArrayList<TipoVagaBean> listaTipoVagaBean = (ArrayList<TipoVagaBean>) request.getAttribute("listaTipoVagaBean");
%>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - ALTERAÇÃO DE VAGA DE ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/vaga.js" ></script>
	</head>
	<body>
	<br><br>
		<div class="container">
			<div class="col-sm-12">
				<%@include file="../../includes/cabecalho.jsp" %> 
				<form id="formAlterar" name="form" method="post" action="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController">
					<input type="hidden" name="acao" id="acao" value="ALTERAR">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<input type="hidden" name="id" id="id" value="${vagaBean.id}">
					<br/>
					<fieldset>
						<legend>Alteração de Vaga de Estacionamento</legend>
						<br>
						<div class="form-group">
							Código..: <input class="form-control" type="text" name="codigo" id="codigo" value="${vagaBean.codigo}">
						</div>
						<div class="form-group">
							Tipo da Vaga..:
							<select name="tipoVaga" class="form-control" >
								<% for(int i=0; i<listaTipoVagaBean.size(); i++) { 
										TipoVagaBean tipoVagaBean = (TipoVagaBean) listaTipoVagaBean.get(i); %>	
									<option value="<%=tipoVagaBean.getId()%>" <%if(tipoVagaBean.getNome().equalsIgnoreCase(nomeTipoVagaBean)){%>selected<%}%> ><%=tipoVagaBean.getNome()%></option>
								<% } %>
							</select>
						</div>
						<div class="form-group">
							Altura..: <input class="form-control" type="number" name="altura" id="altura" min="1" max="100" step="1" value="${vagaBean.altura}">
						</div>
						<div class="form-group">
							Largura..: <input class="form-control" type="number" name="largura" id="largura" min="1" max="100" step="1" value="${vagaBean.largura}">
						</div>
						<div class="form-group">
							Comprimento..: <input class="form-control" type="number" name="comprimento" id="comprimento" min="1" max="100" step="1" value="${vagaBean.comprimento}">
						</div>
						<br/><br/>
						<center>
							<input type="button" name="botaoAlterar" id="botaoAlterar" value="ALTERAR" class="btn btn-success">
							<input type="button" name="botaoVoltar" id="botaoVoltar" value="VOLTAR" class="btn btn-info">
						</center>	
					</fieldset> 
				</form>
			</div>
		</div>
	</body>
</html>