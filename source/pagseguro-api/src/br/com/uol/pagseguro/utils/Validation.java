package br.com.uol.pagseguro.utils;

public interface Validation {
	
	static boolean isValid(String value) {
		return(value == null || "".equals(value));
	}
}
