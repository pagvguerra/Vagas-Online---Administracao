<%@page import="br.com.projeto.beans.TipoLogradouroBean"%>
<%@page import="br.com.projeto.beans.BairroBean"%>
<%@page import="br.com.projeto.beans.CidadeBean"%>
<%@page import="br.com.projeto.beans.EstadoBean"%>
<%@page import="br.com.projeto.beans.EstacionamentoBean"%>
<%@page import="br.com.projeto.beans.PaisBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% 
	ArrayList<PaisBean> listaPais 						=	(ArrayList<PaisBean>) request.getAttribute("listaPais");
	ArrayList<EstadoBean> listaEstado 					=	(ArrayList<EstadoBean>) request.getAttribute("listaEstado");
	ArrayList<CidadeBean> listaCidade 					=	(ArrayList<CidadeBean>) request.getAttribute("listaCidade");
	ArrayList<BairroBean> listaBairro 					=	(ArrayList<BairroBean>) request.getAttribute("listaBairro");
	ArrayList<TipoLogradouroBean> listaTipoLogradouro 	=	(ArrayList<TipoLogradouroBean>) request.getAttribute("listaTipoLogradouro");

	EstacionamentoBean estacionamentoBean				=	(EstacionamentoBean) request.getAttribute("estacionamentoBean");
%>
<!DOCTYPE>
<%@ taglib uri="../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js/"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/estacionamento.js" ></script>
	</head>
	<body>
		<br/><br/>
	</body>
		<div class="container">
			<div class="col-sm-10">
				<%@include file="../includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>HOME - TELA DE ALTERAÇÃO DE ESTACIONAMENTO</B></legend>
					<form name="frmAlterarEstacionamento">
					<input name="acao" id="acao" type="hidden">
					<input type="hidden" name="id" id="id" value="${estacionamentoBean.id}">
					<input type="hidden" name="idEndereco" id="idEndereco" value="${estacionamentoBean.enderecoBean.id}">
						<br/>
						<fieldset>
							<legend>Alteração de Estacionamento</legend>
							<br>
							<div class="form-group">
								Nome Fantasia..: <input class="form-control" type="text" name="nomeFantasia" id="nomeFantasia" value="${estacionamentoBean.nomeFantasia}">
							</div>
							<div class="form-group">
								Razão Social..: <input class="form-control" type="text" name="razaoSocial" id="razaoSocial" value="${estacionamentoBean.razaoSocial}">
							</div>
							<div class="form-group">
								CNPJ..: <input class="form-control" type="text" name="cnpj" id="cnpj" value="${estacionamentoBean.cnpj}">
							</div>
							<div class="form-group">
								Inscrição Municipal..: <input class="form-control" type="text" name="inscricaoMunicipal" id="inscricaoMunicipal" value="${estacionamentoBean.inscricaoMunicipal}">
							</div>
							<div class="form-group">
								Inscrição Estadual..: <input class="form-control" type="text" name="inscricaoEstadual" id="inscricaoEstadual" value="${estacionamentoBean.inscricaoEstadual}">
							</div>
							<div class="form-group">
								Pais..:
								<select name="pais" class="form-control" >
									<option value="0">SELECIONE</option>
									<% 	for(int i=0; i<listaPais.size(); i++) { 
											PaisBean paisBean = (PaisBean) listaPais.get(i); %>
											<option value="<%=paisBean.getId()%>" <%if(paisBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getPaisBean().getNome())){%>selected<%}%>><%=paisBean.getNome()%></option>
									<%	} %>
								</select>
							</div>
							<div class="form-group">
								Estado..:
								<select name="estado" class="form-control" >
									<option value="0">SELECIONE</option>
									<% 	for(int i=0; i<listaEstado.size(); i++) { 
											EstadoBean estadoBean = (EstadoBean) listaEstado.get(i); %>
											<option value="<%=estadoBean.getId()%>" <%if(estadoBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getEstadoBean().getNome())){%>selected<%}%>><%=estadoBean.getNome()%></option>
									<%	} %>
								</select>
							</div>
							<div class="form-group">
								Cidade..: 
								<select name="cidade" class="form-control" >
									<option value="0">SELECIONE</option>
									<% 	for(int i=0; i<listaCidade.size(); i++) { 
											CidadeBean cidadeBean = (CidadeBean) listaCidade.get(i); %>
											<option value="<%=cidadeBean.getId()%>" <%if(cidadeBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getCidadeBean().getNome())){%>selected<%}%>><%=cidadeBean.getNome()%></option>
									<%	} %>
								</select>
							</div>
							<div class="form-group">
								Bairro..: 
								<select name="bairro" class="form-control" >
									<option value="0">SELECIONE</option>
									<% 	for(int i=0; i<listaBairro.size(); i++) { 
											BairroBean bairroBean = (BairroBean) listaBairro.get(i); %>
											<option value="<%=bairroBean.getId()%>" <%if(bairroBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getBairroBean().getNome())){%>selected<%}%>><%=bairroBean.getNome()%></option>
									<%	} %>
								</select>
							</div>
							<div class="form-group">
								Cep..: <input class="form-control" type="text" name="cep" id="cep" value="${estacionamentoBean.enderecoBean.cep}">
							</div>
							<div class="form-group">
								Tipo de Logradouro..: 
								<select name="tipoLogradouro" class="form-control" >
									<option value="0">SELECIONE</option>
									<% 	for(int i=0; i<listaTipoLogradouro.size(); i++) { 
										TipoLogradouroBean tipoLogradouroBean = (TipoLogradouroBean) listaTipoLogradouro.get(i); %>
											<option value="<%=tipoLogradouroBean.getId()%>" <%if(tipoLogradouroBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getTipoLogradouroBean().getNome())){%>selected<%}%>><%=tipoLogradouroBean.getNome()%></option>
									<%	} %>
								</select>
							</div>
							<div class="form-group">
								Nome do Logradouro..: <input class="form-control" type="text" name="nomeLogradouro" id="nomeLogradouro" value="${estacionamentoBean.enderecoBean.nomeLogradouro}">
							</div>
							<div class="form-group">
								Número..: <input class="form-control" type="text" name="numero" id="numero" value="${estacionamentoBean.enderecoBean.numero}">
							</div>
							<br/><br/>
							<center>
							<input type="button" name="botaoAlterar" id="botaoAlterar" value="ALTERAR" class="btn btn-success">
							<input type="button" name="botaoVoltar" id="botaoVoltar" value="VOLTAR" class="btn btn-info">
							</center>	
						</fieldset> 
					</form>
				</fieldset>
			</div>
		</div>	
</html>