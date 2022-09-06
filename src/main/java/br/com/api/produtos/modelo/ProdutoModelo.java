package br.com.api.produtos.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity // indicando que o arquivo vai gerar uma tabela
@Table(name = "produtos") // nomeando a tabela
@Getter // metodo gett
@Setter // metodo sett

public class ProdutoModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long codigo;
    private String nome;
    private String marca;


}
