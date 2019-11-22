
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int taulukonKasvatuskoko;    // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
		this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
		this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        lukujoukko = new int[kapasiteetti];
        for (int i = 0; i < lukujoukko.length; i++) {
            lukujoukko[i] = 0;
        }		
		this.taulukonKasvatuskoko = kasvatuskoko;
	}
	
    public void lisaaAlkioJoukkoon(int luku) {
        if (alkioidenLkm == 0 || !kuuluukoLukuJoukkoon(luku)) {
            lukujoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == lukujoukko.length) {
                kasvataTaulukkoa();
            }
        }
    }

	private void kasvataTaulukkoa() {
		int[] taulukkoOld = new int[lukujoukko.length];
		kopioiTaulukko(lukujoukko, taulukkoOld);
		lukujoukko = new int[alkioidenLkm + taulukonKasvatuskoko];
		kopioiTaulukko(taulukkoOld, lukujoukko);
	}

    public boolean kuuluukoLukuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                return true;
            }
        }
		return false;
    }

    public void poistaAlkioJoukosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                lukujoukko[i] = 0;
				for (int j = i; j < alkioidenLkm - 1; j++) {
					lukujoukko[j] = lukujoukko[j + 1];
				}
				alkioidenLkm--;
                break;
            }
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujoukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukujoukko[i];
                tuotos += ", ";
            }
            tuotos += lukujoukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujoukko[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaaAlkioJoukkoon(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaaAlkioJoukkoon(bTaulu[i]);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
			if (b.kuuluukoLukuJoukkoon(aTaulu[i]))
				leikkausJoukko.lisaaAlkioJoukkoon(aTaulu[i]);
        }
        return leikkausJoukko;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
			if (!b.kuuluukoLukuJoukkoon(aTaulu[i]))
				erotusJoukko.lisaaAlkioJoukkoon(aTaulu[i]);
        }
        return erotusJoukko;
    }
        
}