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
		Usuario usuario = (Usuario) Cache.get("usuario"); 
		Cache.clear(); 
		render(usuario); 
	}
	
	public static void salvarCadastro(@Valid Usuario usuario) { 
		if(validation.hasErrors()) {
			Cache.add("usuario", usuario); 
			validation.keep(); 
			formCadastro(); 
		}
		usuario.setSenha(); 
		usuario.save(); 
		Login.form(); 
	}
	
	public static void salvarEncomenda(@Valid Encomenda encomenda, Long idProduto) { 
		
		if(validation.hasErrors()) {
			Cache.add("encomenda", encomenda); 
			validation.keep(); 
			index(); 
		}
		
		if(idProduto != null) { 
			Produto produto = Produto.findById(idProduto); 
			if(encomenda.produtos.contains(produto) == false) {
				encomenda.produtos.add(produto);
				encomenda.save(); 
			}
			flash.success("Encomenda feita com sucesso!");
			UsuarioPadrao.index();
		}
			
		encomenda.save(); 
		flash.success("Adicione o produto desejado!");
		editar(encomenda.id); 
	}
	
	public static void index() {
		List<Usuario> usuarios = Usuario.findAll(); 
		List<Produto> produtos = Produto.findAll(); 
		List<Encomenda> encomendas = Encomenda.findAll(); 
		Encomenda encomenda = (Encomenda) Cache.get("encomenda"); 
		Cache.clear(); //Limpando o cache
		render(usuarios, produtos, encomendas, encomenda); 
	}
	
	public static void editar(Long id) {
		Encomenda encomenda = Encomenda.findById(id);
		List<Usuario> usuarios = Usuario.findAll();
		List<Produto> produtos = Produto.findAll();
		List<Encomenda> encomendas = Encomenda.findAll();
		renderTemplate("UsuarioPadrao/index.html", encomenda, usuarios, produtos, encomendas);
	}
	
}
