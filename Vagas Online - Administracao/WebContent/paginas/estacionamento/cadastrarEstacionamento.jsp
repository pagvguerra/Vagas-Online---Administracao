<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
				<legend><B>HOME - TELA DE CADASTRO DE ESTACIONAMENTO</B></legend>
					<form name="frmCadastroEstacionamento">
					<input name="acao" id="acao" type="hidden">
						<br/>
						<fieldset>
							<legend>Cadastro de Estacionamento</legend>
							<br>
							<div class="form-group">
								Nome Fantasia..: <input class="form-control" type="text" name="nomeFantasia" id="nomeFantasia">
							</div>
							<div class="form-group">
								Razão Social..: <input class="form-control" type="text" name="razaoSocial" id="razaoSocial">
							</div>
							<div class="form-group">
								CNPJ..: <input class="form-control" type="text" name="cnpj" id="cnpj">
							</div>
							<div class="form-group">
								Inscrição Municipal..: <input class="form-control" type="text" name="inscricaoMunicipal" id="inscricaoMunicipal">
							</div>
							<div class="form-group">
								Inscrição Estadual..: <input class="form-control" type="text" name="inscricaoEstadual" id="inscricaoEstadual">
							</div>
							<div class="form-group">
								Pais..:
								<c:if test="${not empty listaPais}">
									<select name="pais" class="form-control" >
										<option value="0">SELECIONE</option>
										<c:forEach var="pais" items="${listaPais}" >
											<option value="${pais.id}">${pais.nome}</option>
										</c:forEach>	
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								Estado..: 
								<c:if test="${not empty listaEstado}">
									<select name="estado" class="form-control" >
										<option value="0">SELECIONE</option>
										<c:forEach var="estado" items="${listaEstado}" >
											<option value="${estado.id}">${estado.nome}</option>
										</c:forEach>	
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								Cidade..: 
								<c:if test="${not empty listaCidade}">
									<select name="cidade" class="form-control" >
										<option value="0">SELECIONE</option>
										<c:forEach var="cidade" items="${listaCidade}" >
											<option value="${cidade.id}">${cidade.nome}</option>
										</c:forEach>	
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								Bairro..: 
								<c:if test="${not empty listaBairro}">
									<select name="bairro" class="form-control" >
										<option value="0">SELECIONE</option>
										<c:forEach var="bairro" items="${listaBairro}" >
											<option value="${bairro.id}">${bairro.nome}</option>
										</c:forEach>	
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								Cep..: <input class="form-control" type="text" name="cep" id="cep">
							</div>
							<div class="form-group">
								Tipo de Logradouro..: 
								<c:if test="${not empty listaTipoLogradouro}">
									<select name="tipoLogradouro" class="form-control" >
										<option value="0">SELECIONE</option>
										<c:forEach var="tipoLogradouro" items="${listaTipoLogradouro}" >
											<option value="${tipoLogradouro.id}">${tipoLogradouro.nome}</option>
										</c:forEach>	
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								Nome do Logradouro..: <input class="form-control" type="text" name="nomeLogradouro" id="nomeLogradouro">
							</div>
							<div class="form-group">
								Número..: <input class="form-control" type="text" name="numero" id="numero">
							</div>
							<br/><br/>
							<center>
							<input type="button" name="botaoInserir" id="botaoInserir" value="CADASTRAR" class="btn btn-success">
							<input type="button" name="botaoVoltar" id="botaoVoltar" value="VOLTAR" class="btn btn-info">
							</center>	
						</fieldset> 
					</form>
				</fieldset>
			</div>
		</div>	
</html>