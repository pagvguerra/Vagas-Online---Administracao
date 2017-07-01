$(function() {

	$("#botaoAlterar").on('click', function() {

		var form		=	$("#formAlterar");
		var codigo		=	$("#codigo").val().trim();
		var largura 	=	$("#largura").val().trim();
		var altura 		=	$("#altura").val().trim();
		var comprimento	=	$("#comprimento").val().trim();
		var regra		=	/^[0-99]+$/;

		if(codigo === undefined || codigo == null || codigo == '') {
			alert('Preencha o codigo');
			return false;
		} 
		
		if(codigo.length < 2) {
			alert('Digite um código com pelo menos 2 caracteres');
			return false;
		}
		
		if(largura === undefined || largura == null || largura == '') {
			alert('Preencha a Largura');
			return false;
		}
		
		if (!largura.match(regra)) {
			alert("Preencha a largura somente com valores inteiros e positivos");
			return false;
		}
		
		if(altura === undefined || altura == null || altura == '') {
			alert('Preencha a altura');
			return false;
		} 

		if (!altura.match(regra)) {
			alert("Preencha a altura somente com valores inteiros e positivos");
			return false;
		}

		if(comprimento === undefined || comprimento == null || comprimento == '') {
			alert('Preencha o comprimento');
			return false;
		} 

		if (!comprimento.match(regra)) {
			alert("Preencha o comprimento somente com valores inteiros e positivos");
			return false;
		}

		form.submit();
		
	});
	
	$("#botaoInserir").on('click', function() {
		
		var form	=	$("#formCadastrarVagaEstacionamento");
		var codigo	=	$("#codigo").val().trim();
		var largura =	$("#largura").val().trim();
		var altura	=	$("#altura").val().trim();
		var comprimento	=	$("#comprimento").val().trim();
		var regra	=	/^[0-99]+$/;

		if(codigo === undefined || codigo == null || codigo == '') {
			alert('Preencha o codigo');
			return false;
		} 
		
		if(codigo.length < 2) {
			alert('Digite um código com pelo menos 2 caracteres');
			return false;
		}
		
		if(largura === undefined || largura == null || largura == '') {
			alert('Preencha a Largura');
			return false;
		}
		
		if (!largura.match(regra)) {
			alert("Preencha a largura somente com valores inteiros e positivos");
			return false;
		}
		
		if(altura === undefined || altura == null || altura == '') {
			alert('Preencha a altura');
			return false;
		} 

		if (!altura.match(regra)) {
			alert("Preencha a altura somente com valores inteiros e positivos");
			return false;
		}
		
		if(comprimento === undefined || comprimento == null || comprimento == '') {
			alert('Preencha o comprimento');
			return false;
		} 

		if (!comprimento.match(regra)) {
			alert("Preencha o comprimento somente com valores inteiros e positivos");
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