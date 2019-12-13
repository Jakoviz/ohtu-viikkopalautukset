package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends Pelimuoto {

	private static final Scanner scanner = new Scanner(System.in);
	
	@Override
	protected String pelaaToinenOsapuoli(String ekanSiirto) {
		System.out.print("Toisen pelaajan siirto: ");
		String tokanSiirto = scanner.nextLine();
		return tokanSiirto;
	}
}