package controllers;

import models.Usuario;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{

	@Before()
	static void verificar() {
		if(session.get("cliente.email") == null) {
			Login.form();
		} 
	}
}
