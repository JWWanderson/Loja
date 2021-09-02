package controllers;

import java.util.List;

import models.Encomenda;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Encomendas extends Controller {

	public static void form() {
		render();
	}
	
	public static void salvar(Encomenda encomenda) {
		encomenda.save();
		flash.success("Encomenda feita com sucesso!");
		form();
	}
	
	public static void listar() {
		List<Encomenda> encomendas = Encomenda.findAll();
		render(encomendas);
	}
	
	public static void editar(Long id) {
		Encomenda encomenda = Encomenda.findById(id);
		renderTemplate("Encomendas/form.html", encomenda);
	}
	
	public static void remover(Long id) {
		Encomenda encomenda = Encomenda.findById(id);
		encomenda.delete();
		flash.success("Encomenda removida com sucesso!");
		listar();
	}
}
