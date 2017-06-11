<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - REDEFINIÇÃO DE SENHA</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/autenticacao.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-4">
				<fieldset>
    				<legend>REDEFINIR SENHA:</legend>
    				<c:if test="${not empty msg}">
    				<font color="red"><b>${msg}</b></font><br/></br>
					</c:if>
					<form name="redefinir">
						<input type="hidden" name="acao" id="acao" value="">

						<!-- LOGIN -->
						<div class="form-group">
							<b>Login..:</b> <input type="text" name="LOGIN" id="login" maxlength="50" class="form-control" placeholder="INFORME O LOGIN">
						</div>	
						
						<!-- SENHA -->
						<div class="form-group">
							<b>Senha..:</b> <input type="password" name="SENHA" id="senha" maxlength="100" class="form-control" placeholder="INFORME A NOVA SENHA">
						</div>
						
						<br><br>
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
						<br><br>
						
						<!-- BOTÃO ENVIAR -->
						<input type="button" id="redefinirSenha" value="REDEFINIR SENHA" class="btn btn-success"/>
						
						<!-- BOTÃO ENVIAR -->
						<input type="button" id="fechar" value="FECHAR" class="btn btn-info"/>
					
					</form>
				</fieldset>	
			</div>
		</div>		
	</body>
</html>