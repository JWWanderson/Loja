package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Email;
import play.data.validation.Match;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{

	@Required(message="*Este campo é obrigatório") 
	public String nome; 
	
	@Required(message="*Este campo é obrigatório") 
	@Email 
	@Unique(message="*Já existe um usuário com este email") 
	public String email;
	
	@Required(message="Este campo é obrigatório") 
	@Unique(message="*Já existe um usuário com este cpf")
	public String cpf;
	
	@Required(message="*Este campo é obrigatório") 
	@MinSize(value=5, message="*O tamanho minimo de caracteres é 5") 
	public String senha;
	
	@OneToMany(mappedBy="usuario") // Relacionamento OneToMany - Um para Muitos (Um usuario para muitas encomendas)
	public List<Encomenda> encomendas;
	
	public void setSenha() { 
		this.senha = Crypto.passwordHash(senha); 
	}
}
