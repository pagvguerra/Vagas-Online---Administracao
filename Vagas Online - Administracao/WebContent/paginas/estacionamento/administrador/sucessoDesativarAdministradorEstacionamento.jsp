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
		<title>ESTACIONAMENTO ONLINE</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/administrador.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-12">
				<img src="${pageContext.request.contextPath}/estaticos/images/logo_carro.png" width="160px;" height="120px;">
				<br><br><br>
				<fieldset>
					<legend><B>HOME - TELA DE ENTRADA DO ADMINISTRADOR DO ESTACIONAMENTO</B></legend>
					<div class="form-group">
						<b>Conta desativada com sucesso</b>
					</div>
					<br><br>
					<form>
					<input type="hidden" id="acao" name="acao">
						<input type="button" name="botaoVoltarEntrada" id="botaoVoltarEntrada" value="VOLTAR A TELA DE ENTRADA" class="btn btn-primary"/>
					</form>
				</fieldset>
			</div>
		</div>	
	</body>
</html>