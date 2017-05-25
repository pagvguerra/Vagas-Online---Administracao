<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js/"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="imagens" value="${pageContext.request.contextPath}/estaticos/images"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/estacionamento.js" ></script>
		<script>
			function excluir(idEstacionamento) {
				var msgExclusao = "Tem certeza que deseja excluir o estacionamento?";
				if (confirm(msgExclusao)) {
					var form = document.getElementById('form');
					form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController?acao=EXCLUIR&idEstacionamento=' + idEstacionamento;
					form.method	= 'post';
					form.submit();
				}
			}
			
			function verEndereco(idEstacionamento) {
				var URL = "http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController?acao=BUSCAR_ENDERECO_ESTACIONAMENTO&idEstacionamento=" + idEstacionamento;
				
				var width = 600;
				var height = 400;
				 
				var left = 450;
				var top = 100;
				 
				window.open(URL,'janela', 'width='+width+', height='+height+', top='+top+', left='+left+', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
			}
		</script>
	</head>
	<body>
		<br/><br/>
	</body>
		<div class="container">
			<div class="col-sm-12">
				<%@include file="../includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>HOME - TELA DE LISTAGEM DO ESTACIONAMENTO</B></legend>
				<br><br>
				<form id="form" name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Status</th>
							<th>Nome Fantasia</td>
							<th>Razão Social</td>
							<th>Endereço</td>
							<th>Total de Funcionários</th>
							<th>Total de Vagas</th>
							<th><center>Ações</center></th>
						</tr>
						</thead>
						<c:if test="${empty listaEstacionamento}">
							<tr align="center">
								<td colspan="10">Não existem estacionamentos cadastrados</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaEstacionamento}">
							<c:forEach var="estacionamento" items="${listaEstacionamento}" >
								<tr>
									<td>
										<center>
											<c:if test="${estacionamento.statusBean.id == 0}"><img src="${imagens}/bola_vermelha.jpg" title="PENDENTE DE CADASTRO DE FUNCIONÁRIO E VAGA" width="19" height="19"></c:if>
											<c:if test="${estacionamento.statusBean.id == 1}">
												<c:if test="${estacionamento.quantidadeFuncionarios == 0}">
													<img src="${imagens}/bola_amarela.jpg" title="PENDENTE DE CADASTRO DE FUNCIONÁRIO" width="19" height="19">
												</c:if>
												<c:if test="${estacionamento.quantidadeVagas == 0}">
													<img src="${imagens}/bola_amarela.jpg" title="PENDENTE DE CADASTRO DE VAGA" width="19" height="19">
												</c:if>										
											</c:if>
											<c:if test="${estacionamento.statusBean.id == 2}"><img src="${imagens}/bola_verde.jpg" title="ESTACIONAMENTO ATIVO" width="19" height="19"></c:if>
										</center>
									</td>
									<td><center><c:out value="${estacionamento.nomeFantasia}"/></center></td>
									<td><center><c:out value="${estacionamento.razaoSocial}"/></center></td>
									<td><center><a href="#" onclick="javascript:verEndereco('<c:out value='${estacionamento.id}'/>')"><span class="glyphicon glyphicon-list-alt" title="VER ENDEREÇO"></span></a></center></td>
									<td><center><c:out value="${estacionamento.quantidadeFuncionarios}"/></center></td>
									<td><center><c:out value="${estacionamento.quantidadeVagas}"/></center></td>
									<td widht="20%">
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/FuncionarioController?acao=LISTAR_TODOS&idEstacionamento=<c:out value='${estacionamento.id}'/>"><span class="glyphicon glyphicon-user" title="GERENCIAR FUNCIONÁRIOS"></span></a>
										&nbsp;
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController?acao=LISTAR_TODOS&idEstacionamento=<c:out value='${estacionamento.id}'/>"><span class="glyphicon glyphicon-th-list" title="GERENCIAR VAGAS"></span></a>
										&nbsp;
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoTipoPagamentoController?acao=LISTAR_TODOS&idEstacionamento=<c:out value='${estacionamento.id}'/>"><span class="glyphicon glyphicon-usd" title="GERENCIAR PAGAMENTO"></span></a>
										&nbsp;
										<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController?acao=DETALHAR_ESTACIONAMENTO&idEstacionamento=<c:out value='${estacionamento.id}'/>"><span class="glyphicon glyphicon-pencil" title="ALTERAR ESTACIONAMENTO"></span></a>
										&nbsp;
										<a href="#" onclick="javascript:excluir('<c:out value='${estacionamento.id}'/>')"><span class="glyphicon glyphicon-trash" title="EXCLUIR ESTACIONAMENTO"></span></a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<br /><br />
					<div class="form-group">
						<a href="#" id="cadastrarEstacionamento"><span class="glyphicon glyphicon-home"></span>&nbsp;Criar Novo Estacionamento</a>
					</div>
				</form>
			</fieldset>
			<br><br>
			</div>
		</div>	
</html>