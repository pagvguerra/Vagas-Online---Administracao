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
		<title>ESTACIONAMENTO ONLINE - CADASTRO DE ADMINISTRADOR DE ESTACIONAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/administrador.js" ></script>
	</head>
	<body>
		<div class="container">
			<div class="col-sm-8">
				<form id="formCadastrarAdministradorEstacionamento" name="form" method="post" action="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController">
					<input type="hidden" name="acao" id="acao" value="INSERIR"> 
					<br/>
					<fieldset>
						<legend>Cadastro de Administrador de Estacionamento</legend>
						<br>
						<div class="form-group">
							Login..: <input class="form-control" type="text" name="login" id="login">
						</div>
						<div class="form-group">
							Senha..: <input class="form-control" type="password" name="senha" id="senha">
						</div>
						<div class="form-group">
							Nome..: <input class="form-control" type="text" name="nome" id="nome">
						</div>
						<div class="form-group">
							CPF..: <input class="form-control" type="text" name="cpf" id="cpf">
						</div>
						<div class="form-group">
							RG..: <input class="form-control" type="text" name="rg" id="rg">
						</div>
						<div class="form-group">
							Email..: <input class="form-control" type="text" name="email" id="email">
						</div>
						<div class="form-group">
							Sexo..: 
							<select name="sexo" class="form-control" >
								<option value="M">Masculino</option>
								<option value="F">Feminino</option>
							</select>
						</div>
						<br><br>
						<div class="form-group">
							<b>Perguntas para caso de Redefinição de senha:</b>
						</div>
						<br>
						<div class="form-group">
							Pergunta 1 ..: Qual a sua cor favorita?
							<input class="form-control" type="text" name="resposta1" id="resposta1">
						</div>
						<div class="form-group">
							Pergunta 2 ..: Qual o nome completo da sua mãe?
							<input class="form-control" type="text" name="resposta2" id="resposta2">
						</div>
						<div class="form-group">
							Pergunta 3 ..: Qual o ano do seu nascimento?
							<input class="form-control" type="text" name="resposta3" id="resposta3">
						</div>
						<div class="form-group">
							Pergunta 4 ..: Qual o seu maior hobby?
							<input class="form-control" type="text" name="resposta4" id="resposta4">
						</div>
						<br/><br/>
						<center>
							<input type="button" name="botaoInserir" id="botaoInserir" value="CADASTRAR" class="btn btn-success">
							<input type="button" name="botaoFechar" id="botaoFechar" value="FECHAR" class="btn btn-info">
						</center>	
					</fieldset> 
				</form>
			</div>
		</div>
	</body>
</html>	