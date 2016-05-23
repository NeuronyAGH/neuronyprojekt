/**
 * Created by Krzysztof on 2016-05-23.
 */
class PolaczenieWartosc {
    double wartosc;
    String kolumna;
    PolaczenieWartosc(double wartosc, String kolumna){
        this.wartosc = wartosc;
        this.kolumna = kolumna;
    }
    void printPolaczenieWartosc(){
        System.out.print("["+ wartosc + " : " + kolumna +"]");
    }
}
