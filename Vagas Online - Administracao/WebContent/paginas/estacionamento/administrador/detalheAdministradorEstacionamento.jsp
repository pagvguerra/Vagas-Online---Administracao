<%@page import="br.com.projeto.beans.AdministradorEstacionamentoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<%
	AdministradorEstacionamentoBean administradorEstacionamentoBean = (AdministradorEstacionamentoBean) request.getAttribute("administradorEstacionamentoBean");
	String sexo = administradorEstacionamentoBean.getSexo();
%>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - ALTERAÇÃO DE ADMINISTRADOR DE ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/administrador.js" ></script>
	</head>
<body>
<br><br>
<div class="container">
	<div class="col-sm-10">
		<fieldset>
		<legend><B>ALTERAÇÃO DE ADMINISTRADOR DE ESTACIONAMENTO</B></legend>
		<form name="frmAlterarEstacionamento">
			<input name="acao" id="acao" type="hidden" value="ALTERAR">
			<input type="hidden" name="id" id="id" value="${administradorEstacionamentoBean.id}">
			<br>
			<div class="form-group">
				Login..: ${administradorEstacionamentoBean.login}
			</div>
			<div class="form-group">
				Nome..: <input class="form-control" type="text" name="nome" id="nome" value="${administradorEstacionamentoBean.nome}" maxlength="">
			</div>
			<div class="form-group">
				CPF..: <input class="form-control" type="text" name="cpf" id="cpf" value="${administradorEstacionamentoBean.cpf}" maxlength="">
			</div>
			<div class="form-group">
				RG..: <input class="form-control" type="text" name="rg" id="rg" value="${administradorEstacionamentoBean.rg}" maxlength="">
			</div>
			<div class="form-group">
				Email..: <input class="form-control" type="text" name="email" id="email" value="${administradorEstacionamentoBean.email}" maxlength="">
			</div>
			<div class="form-group">
				Sexo..: 
				<select name="sexo" class="form-control">
					<option value="M" <%if(sexo.equalsIgnoreCase("M")){%>selected<%}%>>Masculino</option>
					<option value="F" <%if(sexo.equalsIgnoreCase("F")){%>selected<%}%>>Feminino</option>
				</select>
			</div>
			<br><br>
			<div class="form-group">
				<b>Perguntas para caso de Redefinição de senha:</b>
			</div>
			<br>
			<div class="form-group">
				Pergunta 1 ..: Qual a sua cor favorita?
			<input class="form-control" type="text" name="resposta1" id="resposta1" value="${administradorEstacionamentoBean.resposta1}" maxlength="">
			</div>
			<div class="form-group">
				Pergunta 2 ..: Qual o nome completo da sua mãe?
			<input class="form-control" type="text" name="resposta2" id="resposta2" value="${administradorEstacionamentoBean.resposta2}" maxlength="">
			</div>
			<div class="form-group">
				Pergunta 3 ..: Qual o ano do seu nascimento?
			<input class="form-control" type="text" name="resposta3" id="resposta3" value="${administradorEstacionamentoBean.resposta3}" maxlength="">
			</div>
			<div class="form-group">
				Pergunta 4 ..: Qual o seu maior hobby?
			<input class="form-control" type="text" name="resposta4" id="resposta4" value="${administradorEstacionamentoBean.resposta4}" maxlength="">
			</div>
			<br/><br/>
			<center>
			<input type="button" name="botaoAlterar" id="botaoAlterar" value="ALTERAR" class="btn btn-success">
			<input type="button" name="botaoFechar" id="botaoFechar" value="FECHAR" class="btn btn-info">
			</center>	
		</form>
		</fieldset> 
	</div>
</div>
</body>
</html>	