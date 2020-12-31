package com.azienda.model;

import java.time.LocalDate;
import java.time.Period;

public class Manager extends Dipendente {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021314616625800053L;
	private String dipartimento;
	private double bonus;

	public Manager(String nome, String cognome, String cf, String dipartimento, double bonus) throws InvalidCf {
		super(nome, cognome, cf);
		this.dipartimento = dipartimento;
		this.bonus = bonus;
		setSalario();
	}

	private void setSalario() {
		double percentualeDelBonus = bonus * 100 / 1000;
		double totaleMaggiorazione = 1.5 + percentualeDelBonus;
		super.setSalario(totaleMaggiorazione);
	}

	public String getDipartimento() {
		return dipartimento;
	}

	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "Manager "+ super.toString() +" dipartimento=" + dipartimento + ", bonus=" + bonus +" Lavora con noi da "+infoAnzianitaLavorativa();
	}
	
	@Override
	public String infoAnzianitaLavorativa() {
		Period intervallo = Period.between(super.getDataAssunzione(), LocalDate.now());
        return "Anni: " + intervallo.getYears() + ", Mesi: " + intervallo.getMonths();
	}

}
