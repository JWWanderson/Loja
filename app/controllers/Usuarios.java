package controllers;

import java.util.List;

import models.Usuario;
import models.Produto;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Usuarios extends Controller {

	public static void form() {
		Usuario usuario = (Usuario) Cache.get("usuario");
		Cache.clear();
		render(usuario);
	}
	
	public static void salvar(@Valid Usuario usuario) {
		if(validation.hasErrors()) {
			Cache.add("usuario", usuario);
			validation.keep();
			form();
		}
		usuario.save();
		form();
	}
	
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usuario);
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		listar();
	}
}
