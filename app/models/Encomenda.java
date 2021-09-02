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

	@Required //Validação para campo obrigatório
	public String endereco;
	
	@Required //Validação para campo obrigatório
	public String cep;
	
	@ManyToMany // Relacionamento ManyToMany - Muitos para Muitos (Muitas encomendas para muitos produtos)
	@JoinTable(name="encomenda_produto") 
	public List<Produto> produtos; //Lista de Produtos no Relacionamento
	
	@ManyToOne
	public Usuario usuario; // Relacionamento ManyToOne - Muitos para um (Muitas encomenda associadas a um usuário)
}
