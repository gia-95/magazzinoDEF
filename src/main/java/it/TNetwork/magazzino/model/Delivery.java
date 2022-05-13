package it.TNetwork.magazzino.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Delivery {
	
	@Id
	private String id;

	private String dataConsegna;
	
	private String dataEmissione;

	private String nDelivery;
	
	private int numeroColli;
	
	private String note;
	
	private double quantita;
	
	private String unitaMisura;
	
	private String spedizioniere;
	
	private String  tipologiaStatoConsegna;
	
	private List<Order> ordiniAssociati;
	
	public Delivery () {
		this.ordiniAssociati = new ArrayList<Order>();
	}
	
	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(String dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public String getnDelivery() {
		return nDelivery;
	}

	public void setnDelivery(String nDelivery) {
		this.nDelivery = nDelivery;
	}

	public int getNumeroColli() {
		return numeroColli;
	}

	public void setNumeroColli(int numeroColli) {
		this.numeroColli = numeroColli;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public String getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	public String getSpedizioniere() {
		return spedizioniere;
	}

	public void setSpedizioniere(String spedizioniere) {
		this.spedizioniere = spedizioniere;
	}

	public String getTipologiaStatoConsegna() {
		return tipologiaStatoConsegna;
	}

	public void setTipologiaStatoConsegna(String tipologiaStatoConsegna) {
		this.tipologiaStatoConsegna = tipologiaStatoConsegna;
	}

	public List<Order> getOrdiniAssociati() {
		return ordiniAssociati;
	}

	public void setOrdiniAssociati(List<Order> ordiniAssociati) {
		this.ordiniAssociati = ordiniAssociati;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	

}
