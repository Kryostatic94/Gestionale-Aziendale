package com.azienda.model;


@SuppressWarnings("serial")
public class InvalidData extends Exception {
	public InvalidData(String message) {
		super(message);
	}
}