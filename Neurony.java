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

    double[] zapalarka = new double[]() {1, 1.5, 3, 2, 9, 2.1};

    WczytajZPliku test = new WczytajZPliku();
    SiecNeuronowa nowaMapa = new SiecNeuronowa(test.tablica);
    for(double[] a: test.tablica){
      for(double b: a){
        System.out.printf("%.1f ",b);
      }
      System.out.printf("\n");
    }
  }
}

class Polaczenie {
  double wartosc;
  String kolumna;
  Polaczenie(double wartosc, String kolumna){
    this.wartosc = wartosc;
    this.kolumna = kolumna;
  }
  void printPolaczenie(){
    System.out.printf("["+ wartosc + " : " + kolumna +"]");
  }
}

class SiecNeuronowa {
  SiecNeuronowa(double[][] t){

    Map<String, List<Double>> kolumny = new HashMap<String, List<Double>>();
    Map<String, List<Polaczenie>> neurony = new HashMap<String, List<Polaczenie>>();

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
      List<Double> wartosci = new ArrayList<Double>();
      if(kolumny.get(i)==null){
        kolumny.put("C"+i, wartosci);
      }
      for(int j=0;j<tablicaWejsciowa.length;j++){
        if(!kolumny.get("C"+i).contains(tablicaWejsciowa[j][i])){
          kolumny.get("C"+i).add(tablicaWejsciowa[j][i]);
        }
      }
      Collections.sort(kolumny.get("C"+i));
    }

    // TWORZENIE NEURONÓW DLA KAŻDEGO WIERSZA (Z PUSTĄ LISTĄ POŁĄCZEŃ)

    for(int i = 0; i<tablicaWejsciowa.length; i++ ){
      List<Polaczenie> wartosci = new ArrayList<Polaczenie>();
      neurony.put("O"+i, wartosci);

      // ŁACZENIE NEURONÓW Z WARTOŚCIAMI (WYPEŁNIANIE LISTY POŁĄCZEŃ)

      for(int j=0;j<tablicaWejsciowa[i].length;j++){
        Polaczenie nowePolaczenie = new Polaczenie(tablicaWejsciowa[i][j], "C"+j);
        neurony.get("O"+i).add(nowePolaczenie);
      }
    }

    // WYPISYWANIE MAP

    System.out.println("Kolumny:");
    List<String> k_kolumny = new ArrayList<String>(kolumny.keySet());
    Collections.sort(k_kolumny);
    for(String key : k_kolumny){
      System.out.printf(key + " : ");
      System.out.println(kolumny.get(key));
    }

    System.out.println("Neurony:");
    List<String> k_neurony = new ArrayList<String>(neurony.keySet());
    Collections.sort(k_neurony);
    for(String key : k_neurony){
      System.out.printf(key + " : ");

      List<Polaczenie> polaczenia =  neurony.get(key);
      for(Polaczenie polaczenie : polaczenia){
        polaczenie.printPolaczenie();
        System.out.printf(" ");
      }
      System.out.printf("\n");
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
