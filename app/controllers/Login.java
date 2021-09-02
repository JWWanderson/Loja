package controllers;

import models.Administrador;
import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{

	public static void form() {
		render();
	}
	
	public static void logar(String email, String senha) {
		
		Usuario usuario = Usuario.find("email = ?1 and senha = ?2", email, Crypto.passwordHash(senha)).first();
		
		if(usuario != null) {
			session.put("cliente.nome", usuario.nome);
			session.put("cliente.email", usuario.email);
			if(usuario instanceof Administrador) {
				Application.index();
			} else {
				UsuarioPadrao.index();
			}
			
		} else {
			form();
		}
	}
	
	public static void logout() {
		session.clear();
		form();
	}
}
