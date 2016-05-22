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

class SiecNeuronowa {
  List<PolaczenieWartosc> wypisaneValueNeurons = new ArrayList<PolaczenieWartosc>();
  List<String> wypisaneObjectNeurons = new ArrayList<String>();
  Map<String, Map<Double, List<String>>> kolumny = new HashMap<String, Map<Double, List<String>>>();
  Map<String, List<PolaczenieWartosc>> neurony = new HashMap<String, List<PolaczenieWartosc>>();
  Map<Double, List<PolaczenieWartosc>> odleglosci = new HashMap<Double, List<PolaczenieWartosc>>();

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
      Map<Double, List<String>> neuronywKolumnie = new HashMap<Double, List<String>>();

      if(kolumny.get("C"+i)==null){
        kolumny.put("C"+i, neuronywKolumnie);
      }
      for(int j=0;j<tablicaWejsciowa.length;j++){


        double wartosc = tablicaWejsciowa[j][i];
        if(kolumny.get("C"+i).get(wartosc)==null){
          kolumny.get("C"+i).put(wartosc,new ArrayList<String>());
        }
      }
    }

    // TWORZENIE NEURONÓW DLA KAŻDEGO WIERSZA (Z PUSTĄ LISTĄ POŁĄCZEŃ)

    for(int i = 0; i<tablicaWejsciowa.length; i++ ){
      List<PolaczenieWartosc> wartosci = new ArrayList<PolaczenieWartosc>();
      neurony.put("O"+i, wartosci);

      // ŁACZENIE NEURONÓW Z WARTOŚCIAMI (WYPEŁNIANIE LISTY POŁĄCZEŃ)

      for(int j=0;j<tablicaWejsciowa[i].length;j++){
        double wartosc = tablicaWejsciowa[i][j];
        PolaczenieWartosc nowePolaczenieWartosc = new PolaczenieWartosc(wartosc, "C"+j);
        neurony.get("O"+i).add(nowePolaczenieWartosc);
        kolumny.get("C"+j).get(wartosc).add("O"+i);
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

      List<PolaczenieWartosc> polaczenia =  neurony.get(key);
      for(PolaczenieWartosc PolaczenieWartosc : polaczenia){
        PolaczenieWartosc.printPolaczenieWartosc();
        System.out.printf(" ");
      }
      System.out.printf("\n");
    }
  }

  boolean containsPolaczenie(List<PolaczenieWartosc> list, PolaczenieWartosc el){
    for (PolaczenieWartosc e : list) {
      if (e.wartosc == el.wartosc && e.kolumna.equals(el.kolumna)) {
        return true;
      }
    }
    return false;
  }

  void wypiszNeuron(PolaczenieWartosc valueNeuron){
    try {
      wypisaneValueNeurons.add(valueNeuron);
      valueNeuron.printPolaczenieWartosc();
      System.out.print("\n");
      List<String> polaczenia = kolumny.get(valueNeuron.kolumna).get(valueNeuron.wartosc);
      if(polaczenia.size()>0){
        for(String polaczenie : polaczenia){
          if(!wypisaneObjectNeurons.contains(polaczenie)){
            wypiszNeuron(polaczenie);
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Thread  interrupted.");
    }
  }
  void wypiszNeuron(String objectNeuron){
    try{
      wypisaneObjectNeurons.add(objectNeuron);
      System.out.println(objectNeuron);
      List<PolaczenieWartosc> polaczenia = neurony.get(objectNeuron);
      if(polaczenia.size()>0){
        for(PolaczenieWartosc polaczenie : polaczenia){
          if(!containsPolaczenie(wypisaneValueNeurons, polaczenie)){
            System.out.print("\t");
            wypiszNeuron(polaczenie);
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Thread  interrupted.");
    }
  }

  void rozpal(double[] zapalarka){

    System.out.println("Rozpalamy używając: " + Arrays.toString(zapalarka));

    for(int i=0; i<zapalarka.length;i++){
      String key = "C"+i;
      Map<Double, List<String>> kolumna = kolumny.get(key);
      List<Double> values = new ArrayList<Double>(kolumna.keySet());
      for(double value : values){
        double odleglosc = Math.round(Math.abs(value-zapalarka[i])*100);
        odleglosc /= 100;
        if(odleglosci.get(odleglosc)==null){
          odleglosci.put(odleglosc, new ArrayList<PolaczenieWartosc>());
        }
        PolaczenieWartosc nowePolaczenieWartosc = new PolaczenieWartosc(value, "C"+i);
        odleglosci.get(odleglosc).add(nowePolaczenieWartosc);
      }
    }

    System.out.println("\nTABLICA ODLEGŁOŚCI:\n");

    List<Double> sortowaneOdleglosci = new ArrayList<Double>(odleglosci.keySet());
    Collections.sort(sortowaneOdleglosci);
    for(double key : sortowaneOdleglosci){
      System.out.print(key + ": ");
      for(PolaczenieWartosc el : odleglosci.get(key)){
        el.printPolaczenieWartosc();
      }
      System.out.print("\n");
    }

    System.out.println("\nWYPISYWANIE:\n");
    List<ThreadWypisz> threads = new ArrayList<ThreadWypisz>();
    for(double key : sortowaneOdleglosci){
      for(PolaczenieWartosc el : odleglosci.get(key)){
        if(!containsPolaczenie(wypisaneValueNeurons, el)){
          threads.add(new ThreadWypisz("Thread", this, el));
          // wypiszNeuron(el);
        }
      }
    }
    for(ThreadWypisz t : threads){
      t.start();
    }
    try {
      for(ThreadWypisz t : threads){
        t.join();
      }
    }
    catch( Exception e) {
         System.out.println("Interrupted");
      }
  }

}

class ThreadWypisz extends Thread {
  private Thread t;
  private String threadName;
  PolaczenieWartosc  polaczenie;
  SiecNeuronowa siec;
  ThreadWypisz( String name, SiecNeuronowa Sie,  PolaczenieWartosc Pol){
    threadName = name;
    siec = Sie;
    polaczenie = Pol;
  }
  public void run() {
    // synchronized(siec) {
      siec.wypiszNeuron(polaczenie);
    // }
    // System.out.println("Thread " +  threadName + " exiting.");
  }
  public void start (){
    // System.out.println("Starting " +  threadName );
    if (t == null){
      t = new Thread (this, threadName);
      t.start ();
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
