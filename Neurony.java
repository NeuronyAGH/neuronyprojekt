import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.util.Collections;

public class Neurony {
  public static void main(String args[]) {

    double[] zapalarka = {1, 1.5, 3, 2, 9, 2.1};

    WczytajZPliku test = new WczytajZPliku();
    SiecNeuronowa nowaSiec = new SiecNeuronowa(test.tablica);
    nowaSiec.rozpal(zapalarka);
  }
}

class ValueNeuron {
  double wartosc;
  String kolumna;
  double moc;
  ValueNeuron(double wartosc, String kolumna){
    this.wartosc = wartosc;
    this.kolumna = kolumna;
  }
  void printValueNeuron(){
    System.out.print("["+ wartosc + " : " + kolumna +"]");
  }
}
class SiecNeuronowa {
  Map<ValueNeuron, Double> moceValueNeurons = new HashMap<ValueNeuron, Double>();
  Map<String, Double> moceObjectNeurons = new HashMap<String, Double>();
  List<ValueNeuron> wypisaneValueNeurons = new ArrayList<ValueNeuron>();
  List<String> wypisaneObjectNeurons = new ArrayList<String>();
  Map<String, Map<Double, List<String>>> kolumny = new HashMap<String, Map<Double, List<String>>>();
  Map<String, List<ValueNeuron>> neurony = new HashMap<String, List<ValueNeuron>>();
  Map<Double, List<ValueNeuron>> odleglosci = new HashMap<Double, List<ValueNeuron>>();

  SiecNeuronowa(double[][] t){

    double[][] tablicaWejsciowa = t;

    // WYPISYWANIE TABLICY WEJŚCIOWEJ

    System.out.printf("\n");
    System.out.println("Oto nasza tablica wejściowa: ");
    for(int i = 0; i<tablicaWejsciowa.length; i++ ){
      if(i==0){
        for(int j = 0; j< tablicaWejsciowa[i].length; j++){
          System.out.printf("\tC" + j);
        }
        System.out.printf("\n");
      }
      System.out.printf("R" + i + "\t");
      for(int j = 0; j< tablicaWejsciowa[i].length; j++){
        System.out.printf(tablicaWejsciowa[i][j] + "\t");
      }
      System.out.printf("\n");
    }
    System.out.printf("\n\n");

    // TWORZENIE NEURONÓW DLA WARTOŚCI W WEDŁUG KOLUMN

    for(int i = 0; i<tablicaWejsciowa[0].length; i++ ){
      Map<Double, List<String>> neuronywKolumnie = new HashMap<Double, List<String>>(); //Tworzenie pustej mapy połączeń dla każdej kolumny

      if(kolumny.get("C"+i)==null){
        kolumny.put("C"+i, neuronywKolumnie); //Włożenie jej w odpowiednią kolumnę
      }
      for(int j=0;j<tablicaWejsciowa.length;j++){
        double wartosc = tablicaWejsciowa[j][i];
        if(kolumny.get("C"+i).get(wartosc)==null){
          kolumny.get("C"+i).put(wartosc,new ArrayList<String>()); //Dodanie wartości (wraz z pustą listą połączeń) w do kolumny
        }
      }
    }

    // TWORZENIE NEURONÓW DLA KAŻDEGO WIERSZA (Z PUSTĄ LISTĄ POŁĄCZEŃ)

    for(int i = 0; i<tablicaWejsciowa.length; i++ ){
      List<ValueNeuron> wartosci = new ArrayList<ValueNeuron>(); //Tworzenie pustej listy połączeń z wartościami
      neurony.put("O"+i, wartosci); //I dodanie jej do każdego ObjectNeuronu

      // ŁACZENIE NEURONÓW Z WARTOŚCIAMI (WYPEŁNIANIE LISTY POŁĄCZEŃ)

      for(int j=0;j<tablicaWejsciowa[i].length;j++){
        double wartosc = tablicaWejsciowa[i][j];
        ValueNeuron nowyValueNeuron = new ValueNeuron(wartosc, "C"+j);
        neurony.get("O"+i).add(nowyValueNeuron); //Dodanie połączenia ObjectNeuron -> Wartość w kolumnie
        kolumny.get("C"+j).get(wartosc).add("O"+i); //Dodanie połączenia Wartość w kolumnie -> ObjectNeuron
      }
    }

    // WYPISYWANIE MAP

    List<String> k_kolumny = new ArrayList<String>(kolumny.keySet());
    Collections.sort(k_kolumny);
    for(String key : k_kolumny){
      System.out.printf(key + ": \n");
      Map<Double, List<String>> neuronywKolumnie = kolumny.get(key);

      List<Double> k_neuronywKolumnie = new ArrayList<Double>(neuronywKolumnie.keySet());
      Collections.sort(k_neuronywKolumnie);

      for(Double k_key : k_neuronywKolumnie){
        System.out.printf("\t");
        System.out.printf("%.1f -> ", k_key);
        System.out.println(neuronywKolumnie.get(k_key));
      }
    }

    System.out.println("Neurony:");
    List<String> k_neurony = new ArrayList<String>(neurony.keySet());
    Collections.sort(k_neurony);
    for(String key : k_neurony){
      System.out.printf("\t" + key + " -> ");

      List<ValueNeuron> polaczenia =  neurony.get(key);
      for(ValueNeuron ValueNeuron : polaczenia){
        ValueNeuron.printValueNeuron();
        System.out.printf(" ");
      }
      System.out.printf("\n");
    }
  }

