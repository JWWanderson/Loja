package controllers;

import java.util.List;

import models.Produto;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Produtos extends Controller {

	public static void form() {
		render();
	}
	
	public static void salvar(Produto produto) {
		produto.save();
		form();
	}
	
	public static void listar() {
		List<Produto> produtos = Produto.findAll();
		render(produtos);
	}
	
	public static void editar(Long id) {
		Produto produto = Produto.findById(id);
		renderTemplate("Produtos/form.html", produto);
	}
	
	public static void remover(Long id) {
		Produto produto = Produto.findById(id);
		produto.delete();
		listar();
	}
}
