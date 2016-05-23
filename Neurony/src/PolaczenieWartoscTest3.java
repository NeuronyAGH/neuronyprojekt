import static org.junit.Assert.*;

/**
 * Created by Krzysztof on 2016-05-23.
 */
public class PolaczenieWartoscTest3 {


    @org.junit.Test
    public void testKolumna() throws Exception {
        double wartosc=3;
        String kolumna="trzy";
        PolaczenieWartosc test1 = new PolaczenieWartosc(wartosc, kolumna);
        assertEquals(test1.kolumna, kolumna);
    }
    @org.junit.Test
    public void testWartosc() throws Exception {
        double wartosc=3;
        String kolumna="trzy";
        PolaczenieWartosc test2 = new PolaczenieWartosc(wartosc, kolumna);
        assertEquals(test2.wartosc, wartosc, 0.0);
    }
}