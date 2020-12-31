package com.azienda.model;

import java.time.LocalDate;
import java.time.Period;

public class Grafico extends Dipendente implements Consulente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2315327120273217300L;
	private String ideGrafici;
	private String mansione;
	private int anniEsperienza;

	public Grafico(String nome, String cognome, String cf, String ideGrafici, String mansione, int anniEsperienza) throws InvalidCf {
		super(nome, cognome, cf);
		this.ideGrafici = ideGrafici;
		this.mansione = mansione;
		this.anniEsperienza = anniEsperienza;
		setSalario();
	}

	private void setSalario() {
		double valoreAumentoRiferitoAnni = 0.2;
		double totaleValoreAumentoStipendioAnni = valoreAumentoRiferitoAnni * anniEsperienza;
		double totaleMaggiorazione = totaleValoreAumentoStipendioAnni + 1.2;

		super.setSalario(totaleMaggiorazione);
	}

	public String getIdeGrafici() {
		return ideGrafici;
	}

	public void setIdeGrafici(String ideGrafici) {
		this.ideGrafici = ideGrafici;
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
		return "Grafico " + super.toString() + "ideGrafici=" + ideGrafici + ", mansione=" + mansione
				+ ", anniEsperienza=" + anniEsperienza;
	}


	@Override
	public String infoAnzianitaLavorativa() {
		Period intervallo = Period.between(super.getDataAssunzione(), LocalDate.now());
        return "Anni: " + intervallo.getYears() + ", Mesi: " + intervallo.getMonths();
	}
	
	@Override
	public void dettagliRisorsa() {
		
		System.out.println("Grafico " + " Nome:"+super.getNome() + " , Cognome:"+super.getCognome()+ " , ideGrafici=" + ideGrafici + ", mansione=" + mansione
				+ ", anniEsperienza=" + anniEsperienza+" Lavora con noi da "+infoAnzianitaLavorativa());
	}

	

}
