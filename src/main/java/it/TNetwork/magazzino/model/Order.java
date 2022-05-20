package it.TNetwork.magazzino.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
	
	private String id;
	
	private int numeroOrdine;
	
	private String spedizioniere;
	
	private String dataConsegna;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
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
