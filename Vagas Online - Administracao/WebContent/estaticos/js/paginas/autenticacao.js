$(function() {
	
	$("#enviar").on('click', function() {

		var form  = $("#formularioAutenticacao");
		var login = $("#login").val().trim();
		var senha = $("#senha").val().trim();
			
		if(login === undefined || login == null || login === '') {
			alert('Preencha o Login');
			$("#login").focus();
			return;
		}

		if(senha === undefined || senha == null || senha === '') {
			alert('Preencha a Senha');
			$("#senha").focus();
			return;
		}
		 
		form.submit();
		
	});
	
	
	$("#popRedefinirSenha").on('click', function() {

		var URL = "http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AutenticacaoController?acao=FORM_REDEFINIR_SENHA";
		
		var width = 600;
		var height = 700;
		 
		var left = 500;
		var top = 50;
		 
		window.open(URL,'janela', 'width='+width+', height='+height+', top='+top+', left='+left+', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
	});		

	
	$("#fechar").on('click', function() {
		window.close();
	});	
	
	
	$("#voltar").on('click', function() {
		history.back();
	});
	
	
	$("#redefinirSenha").on('click', function() {

		var login = $("#login").val().trim();
		var senha = $("#senha").val().trim();
			
		if(login === undefined || login == null || login === '') {
			alert('Preencha o Login');
			$("#login").focus();
			return;
		}

		if(login.length < 6) {
			alert('Digite um Login com pelo menos 6 caracteres');
			return false;
		}
		
		if(senha === undefined || senha == null || senha === '') {
			alert('Preencha a Senha');
			$("#senha").focus();
			return;
		}
		
		if(senha.length < 6) {
			alert('Digite uma Senha com pelo menos 6 caracteres');
			return false;
		}
		
		$('form').attr({
			action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AutenticacaoController',
			method : 'post'
		});

		$("#acao").val('REDEFINIR_SENHA');
		$('form').submit();

	});		


	$("#cadastroAdmEstacionamento").on('click', function() {

		var URL = "http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController?acao=PREPARAR_INSERIR";
		
		var width = 500;
		var height = 700;
		 
		var left = 500;
		var top = 50;
		 
		window.open(URL,'janela', 'width='+width+', height='+height+', top='+top+', left='+left+', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
	});		

	
} );