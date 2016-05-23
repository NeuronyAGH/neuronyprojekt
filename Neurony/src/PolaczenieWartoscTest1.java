import static org.junit.Assert.*;

/**
 * Created by Krzysztof on 2016-05-23.
 */
public class PolaczenieWartoscTest1 {


    @org.junit.Test
    public void testKolumna() throws Exception {
        System.out.println("Test polaczen:");
        double wartosc=1;
        String kolumna="jeden";
        PolaczenieWartosc test1 = new PolaczenieWartosc(wartosc, kolumna);
        assertEquals(test1.kolumna, kolumna);
    }
    @org.junit.Test
    public void testWartosc() throws Exception {
        double wartosc=1;
        String kolumna="jeden";
        PolaczenieWartosc test2 = new PolaczenieWartosc(wartosc, kolumna);
        assertEquals(test2.wartosc, wartosc, 0.0);
    }
}