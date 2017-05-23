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
		<title>TELA DE LISTAGEM DE PAGAMENTO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/pagamento.js" ></script>
		<script>
			function excluir(id, idEstacionamento) {
				var msgExclusao = "Tem certeza que deseja excluir o pagamento?";
				if (confirm(msgExclusao)) {
					var form = document.getElementById('form');
					form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/PagamentoController?acao=EXCLUIR&id=' + id+ '&idEstacionamento=' + idEstacionamento;
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
				<B>TELA DE GERENCIAMENTO DE PAGAMENTO DO SISTEMA</B></legend>
				<form id="form" name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Tipo de Pagamento</td>
							<th>Bandeira</td>
							<th>Ações</th>
						</tr>
						</thead>
						<c:if test="${empty listaPagamentos}">
							<tr align="center">
								<td colspan="6">Não existem pagamentos cadastrados</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaPagamentos}">
							<c:forEach var="pagamento" items="${listaPagamentos}" >
								<tr>
									<td><c:out value="${pagamento.tipoPagamentoBean.nome}"/></td>
									<td><c:out value="${pagamento.tipoPagamentoBean.bandeira}"/></td>
									<td>
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/PagamentoController?acao=DETALHAR_PAGAMENTO&id=<c:out value='${pagamento.id}'/>&idEstacionamento=<c:out value='${idEstacionamento}'/>"><span class="glyphicon glyphicon-pencil"></span></a>
										&nbsp;
										<a href="#" onclick="javascript:excluir('<c:out value='${pagamento.id}'/>','<c:out value='${idEstacionamento}'/>')"><span class="glyphicon glyphicon-trash"></span></a>
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