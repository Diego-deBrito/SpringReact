package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ProdutoServico {

	@Autowired // para ter acesso a ações SQL
	private ProdutoRepositorio pr;
	
	@Autowired // para resposta em caso de faltar algo
	private RespostaModelo rm;

	//Metodo para listar todos os produtos 
	public Iterable<ProdutoModelo> listar(){
			return pr.findAll();
	}
	//Metodo para cadastrar ou alterar produtos
	public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao){
		if(pm.getNome().equals("")){
			rm.setMensagem("O nome do produto é obrigatório!");
			return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
		}else if(pm.getMarca().equals("")){
			rm.setMensagem("O nome da marca é obrigatório");
			return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
		}else{
			if (acao.equals("cadastrar")){
				return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<ProdutoModelo>(pr.save(pm),HttpStatus.OK);
			}

		}
	}

	// Método para remover produtos
	public ResponseEntity<RespostaModelo> remover(Long codigo){
		pr.deleteById(codigo);
		rm.setMensagem("O produto foi removido com sucesso");
		return  new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
	}
	
	
}