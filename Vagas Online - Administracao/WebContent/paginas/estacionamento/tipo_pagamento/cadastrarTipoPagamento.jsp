<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
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
		<script src="${jspaginas}/tipo_pagamento.js" ></script>
	</head>
	<body>
		<br/><br/>
	</body>
		<div class="container">
			<div class="col-sm-10">
				<%@include file="../../includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>HOME - TELA DE CADASTRO DE TIPO DE PAGAMENTO</B></legend>
					<form name="form" id="form">
					<input name="acao" id="acao" type="hidden">
					<input name="idEstacionamento" id="idEstacionamento" type="hidden" value="${idEstacionamento}">
						<br/>
						<fieldset>
							<legend>Cadastro de Tipo de Pagamento</legend>
							<br>
							<div class="form-group">
								<b>Tipo da Pagamento..:</b><br>
								<input type="checkbox" name="tipoPagamento" id="tipoPagamentoDinheiro" value="1" checked="checked" disabled="disabled" />&nbsp;<img src="${pageContext.request.contextPath}/estaticos/images/dinheiro.png" title="dinheiro">&nbsp;&nbsp;
								<input type="checkbox" name="tipoPagamento" id="tipoPagamentoVisa" value="2" />&nbsp;<img src="${pageContext.request.contextPath}/estaticos/images/visa.png" title="cart�o visa">&nbsp;&nbsp;
								<input type="checkbox" name="tipoPagamento" id="tipoPagamentoMaster" value="3" />&nbsp;<img src="${pageContext.request.contextPath}/estaticos/images/master.png" title="cart�o mastercard">&nbsp;&nbsp;
								<input type="checkbox" name="tipoPagamento" id="tipoPagamentoAmex" value="4" />&nbsp;<img src="${pageContext.request.contextPath}/estaticos/images/amex.png" title="cart�o american express">
							</div>
							<br><br><br><br><br><br>
							<center>
							<input type="button" name="botaoCadastrar" id="botaoCadastrar" value="CADASTRAR" class="btn btn-success">
							<input type="button" name="botaoVoltar" id="botaoVoltar" value="VOLTAR" class="btn btn-info">
							</center>	
						</fieldset> 
					</form>
				</fieldset>
			</div>
		</div>	
</html>