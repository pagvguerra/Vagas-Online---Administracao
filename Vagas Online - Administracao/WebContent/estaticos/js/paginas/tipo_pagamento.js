$(function() {

	$("#botaoPrepararInserir").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoPagamentoController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});

	$("#botaoCadastrar").on('click', function() {
		var tipoPagamentoVisa 		=	$('#tipoPagamentoVisa').is(':checked');
		var tipoPagamentoMaster 	=	$('#tipoPagamentoMaster').is(':checked');
		var tipoPagamentoAmex 		=	$('#tipoPagamentoAmex').is(':checked');
		
		if(!tipoPagamentoVisa && !tipoPagamentoMaster && !tipoPagamentoAmex) {
			alert("Deve ser selecionado um tipo de Pagamento (Visa, Master ou Amex).");
		} else {
			$('form').attr({
			 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoPagamentoController',
			    method : 'post'
			 });
			 $("#acao").val('INSERIR');
			 $('form').submit();
		}
	});

	$("#botaoAlterar").on('click', function() {
		var tipoPagamentoVisa 		=	$('#tipoPagamentoVisa').is(':checked');
		var tipoPagamentoMaster 	=	$('#tipoPagamentoMaster').is(':checked');
		var tipoPagamentoAmex 		=	$('#tipoPagamentoAmex').is(':checked');
		
		if(!tipoPagamentoVisa && !tipoPagamentoMaster && !tipoPagamentoAmex) {
			alert("Deve ser selecionado um tipo de Pagamento (Visa, Master ou Amex).");
		} else {
			$('form').attr({
				action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoPagamentoController',
				method : 'post'
			});
			$("#acao").val('ALTERAR');
			$('form').submit();
		}	
	});
	
	$("#botaoVoltar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/TipoPagamentoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

});	