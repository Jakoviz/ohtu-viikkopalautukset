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

public abstract class Komento {
	int tuloskentanAiempiArvo;
	int syotekentanArvo;
	Sovelluslogiikka sovellus; 
	TextField tuloskentta;
	TextField syotekentta;
	
    public Komento(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.sovellus = sovellus;
    }

    public void suorita() {
		tuloskentanAiempiArvo = Integer.parseInt(tuloskentta.getText());
		try {
			syotekentanArvo = Integer.parseInt(syotekentta.getText());
		} catch (Exception e) {
			syotekentanArvo = 0;
		}		
	}
	
    public void peru() {
		tuloskentta.setText("" + tuloskentanAiempiArvo);
		sovellus.setTulos(tuloskentanAiempiArvo);
	}
}
