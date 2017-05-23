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
		<title>ESTACIONAMENTO ONLINE - ALTERA��O DE FUNCION�RIO DE ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/funcionario.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-10">
				<%@include file="../../includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>TELA DE ALTERA��O DE FUNCION�RIO DO ESTACIONAMENTO</B></legend>
				<br><br>
					<div class="form-group">
						<b>Funcion�rio alterado com sucesso</b>
					</div>
					<br>
					<form>
						<input type="hidden" name="acao" id="acao">
						<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
						<!-- BOT�O VOLTAR -->
						<input type="button" id="botaoVoltarListagem" value="VOLTAR PARA A LISTAGEM DE FUNCION�RIOS" class="btn btn-info"/>
					</form>
				</fieldset>	
			</div>
		</div>		
	</body>
</html>