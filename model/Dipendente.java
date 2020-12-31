package com.azienda.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Dipendente implements Comparable<Dipendente>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327213029186408171L;
	private int id;
	private String nome;
	private String cognome;
	private String cf;
	private double salario;
	private final double SALARIO_BASE = 1000;
	private static int contatore = 1;
	private LocalDate dataDiNascita;
	private LocalDate dataAssunzione;
	
	public abstract String infoAnzianitaLavorativa();
	
	public static LocalDate generatoreDate(LocalDate data) {
		LocalDate maggiorenne = data.plusYears(18);
		//long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long minDay = maggiorenne.toEpochDay();
	    long maxDay = LocalDate.now().toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
	    
		return randomDate;
	}
	
	public String getDataDiNascita() {
		if(dataDiNascita==null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataDiNascita.format(formatter);
	}

	public void setDataDiNascita(LocalDate dataDiNascita) throws InvalidData{
		LocalDate maggiorenne = LocalDate.now().minusYears(18);
		Period intervallo = Period.between(dataDiNascita, getDataAssunzione());
		long maxDay = maggiorenne.toEpochDay();
		long test=intervallo.toTotalMonths();
		if((maxDay>=dataDiNascita.toEpochDay()&& getDataAssunzione().toEpochDay()>=maxDay) && test>0) {
			this.dataDiNascita = dataDiNascita;
		}else {
			throw new InvalidData(" Data non valida la data");
		}
	}

	public Dipendente() {
		this.id = contatore++;
	}

	public Dipendente(String nome, String cognome, String cf) throws InvalidCf {
		this.id = contatore++;
		this.nome = nome;
		this.cognome = cognome;
		if(verificaCaratteri(cf)) {
			throw new InvalidCf(" Caratteri non permessi nel Codice Fiscale");
		}
		this.cf = cf;
		this.dataAssunzione= Dipendente.generatoreDate(LocalDate.of(1970, 1, 1));
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double totaleMaggiorazione) {
		this.salario = this.SALARIO_BASE;
		this.salario += (salario * totaleMaggiorazione / 100);
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cf=" + cf +", data di nascita= "+getDataDiNascita() + ", salario=" + salario+", Data Assunzione= "+dataAssunzione;
	}

	public LocalDate getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(LocalDate dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}
	
	public static boolean verificaCaratteri(String cf) {
		 //Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		 Pattern p = Pattern.compile("[a-zA-Z0-9]+");
		 Matcher m = p.matcher(cf);
		 boolean b = !m.matches();
		 return b;
	}
	
	public int compareTo(Dipendente d) {
		int result = this.cognome.toLowerCase().compareTo(d.getCognome().toLowerCase());
		return result;
	}

	public static void setContatore(int contatore) {
		Dipendente.contatore = contatore;
		
	}


}
