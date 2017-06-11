$(function() {

	$("#botaoAlterar").on('click', function() {
		var nome = $("#nome").val();
		var cpf = $("#cpf").val();
		var rg = $("#rg").val();
		var email = $("#email").val();
		var resposta1 = $("#resposta1").val();
		var resposta2 = $("#resposta2").val();
		var resposta3 = $("#resposta3").val();
		var resposta4 = $("#resposta4").val();
		
		if(nome == null || nome == '') {
			alert('Preencha o Nome');
			return false;
		} 

		if(nome.length < 10){
			alert('Digite o Nome com pelo menos 10 caracteres');
			return false;
		}

		if(cpf == null || cpf == '') {
			alert('Preencha o CPF');
			return false;
		} 
	
		if(!validarCPF(cpf)) {
			alert('Digite um CPF valido');
			return false;
		}
		
		if(rg == null || rg == '') {
			alert('Preencha o RG');
			return false;
		} 
		
		if(email == null || email == '') {
			alert('Preencha o Email');
			return false;
		} 

		//validando email
		er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2,3}/; 

		if( !er.exec(email) ) {
			alert('Digite um Email valido!');
			return false;
		}
		
		if(resposta1 == null || resposta1 == '') {
			alert('Preencha a Pergunta 1');
			return false;
		}
		
		if(resposta2 == null || resposta2 == '') {
			alert('Preencha a Pergunta 2');
			return false;
		}
		
		if(resposta3 == null || resposta3 == '') {
			alert('Preencha a Pergunta 3');
			return false;
		}
		
		if(resposta4 == null || resposta4 == '') {
			alert('Preencha a Pergunta 4');
			return false;
		}
			
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('ALTERAR');
		 $('form').submit();
		
	});
	
	$("#botaoInserir").on('click', function() {
		
		var form = $("#formCadastrarAdministradorEstacionamento");
		var login = $("#login").val();
		var senha = $("#senha").val();
		var nome = $("#nome").val();
		var cpf = $("#cpf").val();
		var rg = $("#rg").val();
		var email = $("#email").val();
		var resposta1 = $("#resposta1").val();
		var resposta2 = $("#resposta2").val();
		var resposta3 = $("#resposta3").val();
		var resposta4 = $("#resposta4").val();
		
		if(login == null || login == '') {
			alert('Preencha o Login');
			return false;
		} 
		
		if(login.length < 6) {
			alert('Digite um Login com pelo menos 6 caracteres');
			return false;
		}
		
		if(senha == null || senha == '') {
			alert('Preencha a Senha');
			return false;
		} 
		
		if(senha.length < 6) {
			alert('Digite uma Senha com pelo menos 6 caracteres');
			return false;
		}
		
		if(nome == null || nome == '') {
			alert('Preencha o Nome');
			return false;
		} 

		if(nome.length < 10){
			alert('Digite o Nome com pelo menos 10 caracteres');
			return false;
		}

		if(cpf == null || cpf == '') {
			alert('Preencha o CPF');
			return false;
		} 
	
		if(!validarCPF(cpf)) {
			alert('Digite um CPF valido');
			return false;
		}
		
		if(rg == null || rg == '') {
			alert('Preencha o RG');
			return false;
		} 
		
		if(email == null || email == '') {
			alert('Preencha o Email');
			return false;
		} 

		//validando email
		er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2,3}/; 

		if( !er.exec(email) ) {
			alert('Digite um Email valido!');
			return false;
		} 
		
		if(resposta1 == null || resposta1 == '') {
			alert('Preencha a Pergunta 1');
			return false;
		}
		
		if(resposta2 == null || resposta2 == '') {
			alert('Preencha a Pergunta 2');
			return false;
		}
		
		if(resposta3 == null || resposta3 == '') {
			alert('Preencha a Pergunta 3');
			return false;
		}
		
		if(resposta4 == null || resposta4 == '') {
			alert('Preencha a Pergunta 4');
			return false;
		}
		
		form.submit();
		
	});
	
	function isEmailValido(email){
		er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2,3}/; 
		if( !er.exec(email) )
		{
			alert('Digite um Email valido!');
			return false;
		}
	}
	
	function validarCPF(cpf) {
		
		cpf = cpf.replace(/[^\d]+/g,'');
		
		if(cpf == '') {
			return false;
		}

		if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999") {
			return false;
		}

		// Valida 1o digito
		add = 0;
		for (i=0; i < 9; i ++)
			add += parseInt(cpf.charAt(i)) * (10 - i);
			rev = 11 - (add % 11);
			if (rev == 10 || rev == 11) {
				rev = 0;
			}
			if (rev != parseInt(cpf.charAt(9))) {
				return false;
			}
			
			// Valida 2o digito
			add = 0;
			for (i = 0; i < 10; i ++)
				add += parseInt(cpf.charAt(i)) * (11 - i);

			rev = 11 - (add % 11);

			if (rev == 10 || rev == 11) {
				rev = 0;
			}

			if (rev != parseInt(cpf.charAt(10))) {
				return false;
			}
	
		return true;
	}
	
	$("#botaoPrepararInserir").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});
	
	$("#botaoFechar").on('click', function() {
		window.close();
	});
	
	$("#botaoVoltarListagem").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AdministradorEstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

	$("#botaoVoltarEntrada").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AutenticacaoController',
		    method : 'post'
		 });
		 $("#acao").val('ENTRADA');
		 $('form').submit();
	});
	
});