<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>TELA DE TIPO LISTAGEM DE TIPO DE VAGA</title>		
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/tipo_vaga.js" ></script>
		<script>
			function excluir(id, idEstacionamento) {
				var msgExclusao = "Tem certeza que deseja excluir o tipo de vaga?";
				if (confirm(msgExclusao)) {
					var form = document.getElementById('form');
					form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoVagaController?acao=EXCLUIR&id=' + id+ '&idEstacionamento=' + idEstacionamento;
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
				<%@include file="../../../includes/cabecalho.jsp" %> 
				<fieldset>
					<legend>
				<B>TELA DE GERENCIAMENTO DE TIPO DE VAGAS DO SISTEMA</B></legend>
				<br>
				<c:if test="${not empty mensagemImpossibilidadeExclusaoDeTipoDeVaga}">
					<div class="form-group">
						<font color="red" size="5"><b>${mensagemImpossibilidadeExclusaoDeTipoDeVaga}<b></b></font>
					</div>
					<br>
				</c:if>
				<form id="form" name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Tipo da Vaga</td>
							<th>Preço</td>
							<th>Ações</th>
						</tr>
						</thead>
						<c:if test="${empty listaTipoVagas}">
							<tr align="center">
								<td colspan="6">Não existem tipo de vagas cadastradas</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaTipoVagas}">
							<c:forEach var="tipoVaga" items="${listaTipoVagas}" >
								<tr>
									<td><c:out value="${tipoVaga.nome}"/></td>
									<td>R$ <c:out value="${tipoVaga.preco}"/></td>
									<td>
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoVagaController?acao=DETALHAR&id=<c:out value='${tipoVaga.id}'/>&idEstacionamento=<c:out value='${idEstacionamento}'/>"><span class="glyphicon glyphicon-pencil"></span></a>
										&nbsp;
										<a href="#" onclick="javascript:excluir('<c:out value='${tipoVaga.id}'/>','<c:out value='${idEstacionamento}'/>')"><span class="glyphicon glyphicon-trash"></span></a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<br /><br />
					<input type="button" name="botaoPrepararInserir" id="botaoPrepararInserir" value="INSERIR" class="btn btn-success"/>
					<input type="button" name="botaoVoltarListagemVaga" id="botaoVoltarListagemVaga" value="VOLTAR" class="btn btn-primary"/>
				</form>
				</fieldset>
			</div>
		</div>
	</body>
</html>