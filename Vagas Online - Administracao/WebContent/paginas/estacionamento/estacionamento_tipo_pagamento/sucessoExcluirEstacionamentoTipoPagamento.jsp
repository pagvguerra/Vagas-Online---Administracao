<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - EXCLUSÃO DE TIPO DE PAGAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/estacionamento_tipo_pagamento.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-10">
				<%@include file="../../includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>TELA DE EXCLUSÃO DE TIPO DE PAGAMENTO</B></legend>
				<br><br>
					<div class="form-group">
						<b>Tipo de Pagamento excluído com sucesso para este estacionamento</b>
					</div>
					<br>
					<form>
						<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
						<input type="hidden" name="acao" id="acao" value="LISTAR_TODOS">
						<input type="submit" id="botaoVoltar" value="VOLTAR PARA A LISTA DE TIPO DE PAGAMENTO" class="btn btn-info"/>
					</form>
				</fieldset>	
			</div>
		</div>		
	</body>
</html>