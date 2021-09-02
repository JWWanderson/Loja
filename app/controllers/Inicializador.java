package controllers;

import models.Administrador;
import models.Produto;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.mvc.Controller;

@OnApplicationStart
public class Inicializador extends Job{

	@Override
	public void doJob() throws Exception {
		if (Usuario.count() == 0) {
			Administrador admin = new Administrador();
			admin.nome = "admin";
			admin.email = "admin@admin.com";
			admin.cpf = "023.123.291-29";
			admin.senha = "12345";
			admin.setSenha();
			admin.save();
		}
		
		if(Produto.count() == 0) {
			Produto produto = new Produto();
			produto.nome = "Boneca Funko Pop Anne";
			produto.tamanho = 20;
			produto.caracteristicas = "Boneca";
			produto.preco = 8;
			produto.save();
			
			Produto produto1 = new Produto();
			produto1.nome = "Boneca Penelope Charmosa";
			produto1.tamanho = 25;
			produto1.caracteristicas = "Boneca";
			produto1.preco = 10;
			produto1.save();
			
			Produto produto3 = new Produto();
			produto3.nome = "Carro topo de bolo Relampago Macqueen";
			produto3.tamanho = 20;
			produto3.caracteristicas = "Carro";
			produto3.preco = 8;
			produto3.save();
			
			Produto produto4 = new Produto();
			produto4.nome = "Chaveiro coco e papel higienico";
			produto4.tamanho = 18;
			produto4.caracteristicas = "Chaveiro";
			produto4.preco = 14;
			produto4.save();
			
			
		}
	}
}
