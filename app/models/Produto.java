package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Produto extends Model{

	public String nome;
	public int tamanho;
	public String caracteristicas;
	public float preco;
	
	
	@ManyToMany(mappedBy="produtos")
	public List<Encomenda> encomendas;
}
