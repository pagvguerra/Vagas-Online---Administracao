<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="imagens" value="${pageContext.request.contextPath}/estaticos/images"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/jquery.mask.js" ></script>
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
								<font color="red">*</font>&nbsp;Nome Fantasia..:
								<input class="form-control" type="text" name="nomeFantasia" id="nomeFantasia" maxlength="100">
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Razão Social..:
								<input class="form-control" type="text" name="razaoSocial" id="razaoSocial" maxlength="100">
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;CNPJ..:
								<input class="form-control" type="text" name="cnpj" id="cnpj" maxlength="20">
							</div>
							<div class="form-group">
								Inscrição Municipal..:
								<input class="form-control" type="text" name="inscricaoMunicipal" id="inscricaoMunicipal" maxlength="50">
							</div>
							<div class="form-group">
								Inscrição Estadual..:
								<input class="form-control" type="text" name="inscricaoEstadual" id="inscricaoEstadual" maxlength="50">
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Pais..:
								<c:if test="${not empty listaPais}">
									<select name="pais" id="idPais" class="form-control" >
										<option value="0">SELECIONE</option>
										<c:forEach var="pais" items="${listaPais}" >
											<option value="${pais.id}">${pais.nome}</option>
										</c:forEach>
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Estado..: 
								<div id="divEstados">
									<select name="estado" id="idEstado" class="form-control" >
										<option value="0">SELECIONE</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Cidade..: 
								<div id="divCidades">
									<select name="cidade" id="idCidade" class="form-control" >
										<option value="0">SELECIONE</option>
									</select>
								</div>	
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Bairro..: 
								<div id="divBairros">
									<select name="bairro" id="idBairro" class="form-control" >
										<option value="0">SELECIONE</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Cep..: <input class="form-control" type="text" name="cep" id="cep" maxlength="10">
							</div>
							<div class="form-group">
							<font color="red">*</font>&nbsp;Tipo de Logradouro..: 
								<c:if test="${not empty listaTipoLogradouro}">
									<select id="tipoLogradouro" name="tipoLogradouro" class="form-control" >
										<option value="0">SELECIONE</option>
										<c:forEach var="tipoLogradouro" items="${listaTipoLogradouro}" >
											<option value="${tipoLogradouro.id}">${tipoLogradouro.nome}</option>
										</c:forEach>	
									</select>
								</c:if>	
							</div>
							<div class="form-group">
								<font color="red">*</font>&nbsp;Nome do Logradouro..:
								<input class="form-control" type="text" name="nomeLogradouro" id="nomeLogradouro" maxlength="100">
							</div>
							<div class="form-group">
								Número..:
								<input class="form-control" type="text" name="numero" id="numero" maxlength="5">
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