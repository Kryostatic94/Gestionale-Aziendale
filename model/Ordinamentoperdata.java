package com.azienda.model;

import java.util.Comparator;

public class Ordinamentoperdata implements Comparator<Dipendente>  {

	@Override
	public int compare(Dipendente o1, Dipendente o2) {
		int result = o1.getDataAssunzione().compareTo(o2.getDataAssunzione());
		if(result!=0) {
			return result;
		}else {
			return 0;
		}
	}

	
}
