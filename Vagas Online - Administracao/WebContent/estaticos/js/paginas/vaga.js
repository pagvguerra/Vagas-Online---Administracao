$(function() {

	$("#botaoAlterar").on('click', function() {

		var form = $("#formAlterar");
		var codigo = $("#codigo").val();
		var largura = $("#largura").val();
		var altura = $("#altura").val();

		if(codigo == null || codigo == '') {
			alert('Preencha o codigo');
			return false;
		} 
		
		if(codigo.length < 2) {
			alert('Digite um código com pelo menos 2 caracteres');
			return false;
		}
		
		if(largura == null || largura == '') {
			alert('Preencha a Largura');
			return false;
		} 
		
		if(altura == null || altura == '') {
			alert('Preencha a altura');
			return false;
		} 
		
		form.submit();
		
	});
	
	$("#botaoInserir").on('click', function() {
		
		var form = $("#formCadastrarVagaEstacionamento");
		var codigo = $("#codigo").val();
		var largura = $("#largura").val();
		var altura = $("#altura").val();

		if(codigo == null || codigo == '') {
			alert('Preencha o codigo');
			return false;
		} 
		
		if(codigo.length < 2) {
			alert('Digite um código com pelo menos 2 caracteres');
			return false;
		}
		
		if(largura == null || largura == '') {
			alert('Preencha a Largura');
			return false;
		} 
		
		if(altura == null || altura == '') {
			alert('Preencha a altura');
			return false;
		} 

		form.submit();
	
	});
	
	
	$("#botaoPrepararInserir").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});
	
	$("#botaoFechar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
	$("#botaoVoltar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
	$("#botaoVoltarListagem").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

	$("#botaoVoltarListagemAdministradorEstacionamento").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

	
	$("#botaoGerenciarTipoVaga").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoVagaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
});