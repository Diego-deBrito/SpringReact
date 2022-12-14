import logo from './logo.svg';
import Formulario from './Formulario'
import Tabela from "./Tabela";
import {useEffect, useState} from "react";
function App() {

    // Objeto produto
    const produto = {
        codigo: 0,
        nome: '',
        marca: ''
    }

    // UseState
    const [btnCadastrar, setBtnCadastrar] = useState(true);
    const [produtos, setProdutos] = useState([]);
    const [objProduto, setObjProduto] = useState(produto);


    // UseEffect
    useEffect(() => {
        fetch("http://localhost:8080/listar")
            .then(retorno => retorno.json())
            .then(retorno_convertido => setProdutos(retorno_convertido));

    }, []);

    // Obtendo os dados do formulário
    const aoDigitar = (e) => {
        setObjProduto({...objProduto, [e.target.name]: e.target.value});
    }

    // Cadastrar produto
    const cadastrar = () => {
        fetch('http://localhost:8080/cadastrar', {
            method: 'post',
            body: JSON.stringify(objProduto),
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(retorno => retorno.json())
            .then(retorno_convertido => {
                if (retorno_convertido.mensagem !== undefined) {
                    alert(retorno_convertido.mensagem);
                } else {
                    setProdutos([...produtos, retorno_convertido]);
                    alert('Produto cadastrado com sucesso!');
                    limparFormulario();
                }
            })




        // Limpar formulário
        const limparFormulario = () => {
            setObjProduto(produto);
            setBtnCadastrar(true);
        }

        // Selecionar produto
        const selecionarProduto = (indice) => {
            setObjProduto(produtos[indice]);
            setBtnCadastrar(false);
        }

        //Retorno
        return (
            <div>
                <Formulario botao={btnCadastrar} eventoTeclado={aoDigitar} cadastrar={cadastrar} obj={objProduto}  cancelar={limparFormulario}  />
                <Tabela vetor={produtos} selecionar={selecionarProduto}> </Tabela>
            </div>
        );
    }
}

export default App;
