package com.avivas.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilTest {
	
	@Test	
	public void givenLowerCaseAsciiLettersWhenIsLowerCaseAndAsciiThenReturnTrue() {
		assertTrue("La funcion isLowerCaseAndAscii debe regresar true", StringUtil.isLowerCaseAndAscii("eweweggkk"));
	}
	
	@Test	
	public void givenLowerCaseAsciiWithNumerWhenIsLowerCaseAndAsciiThenReturnFalse() {
		assertFalse("La funcion isLowerCaseAndAscii debe regresar si la cadena tiene numeros false", StringUtil.isLowerCaseAndAscii("ew1teweggkk"));
	}
	
	@Test	
	public void givenAsciiWithUpperCaseLetterWhenIsLowerCaseAndAsciiThenReturnFalse() {
		assertFalse("La funcion isLowerCaseAndAscii debe regresar false", StringUtil.isLowerCaseAndAscii("ewtEweggkk"));
	}
	
	@Test	
	public void givenNotAsciiLetterWhenIsLowerCaseAndAsciiThenReturnFalse() {
		assertFalse("La funcion isLowerCaseAndAscii debe regresar false con caracteres no ascii", StringUtil.isLowerCaseAndAscii("ewtñweggkk"));
	}
	
	@Test	
	public void givenNullStringWhenIsLowerCaseAndAsciiThenReturnFalse() {
		assertFalse("La funcion isLowerCaseAndAscii debe regresar false con string null", StringUtil.isLowerCaseAndAscii(null));
	}
	
	@Test	
	public void givenEmptyStringWhenIsLowerCaseAndAsciiThenReturnFalse() {
		assertFalse("La funcion isLowerCaseAndAscii debe regresar false con string null", StringUtil.isLowerCaseAndAscii(""));
	}
}
