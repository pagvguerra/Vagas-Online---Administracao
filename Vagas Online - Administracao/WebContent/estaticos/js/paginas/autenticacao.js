$(function() {
	
	$("#enviar").on('click', function() {

		var form 	=	$("#formularioAutenticacao");
		var login 	=	$("#login").val().trim();
		var senha 	=	$("#senha").val().trim();
			
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
		 
		form.submit();
		
	});
	
	
	$("#popRedefinirSenha").on('click', function() {

		var URL		=	"http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AutenticacaoController?acao=FORM_REDEFINIR_SENHA";
		
		var width 	=	600;
		var height 	=	700;
		 
		var left	=	500;
		var top		=	50;
		 
		window.open(URL,'janela', 'width='+width+', height='+height+', top='+top+', left='+left+', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
	});		

	
	$("#fechar").on('click', function() {
		window.close();
	});	
	
	
	$("#voltar").on('click', function() {
		history.back();
	});
	
	
	$("#redefinirSenha").on('click', function() {

		var login		=	$("#login").val().trim();
		var senha		=	$("#senha").val().trim();
		var resposta1	=	$("#resposta1").val().trim();
		var resposta2	=	$("#resposta2").val().trim();
		var resposta3	=	$("#resposta3").val().trim();
		var resposta4	=	$("#resposta4").val().trim();
		
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

		if(resposta1 === undefined || resposta1 == null || resposta1 === '') {
			alert('Preencha a Pergunta 1');
			$("#resposta1").focus();
			return;
		}
		
		if(resposta2 === undefined || resposta2 == null || resposta2 === '') {
			alert('Preencha a Pergunta 2');
			$("#resposta2").focus();
			return;
		}
		
		if(resposta3 === undefined || resposta3 == null || resposta3 === '') {
			alert('Preencha a Pergunta 3');
			$("#resposta3").focus();
			return;
		}
		
		if(resposta4 === undefined || resposta4 == null || resposta4 === '') {
			alert('Preencha a Pergunta 4');
			$("#resposta4").focus();
			return;
		}
				
		$('form').attr({
			action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AutenticacaoController',
			method : 'post'
		});

		$("#acao").val('REDEFINIR_SENHA');
		$('form').submit();

	});		


	$("#cadastroAdmEstacionamento").on('click', function() {

		var URL		=	"http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController?acao=PREPARAR_INSERIR";
		
		var width 	=	500;
		var height 	=	700;
		 
		var left	=	500;
		var top		=	50;
		 
		window.open(URL,'janela', 'width='+width+', height='+height+', top='+top+', left='+left+', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
	});

	
} );