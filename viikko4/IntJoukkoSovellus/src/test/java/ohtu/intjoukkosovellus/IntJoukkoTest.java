package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaaAlkioJoukkoon(10);
        joukko.lisaaAlkioJoukkoon(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaaAlkioJoukkoon(4);
        assertEquals(3, joukko.mahtavuus());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaaAlkioJoukkoon(10);
        joukko.lisaaAlkioJoukkoon(3);
        assertEquals(2, joukko.mahtavuus());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluukoLukuJoukkoon(10));
        assertFalse(joukko.kuuluukoLukuJoukkoon(5));
        assertTrue(joukko.kuuluukoLukuJoukkoon(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poistaAlkioJoukosta(3);
        assertFalse(joukko.kuuluukoLukuJoukkoon(3));
        assertEquals(1, joukko.mahtavuus());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        joukko.lisaaAlkioJoukkoon(55);
        joukko.poistaAlkioJoukosta(10);
        joukko.lisaaAlkioJoukkoon(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            joukko.lisaaAlkioJoukkoon(luku);
        }
        assertEquals(14, joukko.mahtavuus());
        assertTrue(joukko.kuuluukoLukuJoukkoon(11));
        joukko.poistaAlkioJoukosta(11);
        assertFalse(joukko.kuuluukoLukuJoukkoon(11));
        assertEquals(13, joukko.mahtavuus());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", joukko.toString());
    }
    
    @Test
    public void toStringToimiiYhdenKokoiselleJoukolla(){
        joukko = new IntJoukko();
        joukko.lisaaAlkioJoukkoon(1);
        assertEquals("{1}", joukko.toString());
    }

    @Test
    public void toStringToimiiTyhjallaJoukolla(){
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }     
}
