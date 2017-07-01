$(function() {

	var caminhoAjaxLoad = "http://localhost:8080/Vagas Online - Administracao/WebContent/estaticos/images/icon_loading.gif";

	//funções para ajax da tabela de endereços do estacionamento
	$("select[name=pais]").on('change', function() {
		$("#divBairros").html("<select name='bairro' id='idBairros' class='form-control'><option value='0'>SELECIONE</option></select>");
		$("#divCidades").html("<select name='cidade' id='idCidades' class='form-control'><option value='0'>SELECIONE</option></select>");
		carregaEstados();
	});

	$("select[name=estado]").on('change', function() {
		carregaCidades();
	});

	$("select[name=cidade]").on('change', function() {
		carregaBairros();
	});

	function carregaBairros() {
		$("#divBairros").load($.post("http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AjaxController",
			{
				acao 		: "BUSCA_BAIRROS",
				idCidade 	: $('#idCidade :selected').val()
			},
			function(retorno) {

				var options =	"";
				var lista 	=	retorno.itens;
				for (var i = 0; i < lista.length; i++) {
					options	+=	"<option value='" + lista[i].id +"'>" + lista[i].nome + "</option>";
				}
				
				var select	=	"<select name='bairro' id='idBairro' class='form-control'>";
				select 		+=	"<option value='0'>SELECIONE</option>";
				select 		+=	options;
				select 		+=	"</select>";

				$("#divBairros").html(select);
			
			}, "json"),
			$("#divBairros").html(caminhoAjaxLoad));
	}

	function carregaCidades() {
		$("#divCidades").load($.post("http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AjaxController",
			{
				acao 		: "BUSCA_CIDADES",
				idEstado 	: $('#idEstado :selected').val()
			},
			function(retorno) {
				
				var options =	"";
				var lista 	=	retorno.itens;
				for (var i = 0; i < lista.length; i++) {
					options	+=	"<option value='" + lista[i].id +"'>" + lista[i].nome + "</option>";
				}
				
				var select	=	"<select name='cidade' id='idCidade' class='form-control'>";
				select 		+=	"<option value='0'>SELECIONE</option>";
				select 		+=	options;
				select 		+=	"</select>";

				$("#divCidades").html(select);
				$("#idCidade").change(carregaBairros);
			
			}, "json"),
			$("#divCidades").html(caminhoAjaxLoad));
	}

	function carregaEstados() {
		$("#divEstados").load($.post("http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/AjaxController",
			{
				acao 	: "BUSCA_ESTADOS",
				idPais 	: $('#idPais :selected').val()
			},
			function(retorno) {
				
				var options =	"";
				var lista 	=	retorno.itens;
				for (var i = 0; i < lista.length; i++) {
					options	+=	"<option value='" + lista[i].id +"'>" + lista[i].nome + "</option>";
				}
				
				var select	=	"<select name='estado' id='idEstado' class='form-control''>";
				select 		+=	"<option value='0'>SELECIONE</option>";
				select 		+=	options;
				select 		+=	"</select>";

				$("#divEstados").html(select);
				$("#idEstado").change(carregaCidades);
				
			}, "json"),
			$("#divEstados").html(caminhoAjaxLoad));
	}
	
	$("#cadastrarEstacionamento").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});
	
	$("#botaoInserir").on('click', function() {
		
		var nomeFantasia		=	$("#nomeFantasia").val().trim();
		var razaoSocial			=	$("#razaoSocial").val().trim();
		var cnpj				=	$("#cnpj").val().trim();
		var	pais				=	$("#idPais option:selected").val();
		var	estado				=	$("#idEstado option:selected").val();
		var	cidade				=	$("#idCidade option:selected").val();
		var	bairro				=	$("#idBairro option:selected").val();
		var cep					=	$("#cep").val().trim();
		var	tipoLogradouro		=	$("#idTipoLogradouro option:selected").val();
		var nomeLogradouro		=	$("#nomeLogradouro").val().trim();
		var numero				=	$("#numero").val().trim();
		var regra				=	/^[0-99]+$/;

		if(nomeFantasia === undefined || nomeFantasia == null || nomeFantasia == '') {
			alert('Preencha o Nome Fantasia');
			return false;
		} 

		if(razaoSocial === undefined || razaoSocial == null || razaoSocial == '') {
			alert('Preencha a Razao Social');
			return false;
		} 

		if(cnpj === undefined || cnpj == null || cnpj == '') {
			alert('Preencha o CNPJ');
			return false;
		} 
						
		if(pais == 0) {
			alert('Escolha o Pais');
			return false;
		}
		
		if(estado == 0) {
			alert('Escolha o Estado');
			return false;
		}
		
		if(cidade == 0) {
			alert('Escolha a Cidade');
			return false;
		}
		
		if(bairro == 0) {
			alert('Escolha o Bairro');
			return false;
		}
		
		if(cep === undefined || cep == null || cep == '') {
			alert('Preencha o CEP');
			return false;
		}

		if(tipoLogradouro == 0) {
			alert('Escolha o Tipo de Logradouro');
			return false;
		}

		if(nomeLogradouro === undefined || nomeLogradouro == null || nomeLogradouro == '') {
			alert('Preencha o Nome do Logradouro');
			return false;
		}

		if(numero != '') {
			if (!numero.match(regra)) {
				alert("Preencha o Numero somente com valores inteiros e positivos");
				return false;
			}
		}

		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('INSERIR');
		 $('form').submit();
	});

	$("#botaoAlterar").on('click', function() {
		
		var nomeFantasia		=	$("#nomeFantasia").val().trim();
		var razaoSocial			=	$("#razaoSocial").val().trim();
		var cnpj				=	$("#cnpj").val().trim();
		var	pais				=	$("#pais option:selected").val();
		var	estado				=	$("#estado option:selected").val();
		var	cidade				=	$("#cidade option:selected").val();
		var	bairro				=	$("#bairro option:selected").val();
		var cep					=	$("#cep").val().trim();
		var	tipoLogradouro		=	$("#tipoLogradouro option:selected").val();
		var nomeLogradouro		=	$("#nomeLogradouro").val().trim();
		var numero				=	$("#numero").val().trim();
		var regra				=	/^[0-99]+$/;

		if(nomeFantasia === undefined || nomeFantasia == null || nomeFantasia == '') {
			alert('Preencha o Nome Fantasia');
			return false;
		} 

		if(razaoSocial === undefined || razaoSocial == null || razaoSocial == '') {
			alert('Preencha a Razao Social');
			return false;
		} 

		if(cnpj === undefined || cnpj == null || cnpj == '') {
			alert('Preencha o CNPJ');
			return false;
		} 
		
		if(pais == 0) {
			alert('Escolha o Pais');
			return false;
		}
		
		if(estado == 0) {
			alert('Escolha o Estado');
			return false;
		}
		
		if(cidade == 0) {
			alert('Escolha a Cidade');
			return false;
		}
		
		if(bairro == 0) {
			alert('Escolha o Bairro');
			return false;
		}
		
		if(cep === undefined || cep == null || cep == '') {
			alert('Preencha o CEP');
			return false;
		}

		if(tipoLogradouro == 0) {
			alert('Escolha o Tipo de Logradouro');
			return false;
		}

		if(nomeLogradouro === undefined || nomeLogradouro == null || nomeLogradouro == '') {
			alert('Preencha o Nome do Logradouro');
			return false;
		}

		if(numero != '') {
			if (!numero.match(regra)) {
				alert("Preencha o Numero somente com valores inteiros e positivos");
				return false;
			}
		}
		
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('ALTERAR');
		 $('form').submit();
	});
	
	$("#botaoVoltar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
	$("#botaoVoltarListagem").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/EstacionamentoOnlineEntradaAdministradorEstacionamento/servlet/EstacionamentoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
} );