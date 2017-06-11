<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>TELA DE LISTAGEM DE FUNCIONÁRIO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/funcionario.js" ></script>
		<script>
			function excluir(id, idEstacionamento) {
				var msgExclusao = "Tem certeza que deseja excluir o funcionario?";
				if (confirm(msgExclusao)) {
					var form = document.getElementById('form');
					form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/FuncionarioController?acao=EXCLUIR&id=' + id+ '&idEstacionamento=' + idEstacionamento;
					form.method	= 'post';
					form.submit();
				}
			}
		</script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-12">
				<%@include file="../../includes/cabecalho.jsp" %> 
				<fieldset>
					<legend>
				<B>TELA DE GERENCIAMENTO DE FUNCIONÁRIOS DO SISTEMA</B></legend>
				<form id="form" name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Nome</th>
							<th>Login</th>
							<th>Senha</th>
							<th>CPF</th>
							<th>RG</th>
							<th>Email</th>
							<th>Ações</th>
						</tr>
						</thead>
						<c:if test="${empty listaFuncionarios}">
							<tr align="center">
								<td colspan="6">Não existem funcionários cadastrados</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaFuncionarios}">
							<c:forEach var="funcionario" items="${listaFuncionarios}" >
								<tr>
									<td><c:out value="${funcionario.nome}"/></td>
									<td><c:out value="${funcionario.login}"/></td>
									<td><c:out value="${funcionario.senha}"/></td>
									<td><c:out value="${funcionario.cpf}"/></td>
									<td><c:out value="${funcionario.rg}"/></td>
									<td><c:out value="${funcionario.email}"/></td>
									<td>
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/FuncionarioController?acao=DETALHAR_FUNCIONARIO&id=<c:out value='${funcionario.id}'/>&idEstacionamento=<c:out value='${idEstacionamento}'/>"><span class="glyphicon glyphicon-pencil"></span></a>
										&nbsp;
										<a href="#" onclick="javascript:excluir('<c:out value='${funcionario.id}'/>','<c:out value='${idEstacionamento}'/>')"><span class="glyphicon glyphicon-trash"></span></a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<br /><br />
					<input type="button" name="botaoPrepararInserir" id="botaoPrepararInserir" value="INSERIR" class="btn btn-success"/>
					<input type="button" name="botaoVoltarListagemAdministradorEstacionamento" id="botaoVoltarListagemAdministradorEstacionamento" value="VOLTAR" class="btn btn-primary"/>
				</form>
				</fieldset>
			</div>
		</div>
		
	</body>
</html>