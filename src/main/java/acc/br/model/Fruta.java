package acc.br.model;

import jakarta.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Fruta extends PanacheEntity {

	public String nome;
	public int qtd; 
}

