<script type="text/javascript">

$(function() {
	
	$("#desativarConta").on('click', function() {
		
		var desativar = confirm('Tem certeza que deseja desativar sua conta?');

		if(desativar) {
			
			$('form').attr({
	 			action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController?id=${usuario.id}',
			    method : 'post'
			 });
			 $("#acao").val('DESATIVAR');
			 $('form').submit();
		}
		
	});
	
	$("#popAlterarAdministradorEstacionamento").on('click', function() {

		var URL = "http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController?acao=DETALHAR_ADMINISTRADOR_ESTACIONAMENTO&id=${usuario.id}";
		
		var width = 550;
		var height = 650;
		 
		var left = 500;
		var top = 50;
		 
		window.open(URL,'janela', 'width='+width+', height='+height+', top='+top+', left='+left+', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
	});		
	
	
} );

</script>

<table width="100%">
	<tr>
		<td>
			<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController?acao=LISTAR_TODOS">
				<img src="${pageContext.request.contextPath}/estaticos/images/logotipo.png" width="160px;" height="150px;">
			</a>
		</td>
		<td align="center"><b>Login do Administrador..:</b>&nbsp;${usuario.login}</td>
		<td align="right">
			<form>
				
				<a href="#" id="popAlterarAdministradorEstacionamento"><img src="${pageContext.request.contextPath}/estaticos/images/icon_alterar_conta.png" title="Alterar Administrador do Estacionamento" width="40" height="40"></a>
				&nbsp;
				<a href="#" id="desativarConta"><img src="${pageContext.request.contextPath}/estaticos/images/icon_excluir_conta.png" title="Desativar Conta do Administrador do Estacionamento" width="40" height="40"></a>
				&nbsp;
				<a href="http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AutenticacaoController?acao=LOGOUT"><img src="${pageContext.request.contextPath}/estaticos/images/icon_porta_sair.png" width="40" height="40" title="Logout" ></a>
			</form>
		</td>
	</tr>
</table>
<br /><br />