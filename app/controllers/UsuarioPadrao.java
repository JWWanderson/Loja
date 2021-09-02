package controllers;

import java.util.List;

import models.Encomenda;
import models.Produto;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;

public class UsuarioPadrao extends Controller {

	public static void formCadastro() {
		Usuario usuario = (Usuario) Cache.get("usuario"); // Recuperando as informacoes do usuario que foram passadas no cache do salvarEncomenda
		Cache.clear(); //Limpando o cache
		render(usuario); //Renderizando a página
	}
	
	public static void salvarCadastro(@Valid Usuario usuario) { // @Valid para validar um usuario ao fazer o cadastro
		if(validation.hasErrors()) { //tradução: se houver erros de validação
			Cache.add("usuario", usuario); // adicione essas informações no cache
			validation.keep(); //pegue e coloque na próxima requisição
			formCadastro(); //volte para o formulário de Cadastro
		}
		usuario.setSenha(); //usuario.setSenha serve para criptografar a senha(lá no modelo Usuario)
		usuario.save(); // salvar o usuario
		Login.form(); // Redireciona para o formulário de Login onde é feito o mesmo
	}
	
	public static void salvarEncomenda(@Valid Encomenda encomenda, Long idProduto) { // @Valid para validar uma Encomenda
		
		if(validation.hasErrors()) { //tradução: se houver erros de validação
			Cache.add("encomenda", encomenda);  // adicione essas informações no cache
			validation.keep(); //pegue e coloque na próxima requisição
			index(); //volte para a página inicial onde contém o form para cadastrar a encomenda
		}
		
		if(idProduto != null) { //tradução: se o idproduto for diferente de null, ou seja, se existir 
			Produto produto = Produto.findById(idProduto); // Procura um produto no banco de dados que tenha o id informado no Parâmetro
			if(encomenda.produtos.contains(produto) == false) {
				encomenda.produtos.add(produto);
				encomenda.save(); // adicione esse produto dentro da lista de produtos
			}
			flash.success("Encomenda feita com sucesso!");
			UsuarioPadrao.index();
		}
			
		encomenda.save(); // salve a encomenda
		flash.success("Adicione o produto desejado!");
		editar(encomenda.id); // vá para página de edição para adicionar o produto
	}
	
	public static void index() {
		List<Usuario> usuarios = Usuario.findAll(); // Lista de Usuarios
		List<Produto> produtos = Produto.findAll(); // II de Produtos
		List<Encomenda> encomendas = Encomenda.findAll(); // II de Encomendas
		Encomenda encomenda = (Encomenda) Cache.get("encomenda"); // Recuperando as informacoes do usuario que foram passadas no cache do salvarEncomenda
		Cache.clear(); //Limpando o cache
		render(usuarios, produtos, encomendas, encomenda); //Renderizando a página inicial mandando todas as informações anteriores para dentro da página
	}
	
	public static void editar(Long id) {
		Encomenda encomenda = Encomenda.findById(id);
		List<Usuario> usuarios = Usuario.findAll();
		List<Produto> produtos = Produto.findAll();
		List<Encomenda> encomendas = Encomenda.findAll();
		renderTemplate("UsuarioPadrao/index.html", encomenda, usuarios, produtos, encomendas);
	}
	
}
