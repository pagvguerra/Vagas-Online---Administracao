$(function() {

    $("#botaoAlterar").on('click', function() {

		var form	=	$("#formAlterar");
		var nome	=	$("#nome").val().trim();
		var preco	=	$("#preco").val().trim();
		var regra	=	/^[0-99]+$/;

		if(nome === undefined || nome == null || nome == '') {
			alert('Preencha o Tipo da Vaga');
			return false;
		} 
		
		if(preco === undefined || preco == null || preco == '') {
			alert('Preencha o preco');
			return false;
		}
				
		if (!preco.match(regra)) {
			alert("Preencha o preco somente com valores inteiros e positivos");
			return false;
		}

		form.submit();
		
	});
	
	$("#botaoInserir").on('click', function() {
		
		var form	=	$("#formCadastrarTipoVagaEstacionamento");
		var nome	=	$("#nome").val().trim();
		var preco	=	$("#preco").val().trim();
		var regra	=	/^[0-99]+$/;

		if(nome === undefined || nome == null || nome == '') {
			alert('Preencha o Tipo da Vaga');
			return false;
		} 
		
		if(preco === undefined || preco == null || preco == '') {
			alert('Preencha o preco');
			return false;
		}
		
		if (!preco.match(regra)) {
			alert("Preencha o preco somente com valores inteiros e positivos");
			return false;
		}
		  
		form.submit();
	
	});
	
	$("#botaoPrepararInserir").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoVagaController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});
	
	$("#botaoVoltarListagem").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoVagaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

	$("#botaoCadastrarVaga").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});

	$("#botaoVoltarListagemVaga").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/VagaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

	$("#botaoVoltar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoVagaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
});