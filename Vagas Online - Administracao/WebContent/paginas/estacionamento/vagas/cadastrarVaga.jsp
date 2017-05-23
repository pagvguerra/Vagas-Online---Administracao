<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - CADASTRO DE VAGA DE ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/vaga.js" ></script>
	</head>
	<body>
	<br><br>
		<div class="container">
			<div class="col-sm-12">
				<%@include file="../../includes/cabecalho.jsp" %> 
				<form id="formCadastrarVagaEstacionamento" name="form" method="post" action="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController">
					<input type="hidden" name="acao" id="acao" value="INSERIR">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<br/>
					<fieldset>
						<legend>Cadastro de Vaga de Estacionamento</legend>
						<br>
						<div class="form-group">
							Código..: <input class="form-control" type="text" name="codigo" id="codigo">
						</div>
						<div class="form-group">
							Tipo da Vaga..:
							<c:if test="${not empty listaTipoVagaBean}">
								<select name="tipoVaga" class="form-control" >
									<c:forEach var="tipoVagaBean" items="${listaTipoVagaBean}" >
										<option value="${tipoVagaBean.id}">${tipoVagaBean.nome}</option>
									</c:forEach>
								</select>
							</c:if>
							<c:if test="${empty listaTipoVagaBean}">
								Tipo de Vagas não cadastradas
							</c:if>
						</div>
						<div class="form-group">
							Altura..: <input class="form-control" type="number" name="altura" id="altura" min="1" max="100" step="1" value="1">
						</div>
						<div class="form-group">
							Largura..: <input class="form-control" type="number" name="largura" id="largura" min="1" max="100" step="1" value="1">
						</div>
						<br/><br/>
						<center>
							<input type="button" name="botaoInserir" id="botaoInserir" value="CADASTRAR" class="btn btn-success">
							<input type="button" name="botaoVoltar" id="botaoVoltar" value="VOLTAR" class="btn btn-info">
						</center>	
					</fieldset> 
				</form>
			</div>
		</div>
	</body>
</html>