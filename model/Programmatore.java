package com.azienda.model;

import java.time.LocalDate;
import java.time.Period;

public class Programmatore extends Dipendente implements Consulente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2588704145290235382L;
	private String linguaggio;
	private String mansione;
	private int anniEsperienza;

	public Programmatore(String nome, String cognome, String cf, String linguaggio, String mansione,
			int anniEsperienza) throws InvalidCf {
		super(nome, cognome, cf);
		this.linguaggio = linguaggio;
		this.mansione = mansione;
		this.anniEsperienza = anniEsperienza;
		setSalario();
	}

	private void setSalario() {
		double valoreAumentoRiferitoAnni = 0.2;
		double totaleValoreAumentoStipendioAnni = valoreAumentoRiferitoAnni * anniEsperienza;
		double totaleMaggiorazione = totaleValoreAumentoStipendioAnni + 1.3;

		super.setSalario(totaleMaggiorazione);
	}

	public String getLinguaggio() {
		return linguaggio;
	}

	public void setLinguaggio(String linguaggio) {
		this.linguaggio = linguaggio;
	}

	public String getMansione() {
		return mansione;
	}

	public void setMansione(String mansione) {
		this.mansione = mansione;
	}

	public int getAnniEsperienza() {
		return anniEsperienza;
	}

	public void setAnniEsperienza(int anniEsperienza) {
		this.anniEsperienza = anniEsperienza;
	}

	@Override
	public String toString() {
		return "Programmatore " + super.toString() + " linguaggio=" + linguaggio + ", mansione=" + mansione
				+ ", anniEsperienza=" + anniEsperienza;
	}
	
	@Override
	public String infoAnzianitaLavorativa() {
		Period intervallo = Period.between(super.getDataAssunzione(), LocalDate.now());
        return "Anni: " + intervallo.getYears() + ", Mesi: " + intervallo.getMonths();
	}
	
	@Override
	public void dettagliRisorsa() {
		System.out.println("Programmatore " + " Nome:"+super.getNome() + " , Cognome:"+super.getCognome()+ " , linguaggio=" + linguaggio + ", mansione=" + mansione
				+ ", anniEsperienza=" + anniEsperienza+" Lavora con noi da "+infoAnzianitaLavorativa());
		
	}

}
