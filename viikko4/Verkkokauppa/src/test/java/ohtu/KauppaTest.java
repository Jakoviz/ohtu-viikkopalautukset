package ohtu;

import ohtu.verkkokauppa.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

	private Pankki pankki;
	private Varasto varasto;
	private Viitegeneraattori viite;
	private Kauppa k;

	@Before
	public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);

        // määritellään että viitegeneraattori palauttaa viitten 42
        // sitten testattava kauppa 
        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        k = new Kauppa(varasto, pankki, viite);              

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 7));
	}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

	@Test
	public void yhdenTuotteenOstamisenJalkeenKutsutaanPankinMetodiaTilisiirtoOikeallaAsiakkaallaTilinumeroillaJaSummalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
	}

	@Test
	public void kahdenEriTuotteenOstamisenJalkeenKutsutaanPankinMetodiaTilisiirtoOikeallaAsiakkaallaTilinumeroillaJaSummalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli mehua
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(12));   
	}

	@Test
	public void kahdenSamanTuotteenOstamisenJalkeenKutsutaanPankinMetodiaTilisiirtoOikeallaAsiakkaallaTilinumeroillaJaSummalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));   
	}

	@Test
	public void tuotteenJaLoppuneenTuotteenOstamisenJalkeenKutsutaanPankinMetodiaTilisiirtoOikeallaAsiakkaallaTilinumeroillaJaSummalla() {
        when(varasto.saldo(2)).thenReturn(0); 

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
	}

	@Test
	public void asioinninAloittaminenNollaaEdellisenOstoksenTiedot() {
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");

		verify(pankki, times(1)).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   

		k.aloitaAsiointi();
		k.lisaaKoriin(2);
		k.tilimaksu("pekka", "12345");

		verify(pankki, times(2)).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(7));   
	}	
	
	@Test
	public void jokaiselleMaksutapahtumallePyydetaanUusiViitenumero() {
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");

		when(viite.uusi()).thenReturn(99);

		verify(pankki, times(1)).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   

		k.aloitaAsiointi();
		k.lisaaKoriin(2);
		k.tilimaksu("pekka", "12345");

		when(viite.uusi()).thenReturn(88);

		verify(pankki, times(2)).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        verify(pankki).tilisiirto(eq("pekka"), eq(99), eq("12345"), eq("33333-44455"), eq(7));  		
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");

		verify(pankki, times(3)).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        verify(pankki).tilisiirto(eq("pekka"), eq(88), eq("12345"), eq("33333-44455"), eq(5));  
	 }

	public void tuotteenPoistamisenJalkeenKutsutaanPankinMetodiaTilisiirtoOikeallaAsiakkaallaTilinumeroillaJaSummalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli mehua
		k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");

		verify(varasto).palautaVarastoon(eq(varasto.haeTuote(1)));
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(7));	
	}
}