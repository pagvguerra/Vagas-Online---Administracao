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
				<legend><B>HOME - TELA DE ALTERA��O DE ESTACIONAMENTO</B></legend>
					<form name="frmAlterarEstacionamento">
					<input name="acao" id="acao" type="hidden">
					<input type="hidden" name="id" id="id" value="${estacionamentoBean.id}">
					<input type="hidden" name="idEndereco" id="idEndereco" value="${estacionamentoBean.enderecoBean.id}">
						<br/>
						<fieldset>
							<legend>Altera��o de Estacionamento</legend>
							<br>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Nome Fantasia..:
								<input class="form-control" type="text" name="nomeFantasia" id="nomeFantasia" value="${estacionamentoBean.nomeFantasia}" maxlength="100">
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Raz�o Social..:
								<input class="form-control" type="text" name="razaoSocial" id="razaoSocial" value="${estacionamentoBean.razaoSocial}" maxlength="100">
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;CNPJ..:
								<input class="form-control" type="text" name="cnpj" id="cnpj" value="${estacionamentoBean.cnpj}" maxlength="20">
							</div>
							<div class="form-group">
								Inscri��o Municipal..: 
								<input class="form-control" type="text" name="inscricaoMunicipal" id="inscricaoMunicipal" value="${estacionamentoBean.inscricaoMunicipal}" maxlength="50">
							</div>
							<div class="form-group">
								Inscri��o Estadual..: 
								<input class="form-control" type="text" name="inscricaoEstadual" id="inscricaoEstadual" value="${estacionamentoBean.inscricaoEstadual}" maxlength="50">
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Pais..:
								<c:if test="${not empty listaPais}">
									<select name="pais" id="idPais" class="form-control" >
										<option value="0">SELECIONE</option>
										<% 	for(int i=0; i<listaPais.size(); i++) { 
												PaisBean paisBean = (PaisBean) listaPais.get(i); %>
												<option value="<%=paisBean.getId()%>" <%if(paisBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getPaisBean().getNome())){%>selected<%}%>><%=paisBean.getNome()%></option>
										<%	} %>
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Estado..:
								<div id="divEstados">
									<select name="estado" id="idEstado" class="form-control" >
										<option value="0">SELECIONE</option>
										<% 	for(int i=0; i<listaEstado.size(); i++) { 
												EstadoBean estadoBean = (EstadoBean) listaEstado.get(i); %>
												<option value="<%=estadoBean.getId()%>" <%if(estadoBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getEstadoBean().getNome())){%>selected<%}%>><%=estadoBean.getNome()%></option>
										<%	} %>
									</select>
								</div>	
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Cidade..:
								<div id="divCidades">
									<select name="cidade" id="idCidade" class="form-control" >
										<option value="0">SELECIONE</option>
										<% 	for(int i=0; i<listaCidade.size(); i++) { 
												CidadeBean cidadeBean = (CidadeBean) listaCidade.get(i); %>
												<option value="<%=cidadeBean.getId()%>" <%if(cidadeBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getCidadeBean().getNome())){%>selected<%}%>><%=cidadeBean.getNome()%></option>
										<%	} %>
									</select>
								</div>
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Bairro..: 
								<div id="divBairros">
									<select name="bairro" id="idBairro" class="form-control" >
										<option value="0">SELECIONE</option>
										<% 	for(int i=0; i<listaBairro.size(); i++) { 
												BairroBean bairroBean = (BairroBean) listaBairro.get(i); %>
												<option value="<%=bairroBean.getId()%>" <%if(bairroBean.getNome().equalsIgnoreCase(estacionamentoBean.getEnderecoBean().getBairroBean().getNome())){%>selected<%}%>><%=bairroBean.getNome()%></option>
										<%	} %>
									</select>
								</div>	
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Cep..:
								<input class="form-control" type="text" name="cep" id="cep" value="${estacionamentoBean.enderecoBean.cep}" maxlength="10">
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Nome do Logradouro..:
								<input class="form-control" type="text" name="nomeLogradouro" id="nomeLogradouro" value="${estacionamentoBean.enderecoBean.nomeLogradouro}" maxlength="100">
							</div>
							<div class="form-group">
								N�mero..:
								<input class="form-control" type="text" name="numero" id="numero" value="${estacionamentoBean.enderecoBean.numero}" maxlength="5">
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