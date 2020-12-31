package com.azienda.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.azienda.model.*;

public class Archivio {

	private ArrayList<Dipendente> schedario = new ArrayList<Dipendente>();
	//--------------Singleton----------------
    private static Archivio istanza = null;

    
    private Archivio() {}

 
    public static Archivio getArchivio() {
        if (istanza == null) {
            istanza = new Archivio();
        }
        return istanza;
    }
    
    //------------------------------
	public boolean addDipendente(Dipendente dip) throws ArrayIndexOutOfBoundsException {
		for (Dipendente d : schedario) {
			if (d.getCf().equals(dip.getCf())) {
				System.out.println(
						"Il dipendente con il codice fiscale " + dip.getCf() + " e' gia' presente nella banca dati!");
				return false;
			}
		}
		schedario.add(dip);
		return true;
	}

	public void stampaDipendenti() {
		for (Dipendente d : schedario) {
			System.out.println(d.toString());
		}
	}
	
	public void stampaDipendentiCognomi(){
		Collections.sort(schedario);
		for (Dipendente c : schedario) {
			System.out.println(c.toString());
			}
	}
	
	public void stampaDipendentiCognomienomi(){
		Comparator<Dipendente> sortName = new Ordinamentonomecognome();
		Collections.sort(schedario,sortName);
		for (Dipendente c : schedario) {
			System.out.println(c.toString());
			}
	}
	
	public void stampaDipendentiData(){
		Comparator<Dipendente> sortDate = new Ordinamentoperdata();
		Collections.sort(schedario,Collections.reverseOrder(sortDate));
		for (Dipendente c : schedario) {
			System.out.println(c.toString());
			}
	}

	public ArrayList<Dipendente> trovaDipendentePerTipo(String tipo) {
		ArrayList<Dipendente> listTmp = new ArrayList<Dipendente>();
		for (Dipendente d : schedario) {
			
			if(tipo.equalsIgnoreCase("p") && d instanceof Programmatore) {
				listTmp.add(d);
			}else if(tipo.equalsIgnoreCase("g") && d instanceof Grafico) {
				listTmp.add(d);
			}else if(tipo.equalsIgnoreCase("m") && d instanceof Manager) {
				listTmp.add(d);
			}
		}
		return listTmp;
	}

	public void stampaDipendentiPerTipo(String tipo) {
		ArrayList<Dipendente> listTmp = trovaDipendentePerTipo(tipo);
		for (Dipendente d : listTmp) {
			System.out.println(d);
		}
	}
	
	public Dipendente trovaDipendente(String cf) {
		for (Dipendente d : schedario) {
			if(d.getCf().equals(cf)) {
				return d;
			}
		}
		System.out.println("Utente non presente nella banca dati!");
		return null;
		
	}
	
	public boolean rimuoviDipendente(String cf) {
		Dipendente dipTmp = trovaDipendente(cf);
		if(dipTmp != null) {
			schedario.remove(dipTmp);
			return true;
		}
		return false;
		
	}
	
	public double calcolaStipendiPerTipo(String tipo) {
		ArrayList<Dipendente> listTmp = trovaDipendentePerTipo(tipo);
		double totaleStipendi = 0;
		for (Dipendente dipendente : listTmp) {
			totaleStipendi += dipendente.getSalario();
		}
		return totaleStipendi;
	}
	
	public Dipendente trovaDipendentePerCF(String CF) {
		
		for (Dipendente d : schedario) {
			
			if(CF.equalsIgnoreCase(d.getCf())) {
				return d;
			}
		}
		return null;
	}
	
	
	public void elencoPersonaleConsulente() {
		
		for (Dipendente d : schedario) {
			if(d instanceof Consulente) {
				((Consulente) d).dettagliRisorsa();
			}
		}
	}
	
	public void stampaDipendentiSuFile() {
		try (FileWriter fw = new FileWriter("dipendenti.txt")){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
			fw.write("Elenco Dipendenti del "+LocalDateTime.now().format(formatter).toString()+System.getProperty("line.separator"));
				 for(Dipendente d : schedario){
					     fw.write(d.toString() +System.getProperty("line.separator"));
		}
				
			System.out.println("Fatto");
		}catch(FileNotFoundException f){
			System.out.println("File dipendenti.txt non trovato " + f.getStackTrace());
		}catch(IOException e) {
			System.out.println("IOException: " + e);
		}
	}
	
	public void salvaDipendentiSuFile() {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("databasedipendenti.ser"));){
				 output.writeObject(schedario);
				 System.out.println("Fatto");
		}catch(FileNotFoundException f){
			System.out.println("FileNotFoundException: " + f);
		}catch(IOException e) {
			System.out.println("IOException: " + e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void importaDipendentiDaFile() {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("databasedipendenti.ser"));){
			this.schedario = (ArrayList<Dipendente>) input.readObject();
			System.out.println("Fatto");
			int maxID = 0;
			for (Dipendente d : schedario) {
				if (d.getId() > maxID) {
					maxID = d.getId();
				}
			}
			Dipendente.setContatore(maxID + 1);
			
		}catch(FileNotFoundException f){
			System.out.println("FileNotFoundException: " + f);
		}catch(IOException e) {
			System.out.println("IOException: " + e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
