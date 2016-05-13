package br.com.uol.pagseguro.utils;

public interface Validation {
	
	static boolean isInvalid(String value) {
		return(value == null || "".equals(value.trim()));
	}
}
