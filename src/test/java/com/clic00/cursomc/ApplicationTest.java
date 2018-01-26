package com.clic00.cursomc;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ApplicationTest {
	
	static Random rand = new Random();	
	
	public static void main(String[] args) {

		
		String mascara = "dd/MM/yyyy hh:mm:ssss";
		SimpleDateFormat dtFormat = new SimpleDateFormat(mascara);

		System.out.println(dtFormat.format(System.currentTimeMillis()));

		
	char[] vet = new char[10];
		
		int i = 0;
		for (char v : vet) {
			v = randomChar();
			vet[i] = v;
			System.out.println(i);
			i += 1;			

		}
		
		System.out.println(new String(vet));
	}
	
	 static char randomChar() {
		
		int opt = rand.nextInt(3);
		if(opt == 0) {  // gera um dígito
			
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1){ //gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}

	}
	
	
}
