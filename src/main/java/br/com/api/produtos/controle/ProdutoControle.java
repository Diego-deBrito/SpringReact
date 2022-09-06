package br.com.api.produtos.controle;

import br.com.api.produtos.modelo.RespostaModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.servico.ProdutoServico;

@RestController // responsavel por criar rotas, exemplo rota de testes
@CrossOrigin(origins = "*")
public class ProdutoControle {
	
	@Autowired
	private ProdutoServico ps;
	
	@DeleteMapping("/remover/{codigo}")
	public ResponseEntity<RespostaModelo> deletar(@PathVariable long codigo){
		return ps.remover(codigo);
	}

	@PutMapping("/alterar")
	public ResponseEntity<?>alterar(@RequestBody ProdutoModelo pm){
		return ps.cadastrarAlterar(pm, "alterar");
	}

	@PostMapping(value = "/cadastrar") //retorna um ResponseEntity, objeto ou erro
	public ResponseEntity<?>cadastrar(@RequestBody ProdutoModelo pm){
		return ps.cadastrarAlterar(pm, "cadastrar");
	}
	
	@GetMapping("/listar")
	public Iterable<ProdutoModelo>listar(){
		return ps.listar();
	}
	
	
	@GetMapping("/")  //tipo de requisição
	public String rota() {
		return "API de produtos funcionando!";
	}
}
