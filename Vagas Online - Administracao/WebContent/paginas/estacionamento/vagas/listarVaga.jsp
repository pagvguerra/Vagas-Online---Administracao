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
		<title>TELA DE LISTAGEM DE VAGA</title>		
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/vaga.js" ></script>
		<script>
			function excluir(id, idEstacionamento) {
				var msgExclusao = "Tem certeza que deseja excluir a vaga?";
				if (confirm(msgExclusao)) {
					var form = document.getElementById('form');
					form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController?acao=EXCLUIR&id=' + id+ '&idEstacionamento=' + idEstacionamento;
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
				<B>TELA DE GERENCIAMENTO DE VAGAS DO SISTEMA</B></legend>
				<form id="form" name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<input type="button" name="botaoGerenciarTipoVaga" id="botaoGerenciarTipoVaga" value="GERENCIAR TIPO DE VAGA" class="btn btn-default"/>
					<br><br><br><br>
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Código</td>
							<th>Tipo da Vaga</td>
							<th>Altura</td>
							<th>Largura</td>
							<th>Ações</th>
						</tr>
						</thead>
						<c:if test="${empty listaVagas}">
							<tr align="center">
								<td colspan="6">Não existem vagas cadastradas</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaVagas}">
							<c:forEach var="vaga" items="${listaVagas}" >
								<tr>
									<td><c:out value="${vaga.codigo}"/></td>
									<td><c:out value="${vaga.tipoVagaBean.nome}"/></td>
									<td><c:out value="${vaga.altura}"/></td>
									<td><c:out value="${vaga.largura}"/></td>
									<td>
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController?acao=DETALHAR&id=<c:out value='${vaga.id}'/>&idEstacionamento=<c:out value='${idEstacionamento}'/>"><span class="glyphicon glyphicon-pencil"></span></a>
										&nbsp;
										<a href="#" onclick="javascript:excluir('<c:out value='${vaga.id}'/>','<c:out value='${idEstacionamento}'/>')"><span class="glyphicon glyphicon-trash"></span></a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<br /><br />
					<input type="button" name="botaoPrepararInserir" id="botaoPrepararInserir" value="INSERIR" class="btn btn-success"/>
					<input type="button" name="botaoVoltarEntrada" id="botaoVoltarListagemAdministradorEstacionamento" value="VOLTAR" class="btn btn-primary"/>
				</form>
				</fieldset>
			</div>
		</div>
		
	</body>
</html>