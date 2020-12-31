package com.azienda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.azienda.db.Archivio;
import com.azienda.model.*;

public class Start {
	private static Scanner input = new Scanner(System.in);
	private static Scanner inputDipendente = new Scanner(System.in);
	
	public static void main(String[] args) {
		Archivio archivio =Archivio.getArchivio();
		//carica(archivio);
		System.out.println("Benvenuto nel Gestionale dell'Azienda");
		int scelta = 0;
		String tmp = null;
		String[] datiSplit = null;
		String dataDiNascita= null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDataDiNascita = null;
		Dipendente x = null;

		while (scelta != 12) {
			menu();

			scelta = input.nextInt();
			switch (scelta) {
			case 1:
				try {
				System.out.println(
						"Inserisci i dati del Programmatore separati da virgola (nome, cognome, cf, linguaggio, mansione, anniEsperienza, data di nascita[gg-mm-aaaa])");
				tmp = inputDipendente.nextLine();
				datiSplit = tmp.split(",");
				Dipendente.verificaCaratteri(datiSplit[2]);
				Programmatore p = new Programmatore(datiSplit[0], datiSplit[1], datiSplit[2], datiSplit[3],
						datiSplit[4], Integer.parseInt(datiSplit[5].trim()));
				dataDiNascita= datiSplit[6].trim(); 
				localDataDiNascita= LocalDate.parse(dataDiNascita, formatter);
				LocalDate assunzione=Dipendente.generatoreDate(localDataDiNascita);
				p.setDataAssunzione(assunzione);
				p.setDataDiNascita(localDataDiNascita);
				archivio.addDipendente(p);
				}catch(Exception e) {
					System.out.println(
							"Errore, riprovare!"+e.getMessage());
				}
				break;
			case 2:
				try {
				System.out.println(
						"Inserisci i dati del Grafico separati da virgola (nome, cognome, cf, ideGrafici, mansione, anniEsperienza,data di nascita[gg-mm-aaaa]) ");
				tmp = inputDipendente.nextLine();
				datiSplit = tmp.split(",");
				Dipendente.verificaCaratteri(datiSplit[2]);
				Grafico g = new Grafico(datiSplit[0], datiSplit[1], datiSplit[2], datiSplit[3], datiSplit[4],
						Integer.parseInt(datiSplit[5].trim()));
				dataDiNascita= datiSplit[6].trim(); 
				localDataDiNascita= LocalDate.parse(dataDiNascita, formatter);
				LocalDate assunzione=Dipendente.generatoreDate(localDataDiNascita);
				g.setDataAssunzione(assunzione);
				g.setDataDiNascita(localDataDiNascita);
				archivio.addDipendente(g);
				//archivio.addDipendente(g);
				}catch(Exception e) {
					System.out.println(
						"Errore, riprovare!");
				}
				break;

			case 3:
				try {
				System.out.println(
						"Inserisci i dati del Manager separati da virgola (nome, cognome, cf, dipartimento, bonus,data di nascita[gg-mm-aaaa])) ");

				tmp = inputDipendente.nextLine();
				datiSplit = tmp.split(",");
				Dipendente.verificaCaratteri(datiSplit[2]);
				Manager m = new Manager(datiSplit[0], datiSplit[1], datiSplit[2], datiSplit[3],
						Integer.parseInt(datiSplit[4].trim()));
				dataDiNascita= datiSplit[6].trim(); 
				localDataDiNascita= LocalDate.parse(dataDiNascita, formatter);
				LocalDate assunzione=Dipendente.generatoreDate(localDataDiNascita);
				m.setDataAssunzione(assunzione);
				m.setDataDiNascita(localDataDiNascita);
				archivio.addDipendente(m);
				//archivio.addDipendente(m);
				}catch(Exception e) {
					System.out.println(
					"Errore, riprovare!");
				}
				break;
			case 4:
				System.out.println("Elenco Dipendenti:");
				//archivio.stampaDipendentiCognomi();
				//archivio.stampaDipendentiData();
				//archivio.stampaDipendenti();
				archivio.stampaDipendentiCognomienomi();
				break;
			case 5:
				System.out.println("Inserisci P x Programmatore, G x Grafico, M x Manager");
				tmp = inputDipendente.nextLine();
				System.out.println("Elenco Dipendenti x tipo:" + tmp);
				archivio.stampaDipendentiPerTipo(tmp);
				break;
			case 6:
				System.out.println("Inserisci Il Codice Fiscale del Dipendente");
				tmp = inputDipendente.nextLine();
				x=archivio.trovaDipendentePerCF(tmp);
				if(x!=null) { 
					System.out.println("Il dipendente corrispondente e' : ");
					System.out.println(x.toString());
				}else {
					System.out.println("Dipendente non trovato");
				}
				break;
			case 7:
				System.out.println("Totale Stipendi per tipo dipendente:");
				System.out.println("Inserisci P x Programmatore, G x Grafico, M x Manager");
				tmp = inputDipendente.nextLine();
				double totale = archivio.calcolaStipendiPerTipo(tmp);
				System.out.println("Totale stipendi: " + totale);
				break;
			case 8:
				System.out.println("Ecco la lista dei nostri consulenti:");
				archivio.elencoPersonaleConsulente();
				break;
			case 9:
				archivio.stampaDipendentiSuFile();
				break;
			case 10:
				archivio.salvaDipendentiSuFile();
				break;
			case 11:
				archivio.importaDipendentiDaFile();
				break;
			case 12:
				System.out.println("Alla prossima!!!");
				break;
			default:
				System.out.println("Scelta non valida!!! Riprova.");
				break;
			}

		}
	}

	private static void menu() {
		System.out.println();
		System.out.println("1) Inserisci Programmatore");
		System.out.println("2) Inserisci Grafico");
		System.out.println("3) Inserisci Manager");
		System.out.println("4) Stampa elenco dipendenti");
		System.out.println("5) Stampa elenco dipendenti per tipo");
		System.out.println("6) Trova dipendente per codice fiscale");
		System.out.println("7) Calcola totale stipendi per tipo dipendente");
		System.out.println("8) Stampa Consulenti");
		System.out.println("9) Stampa lista dipendenti su un file");
		System.out.println("10) Esporta database dipendenti");
		System.out.println("11) Importa database dipendenti");
		System.out.println("12) Esci");
		System.out.print("Inserire la scelta: ");
	}

	/*public static void carica(Archivio archivio) {
		try {
		Programmatore p1 = new Programmatore("Nino", "Rossi", "nnrss", "java", "Sviluppatore Android", 2);
		Programmatore p2 = new Programmatore("Pippo", "Verdi", "pppvrd", "C#", "Programmatore Standlone PC", 0);
		Programmatore p3 = new Programmatore("Alex", "Bianchi", "lxbch", "Python", "Intelligenza Artificiale", 7);
		Programmatore p4 = new Programmatore("Lino", "Rossi", "kkkkkkkkk", "java", "Sviluppatore Android", 2);

		archivio.addDipendente(p1);
		archivio.addDipendente(p2);
		archivio.addDipendente(p3);
		archivio.addDipendente(p4);
		

		Grafico g1 = new Grafico("Giorgia", "Viola", "grgvl", "Photoshop", "Grafica editoriale", 2);
		archivio.addDipendente(g1);

		Manager m1 = new Manager("Luigi", "Rosa", "lgrs", "IT", 500);
		archivio.addDipendente(m1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}*/

}