  boolean containsPolaczenie(List<ValueNeuron> list, ValueNeuron el){
    for (ValueNeuron e : list) {
      if (e.wartosc == el.wartosc && e.kolumna.equals(el.kolumna)) {
        return true;
      }
    }
    return false;
  }

  void wypiszNeuron(ValueNeuron valueNeuron, double power){ //Wypisywanie valueNeuronu
    if(moceValueNeurons.get(valueNeuron) == null){ //Sprawdz, czy już istnieje w tablicy z mocami
      moceValueNeurons.put(valueNeuron, power); //Jeśli nie to dodaj z mocą
    }
    else { //Jeśli tak to zwiększ moc neuronu
      double newPower = moceValueNeurons.get(valueNeuron) + power;
      moceValueNeurons.put(valueNeuron, newPower);
    }
    if(moceValueNeurons.get(valueNeuron)>0.5){ //Jeżeli moc > 0.5 wypisz neuron i dodaj do listy wypisanych, aby się nie powtarzał
      wypisaneValueNeurons.add(valueNeuron);
      valueNeuron.printValueNeuron();
      System.out.print(" (moc: " + moceValueNeurons.get(valueNeuron) + ")\n");
      // System.out.println(moceValueNeurons.get(valueNeuron));
    }
    List<String> polaczenia = kolumny.get(valueNeuron.kolumna).get(valueNeuron.wartosc); //Wypisz każde połączenie
    if(polaczenia.size()>0){
      for(String polaczenie : polaczenia){
        if(!wypisaneObjectNeurons.contains(polaczenie) && power > 0.001){ //Jeśli jeszcze jest moc
          wypiszNeuron(polaczenie, power/2);
        }
      }
    }
  }
  void wypiszNeuron(String objectNeuron, double power){ //Analogicznie jak wyżej, ale dla objectNeuron
    if(moceObjectNeurons.get(objectNeuron) == null){
      moceObjectNeurons.put(objectNeuron, power);
    }
    else {
      double newPower = moceObjectNeurons.get(objectNeuron) + power;
      moceObjectNeurons.put(objectNeuron, newPower);
    }
    if(moceObjectNeurons.get(objectNeuron)>0.5){
      System.out.print(objectNeuron + " (moc: " + moceObjectNeurons.get(objectNeuron) + ")\n");
      // System.out.println(moceObjectNeurons.get(objectNeuron));
      wypisaneObjectNeurons.add(objectNeuron);
    }

    List<ValueNeuron> polaczenia = neurony.get(objectNeuron);
    if(polaczenia.size()>0){
      for(ValueNeuron polaczenie : polaczenia){
        if(!containsPolaczenie(wypisaneValueNeurons, polaczenie) && power > 0.001){
        // if(!wypisaneValueNeurons.contains(polaczenie) && probujDalej){
          // System.out.print("\t");
          wypiszNeuron(polaczenie, power/2);
        }
      }
    }
  }

  void rozpal(double[] zapalarka){

    System.out.println("Rozpalamy używając: " + Arrays.toString(zapalarka));

    for(int i=0; i<zapalarka.length;i++){
      String key = "C"+i;
      Map<Double, List<String>> kolumna = kolumny.get(key); //Tablica sortująca valueNeurony wedługo odległości od bodźca
      List<Double> values = new ArrayList<Double>(kolumna.keySet());
      for(double value : values){
        double odleglosc = Math.round(Math.abs(value-zapalarka[i])*100); //Liczenie odległości
        odleglosc /= 100;
        if(odleglosci.get(odleglosc)==null){
          odleglosci.put(odleglosc, new ArrayList<ValueNeuron>()); //Dodawanie połączenia wg. odległości
        }
        ValueNeuron noweValueNeuron = new ValueNeuron(value, "C"+i);
        odleglosci.get(odleglosc).add(noweValueNeuron);
      }
    }

    System.out.println("\nTABLICA ODLEGŁOŚCI:\n"); //Wypisiwanie tablicy odległości

    List<Double> sortowaneOdleglosci = new ArrayList<Double>(odleglosci.keySet());
    Collections.sort(sortowaneOdleglosci); //Sortowanie listy wedługo odległości
    for(double key : sortowaneOdleglosci){
      System.out.print(key + ": ");
      for(ValueNeuron el : odleglosci.get(key)){
        el.printValueNeuron();
      }
      System.out.print("\n");
    }

    System.out.println("\nWYPISYWANIE:\n");
    for(double key : sortowaneOdleglosci){
      for(ValueNeuron el : odleglosci.get(key)){
        if(!containsPolaczenie(wypisaneValueNeurons, el)){
          wypiszNeuron(el,1); //wypisywanie każdego valueNeuronu (najbliższe najpierw)
        }
      }
    }
  }

}

class WczytajZPliku{
  double[][] tablica = new double[5][6];
  WczytajZPliku(){
  	//poki co tylko wczytuje i wypisuje na ekran pojedyncze wartosci z pliku
    System.out.println("---TEST ODCZYTU PLIKU--");
  	File plik = new File("data.txt");
    try{
  	  Scanner odczyt = new Scanner(plik);
    	StringTokenizer token;
      int i=0;
    	while(odczyt.hasNextLine()){
    		token = new StringTokenizer(odczyt.nextLine(), " ");
        int j=0;
    		while(token.hasMoreElements()){
    			tablica[i][j] = Double.parseDouble(token.nextToken());
          j++;
    		}
        i++;
    	}
    } catch(FileNotFoundException fnfe) {
      System.out.println(fnfe.getMessage());
    }
  }
}
