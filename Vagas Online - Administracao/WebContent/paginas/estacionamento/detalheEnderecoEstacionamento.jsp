<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../tlds/c.tld" prefix="c" %>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - ENDEREÇO DO ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="col-sm-8">
				<br/><br/>
					<fieldset>
					<legend>Endereço do Estacionamento</legend>
					<br>
					<div class="form-group">
						<b>País..:</b> ${estacionamentoBean.enderecoBean.paisBean.nome}
					</div>
					<div class="form-group">
						<b>Estado..:</b> ${estacionamentoBean.enderecoBean.estadoBean.nome}
					</div>
					<div class="form-group">
						<b>Cidade..:</b> ${estacionamentoBean.enderecoBean.cidadeBean.nome}
					</div>
					<div class="form-group">
						<b>Cep..</b>: ${estacionamentoBean.enderecoBean.cep}
					</div>
					<div class="form-group">
						<b>Bairro..:</b> ${estacionamentoBean.enderecoBean.bairroBean.nome}
					</div>
					<div class="form-group">
						<b>Logradouro..:</b> ${estacionamentoBean.enderecoBean.nomeLogradouro} ${estacionamentoBean.enderecoBean.numero}
					</div>
				</fieldset> 
			</div>
		</div>
	</body>
</html>	