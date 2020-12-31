package com.azienda.model;

import java.util.Comparator;

public class Ordinamentonomecognome implements Comparator<Dipendente>  {

	@Override
	public int compare(Dipendente o1, Dipendente o2) {
		int results = o1.getCognome().toLowerCase().compareTo(o2.getCognome().toLowerCase());
	    if (results == 0) {
	    	results=o1.getNome().toLowerCase().compareTo(o2.getNome().toLowerCase());
	    }
	    return results;
	}
}
