<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - ALTERAÇÃO DE TIPO VAGA DE ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/tipo_vaga.js" ></script>
	</head>
	<body>
	<br><br>
		<div class="container">
			<div class="col-sm-12">
				<%@include file="../../../includes/cabecalho.jsp" %> 
				<form id="formAlterar" name="form" method="post" action="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoVagaController">
					<input type="hidden" name="acao" id="acao" value="ALTERAR">
					<input type="hidden" name="id" id="id" value="${tipoVagaBean.id}">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<br/>
					<fieldset>
						<legend>Alteração de Tipo de Vaga de Estacionamento</legend>
						<br>
						<div class="form-group">
							<font color="red">*</font>&nbsp;Tipo da Vaga..:
							<input class="form-control" type="text" name="nome" id="nome" value="${tipoVagaBean.nome}" maxlength="28">
						</div>
						<div class="form-group">
							<font color="red">*</font>&nbsp;Preço..: 
							R$<input class="form-control" type="text" name="preco" id="preco" value="${tipoVagaBean.preco}" min="1" max="99" step="1" maxlength="2">
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