package br.com.api.produtos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.api.produtos.modelo.ProdutoModelo;

@Repository // ter acesso que é um repositório e usar dependências
public interface ProdutoRepositorio extends CrudRepository<ProdutoModelo, Long> {   // Crud precisa de uma classe de modelo e o tipo de dado, interage com funções no banco de dados
	 
	
	
}
