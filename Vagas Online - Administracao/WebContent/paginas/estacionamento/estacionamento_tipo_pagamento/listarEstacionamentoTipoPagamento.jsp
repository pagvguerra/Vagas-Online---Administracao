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
		<script src="${jspaginas}/estacionamento_tipo_pagamento.js" ></script>
		<script>
			function excluir(idTipoPagamento, idEstacionamento) {
				var msgExclusao = "Tem certeza que deseja excluir o tipo de pagamento?";
				if (confirm(msgExclusao)) {
					var form = document.getElementById('form');
					form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoTipoPagamentoController?acao=EXCLUIR&idTipoPagamento=' + idTipoPagamento+ '&idEstacionamento=' + idEstacionamento;
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
				<B>TELA DE GERENCIAMENTO DE TIPO DE PAGAMENTO DO ESTACIONAMENTO</B></legend>
				<form id="form" name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Tipo de Pagamento</td>
							<th>Ações</th>
						</tr>
						</thead>
						<c:if test="${empty listaEstacionamentoTipoPagamento}">
							<tr align="center">
								<td colspan="6">Não existem tipos de pagamentos cadastrados para este estacionamento</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaEstacionamentoTipoPagamento}">
							<c:forEach var="tp" items="${listaEstacionamentoTipoPagamento}" >
								<tr>
									<td>
										<c:if test="${tp.tipoPagamentoBean.nome == 'dinheiro' || tp.tipoPagamentoBean.nome == 'DINHEIRO'}">
											<img src="${pageContext.request.contextPath}/estaticos/images/dinheiro.png" title="dinheiro">										
										</c:if>	
										<c:if test="${tp.tipoPagamentoBean.nome == 'visa' || tp.tipoPagamentoBean.nome == 'VISA'}">
											<img src="${pageContext.request.contextPath}/estaticos/images/visa.png" title="visa">										
										</c:if>	
										<c:if test="${tp.tipoPagamentoBean.nome == 'master' || tp.tipoPagamentoBean.nome == 'MASTER'}">
											<img src="${pageContext.request.contextPath}/estaticos/images/master.png" title="master">
										</c:if>	
										<c:if test="${tp.tipoPagamentoBean.nome == 'amex' || tp.tipoPagamentoBean.nome == 'AMEX'}">
											<img src="${pageContext.request.contextPath}/estaticos/images/amex.png" title="amex">										
										</c:if>	
									</td>
									<td>
										<c:if test="${tp.tipoPagamentoBean.id != 1}">
											<a href="#" onclick="javascript:excluir('<c:out value='${tp.tipoPagamentoBean.id}'/>','<c:out value='${idEstacionamento}'/>')"><span class="glyphicon glyphicon-trash"></span></a>
										</c:if>
										<c:if test="${tp.tipoPagamentoBean.id == 1}">
											-
										</c:if>			
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<br /><br />
					<c:if test="${estacionamentoPossuiTodosOsTiposDePagamento == false}">
						<input type="button" name="botaoPrepararInserir" id="botaoPrepararInserir" value="INSERIR" class="btn btn-success"/>
					</c:if>
					<input type="button" name="botaoVoltarListagemAdministradorEstacionamento" id="botaoVoltarListagemAdministradorEstacionamento" value="VOLTAR" class="btn btn-primary"/>
				</form>
				</fieldset>
			</div>
		</div>
	</body>
</html>