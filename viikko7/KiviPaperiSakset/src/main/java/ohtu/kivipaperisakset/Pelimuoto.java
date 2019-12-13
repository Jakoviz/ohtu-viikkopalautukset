/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author jaakkpaa
 */
public abstract class Pelimuoto {

	Scanner scanner = new Scanner(System.in);
	Tuomari tuomari = new Tuomari();	

	public static Pelimuoto luoKaksinpeli() {
		return new KPSPelaajaVsPelaaja();
	} 

	public static Pelimuoto luoTekoaly() {
		return new KPSTekoaly();
	} 

	public static Pelimuoto luoParempiTekoaly(int muisti) {
		return new KPSParempiTekoaly(muisti);
	} 
	
	public void pelaa() {
		String ekanSiirto;
		String tokanSiirto;
        do {
            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            
            tokanSiirto = pelaaToinenOsapuoli(ekanSiirto);

			tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
			System.out.println(tuomari);
			System.out.println();
        } while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto));
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
	} 

	protected abstract String pelaaToinenOsapuoli(String ekanSiirto);
	
	private static boolean onkoOkSiirto(String siirto) {
		return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
	}
}
