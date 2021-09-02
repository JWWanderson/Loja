package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Encomenda extends Model{

	@Required 
	public String endereco;
	
	@Required 
	public String cep;
	
	@ManyToMany
	@JoinTable(name="encomenda_produto") 
	public List<Produto> produtos;
	
	@ManyToOne
	public Usuario usuario; 
}
