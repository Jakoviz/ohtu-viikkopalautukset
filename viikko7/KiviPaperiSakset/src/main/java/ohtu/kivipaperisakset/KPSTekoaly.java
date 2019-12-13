package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends Pelimuoto
{
	int siirto;
	
	public KPSTekoaly() {
		siirto = 0;
	}

	public String annaSiirto() {
		siirto++;
		siirto = siirto % 3;

		if (siirto == 0) {
			return "k";
		} else if (siirto == 1) {
			return "p";
		} else {
			return "s";
		}
	}

	void asetaSiirto(String ekanSiirto) {
		// ei tehdä mitään 
	}

	@Override
	protected String pelaaToinenOsapuoli(String ekanSiirto) {
		String tokanSiirto = annaSiirto();
		System.out.println("Tietokone valitsi: " + tokanSiirto);
		return tokanSiirto;
	}

}