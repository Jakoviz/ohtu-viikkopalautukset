/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author jaakkpaa
 */
public class Nollaa extends Komento {

	public Nollaa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, sovellus);
	}
	
	@Override
	public void suorita() {
		if (!tuloskentta.getText().equals("0")) {
			super.suorita();
			sovellus.nollaa();
		}
	}

	@Override
	public void peru() {
		super.peru();
	}		
}
