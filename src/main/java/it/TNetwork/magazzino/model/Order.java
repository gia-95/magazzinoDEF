package it.TNetwork.magazzino.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
	
	private int n_ordine;
	
	private String spedizioniere;
	
	private String dataConsegna;
	

	public int getN_ordine() {
		return n_ordine;
	}

	public void setN_ordine(int n_ordine) {
		this.n_ordine = n_ordine;
	}

	public String getSpedizioniere() {
		return spedizioniere;
	}

	public void setSpedizioniere(String spedizioniere) {
		this.spedizioniere = spedizioniere;
	}

	
	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	

}
