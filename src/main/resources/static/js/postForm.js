$(document).ready(
			function(){
				
				$("#formPapel").submit(function(event){
					event.preventDefault();
					submeterPapel();
				});			
				
				function submeterPapel(){
					var valoresCampos={
							nome : $("#nome").val()
					}
// 					alert(valoresCampos.nome);
					//console.log(valoresCampos);
					$.ajax({
						type:"POST",
						contentType:"application/json",
						url: "/comapp/salvarPapel",
						dataType:'json',
						data : JSON.stringify(valoresCampos),
						success: function(result){
							//alert("asd");
							if(result.id!=null){
								//alert("salvo com sucesso "+result.nome);
								$("#mensagem").html("<div class=\"alert alert-success\" role=\"alert\"> Opaa, deu certo! </div>");
								limparCampo();
							}else{
								//alert("Erro ao salvar "+result.nome);
								$("#mensagem").html("Erro ao salvar");
							}
						},
						error : function(e){
							alert("ERROR: "+e.nome);
						}
					})
				}
				
				function limparCampo(){
					$("#nome").val("");
				}
			})	