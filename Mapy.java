import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;

public class Mapy {
  public static void main(String args[]) {
    TestowaMapa nowaMapa = new TestowaMapa();
    WczytajZPliku test = new WczytajZPliku();
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
    System.out.printf("["+ kolumna + ":" + wartosc +"]");
  }
}

class TestowaMapa {
  TestowaMapa(){
    List<Integer> listaArgumentow = Arrays.asList(5,3,8,9,1,0,4,3,12,0);
    Map<String, List<Double>> kolumny = new HashMap<String, List<Double>>();
    Map<String, List<Polaczenie>> neurony = new HashMap<String, List<Polaczenie>>();


    double[][] tablicaArgumentow = new double[][]{
      { 0.1, 3, 4.5, 6, 8 },
      { 1, 1.5, 2, 2.2, 2.7 },
      { 0.3, 1, 6, 10, 10.2 },
      { 1, 0.5, 1.2, 2, 10 },
      { 0.4, 1, 5, 7, 8 },
    };

    System.out.printf("\n");
    System.out.println("Oto nasza tablica wejściowa: ");
    for(int i = 0; i<tablicaArgumentow.length; i++ ){
      if(i==0){
        for(int j = 0; j< tablicaArgumentow[i].length; j++){
          System.out.printf("\tC" + j);
        }
        System.out.printf("\n");
      }
      System.out.printf("R" + i + "\t");
      for(int j = 0; j< tablicaArgumentow[i].length; j++){
        System.out.printf(tablicaArgumentow[i][j] + "\t");
      }
      System.out.printf("\n");
    }
    System.out.printf("\n\n");

    for(int i = 0; i<tablicaArgumentow[0].length; i++ ){
      List<Double> wartosci = new ArrayList<Double>();
      if(kolumny.get(i)==null){
        kolumny.put("C"+i, wartosci);
      }
      for(int j=0;j<tablicaArgumentow.length;j++){
        if(!kolumny.get("C"+i).contains(tablicaArgumentow[j][i])){
          kolumny.get("C"+i).add(tablicaArgumentow[j][i]);
        }
      }
    }

    for(int i = 0; i<tablicaArgumentow.length; i++ ){
      List<Polaczenie> wartosci = new ArrayList<Polaczenie>();
      neurony.put("O"+i, wartosci);

      for(int j=0;j<tablicaArgumentow[i].length;j++){
        Polaczenie nowePolaczenie = new Polaczenie(tablicaArgumentow[i][j], "C"+j);
        neurony.get("O"+i).add(nowePolaczenie);
      }
    }

    System.out.println("Kolumny:");
    for(Map.Entry<String, List<Double>> el : kolumny.entrySet()){
      List<Double> polaczenia =  el.getValue();
      System.out.printf(el.getKey() + " : ");
      System.out.println(polaczenia);
    }

    System.out.println("Neurony:");
    for(Map.Entry<String, List<Polaczenie>> el : neurony.entrySet()){
      List<Polaczenie> polaczenia =  el.getValue();
      System.out.printf(el.getKey() + " : ");
      for(Polaczenie polaczenie : polaczenia){
        polaczenie.printPolaczenie();
      }
      System.out.printf("\n");
    }
  }
}

class WczytajZPliku{
  WczytajZPliku(){
  	//poki co tylko wczytuje i wypisuje na ekran pojedyncze wartosci z pliku
    System.out.println("---TEST ODCZYTU PLIKU--");
  	File plik = new File("data.txt");
    try{
  	  Scanner odczyt = new Scanner(plik);
    	StringTokenizer token;
    	while(odczyt.hasNextLine()){
    		token = new StringTokenizer(odczyt.nextLine(), " ");
    		while(token.hasMoreElements()){
    			System.out.printf(token.nextToken()+"\t");
    		}
        System.out.printf("\n");
    	}
    } catch(FileNotFoundException fnfe) {
      System.out.println(fnfe.getMessage());
    }
  }
}
