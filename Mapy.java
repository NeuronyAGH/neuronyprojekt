import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mapy {
  public static void main(String args[]) {
    TestowaMapa nowaMapa = new TestowaMapa();
  }
}

class Punkt {
  int x;
  int y;
  void print(){
    System.out.printf("["+x + ":" + y+"]");
  }
}

class TestowaMapa {
  TestowaMapa(){
    List<Integer> listaArgumentow = Arrays.asList(5,3,8,9,1,0,4,3,12,0);
    Map<String, List<Double>> neurony = new HashMap<String, List<Double>>();
    Map<String, List<Double>> kolumny = new HashMap<String, List<Double>>();


    double[][] tablicaArgumentow = new double[][]{
      { 0.1, 3, 4.5, 6, 8 },
      { 1, 1.5, 2, 2.2, 2.7 },
      { 0.3, 1, 6, 10, 10.2 },
      { 1, 0.5, 1.2, 2, 10 },
      { 0.4, 1, 5, 7, 8 },
    };

    System.out.println("Oto nasza tablica wejściowa: ");
    for(int i = 0; i<tablicaArgumentow.length; i++ ){
      for(int j = 0; j< tablicaArgumentow[i].length; j++){
        System.out.printf(tablicaArgumentow[i][j] + " ");
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
      List<Double> wartosci = new ArrayList<Double>();
      neurony.put("O"+i, wartosci);

      for(int j=0;j<tablicaArgumentow[i].length;j++){
        neurony.get("O"+i).add(tablicaArgumentow[i][j]);
      }
    }

    System.out.println("Kolumny:");
    for(Map.Entry<String, List<Double>> el : kolumny.entrySet()){
      List<Double> polaczenia =  el.getValue();
      System.out.printf(el.getKey() + " : ");
      System.out.println(polaczenia);
    }

    System.out.println("Neurony:");
    for(Map.Entry<String, List<Double>> el : neurony.entrySet()){
      List<Double> polaczenia =  el.getValue();
      System.out.printf(el.getKey() + " : ");
      System.out.println(polaczenia);
    }



  //
  //   for(int i = 0; i<tablicaArgumentow.length; i++ ){
  //     for(int j = 0; j< tablicaArgumentow[i].length; j++){
  //
  //       double neuron = tablicaArgumentow[i][j];
  //
  //       if(neurony.get(neuron)==null){ //Sprawdzamy, czy trzeba dodać neuron do mapy
  //         List<Punkt> pustaLista = new ArrayList<Punkt>();
  //         neurony.put(neuron, pustaLista); //Dodajemy neuron z pustą listą połączeń
  //         Punkt wspolrzedne = new Punkt();
  //         wspolrzedne.x = i;
  //         wspolrzedne.y=j;
  //
  //         neurony.get(neuron).add(wspolrzedne);
  //       }
  //       else {
  //
  //         Punkt wspolrzedne = new Punkt();
  //         wspolrzedne.x = i;
  //         wspolrzedne.y=j;
  //
  //         neurony.get(neuron).add(wspolrzedne);
  //       }
  //     }
  //   }
  //
  //   //Wypisywanie mapy:
  //
  //   System.out.println("Oto nasza mapa:");
  //   for(Map.Entry<Double, List<Punkt>> el : neurony.entrySet()){
  //     List<Punkt> polaczenia =  el.getValue();
  //     System.out.printf(el.getKey() + " : ");
  //     for(int i=0;i<polaczenia.size();i++){
  //       polaczenia.get(i).print();
  //       System.out.printf(" ");
  //     }
  //     System.out.printf("\n");
  //   }
  //
  }
}
