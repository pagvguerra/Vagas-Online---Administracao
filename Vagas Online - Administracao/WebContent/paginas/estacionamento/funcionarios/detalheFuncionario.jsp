<%@page import="br.com.projeto.beans.FuncionarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<%
	FuncionarioBean funcionarioBean = (FuncionarioBean) request.getAttribute("funcionarioBean");
	String sexo = funcionarioBean.getSexo();
%>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - ALTERAÇÃO DE FUNCIONÁRIO DE ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/funcionario.js" ></script>
	</head>
<body>
<br><br>
<div class="container">
	<div class="col-sm-12">
		<%@include file="../../includes/cabecalho.jsp" %> 
		<fieldset>
		<legend><B>TELA DE ALTERAÇÃO DE FUNCIONÁRIO ESTACIONAMENTO</B></legend>
		<form name="frmAlterarFuncionario">
			<input name="acao" id="acao" type="hidden" value="ALTERAR">
			<input type="hidden" name="id" id="id" value="${funcionarioBean.id}">
			<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
			<br>
			<div class="form-group">
				Login..: <input class="form-control" type="text" name="login" id="login" value="${funcionarioBean.nome}" maxlength="">
			</div>
			<div class="form-group">
				Senha..: ${funcionarioBean.senha}
			</div>
			<div class="form-group">
				Nome..: <input class="form-control" type="text" name="nome" id="nome" value="${funcionarioBean.nome}" maxlength="">
			</div>
			<div class="form-group">
				CPF..: <input class="form-control" type="text" name="cpf" id="cpf" value="${funcionarioBean.cpf}" maxlength="">
			</div>
			<div class="form-group">
				RG..: <input class="form-control" type="text" name="rg" id="rg" value="${funcionarioBean.rg}" maxlength="">
			</div>
			<div class="form-group">
				Email..: <input class="form-control" type="text" name="email" id="email" value="${funcionarioBean.email}" maxlength="">
			</div>
			<div class="form-group">
				Sexo..: 
				<select name="sexo" class="form-control">
					<option value="M" <%if(sexo.equalsIgnoreCase("M")){%>selected<%}%>>Masculino</option>
					<option value="F" <%if(sexo.equalsIgnoreCase("F")){%>selected<%}%>>Feminino</option>
				</select>
			</div>
			<br/><br/>
			<center>
			<input type="button" name="botaoAlterar" id="botaoAlterar" value="ALTERAR" class="btn btn-success">
			<input type="button" name="botaoFechar" id="botaoFechar" value="VOLTAR" class="btn btn-info">
			</center>	
		</form>
		</fieldset> 
	</div>
</div>
</body>
</html>	