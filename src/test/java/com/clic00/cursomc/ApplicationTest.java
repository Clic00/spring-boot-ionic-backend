package com.clic00.cursomc;

import java.text.SimpleDateFormat;

public class ApplicationTest {
	public static void main(String[] args) {
		
		String mascara = "dd/MM/yyyy hh:mm:ssss";
		SimpleDateFormat dtFormat = new SimpleDateFormat(mascara);

		System.out.println(dtFormat.format(System.currentTimeMillis()));

	}
}
