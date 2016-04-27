import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Mapy {
  public static void main(String args[]) {
    System.out.println("Zaczynamy program!");
    TestowaMapa nowaMapa = new TestowaMapa();
  }
}

class TestowaMapa {
  TestowaMapa(){
    List<Integer> listaArgumentow = Arrays.asList(5,3,8,9,1,0,4,3,12,0);
    Map<Integer, List<Integer>> t_mapa = new HashMap<Integer, List<Integer>>();

    System.out.println("Oto nasza lista: " + listaArgumentow);

    for(int i=0; i<listaArgumentow.size();i++){ //Będziemy tworzyć mapę wszystkich neuronów
      int element = listaArgumentow.get(i);

      if(t_mapa.get(element)==null){ //Sprawdzamy, czy trzeba dodać neuron do mapy
        List<Integer> listaPolaczen = new ArrayList<Integer>();
        t_mapa.put(element, listaPolaczen); //Dodajemy neuron z pustą listą połączeń
      }
      else {
        // System.out.println("W naszej mapie jest juz klucz " + element);
      }

      if(i>0){
        for(int j=i-1; j>=0;j--){ //Dla każdego neuronu będziemy dodawać połączenia do wszystkich poprzednich
          List<Integer> lista_w_Mapie = t_mapa.get(element);
          Integer idPolaczenia = listaArgumentow.get(j);

          //Sprzwdzamy, czy połączenia już nie ma (w dalszej wersji programu będziemy wtedy mu zwiększać "wartość". Na razie po prostu nie duplikujemy)
          if(!lista_w_Mapie.contains(idPolaczenia)){
            lista_w_Mapie.add(idPolaczenia); //Dodajemy połączenie
          }
        }
      }

    }

    //Wypisywanie mapy:

    System.out.println("Oto nasza mapa:");
    for(Map.Entry<Integer, List<Integer>> el : t_mapa.entrySet()){
      System.out.println(el.getKey() + ":" + el.getValue());
    }

  }
}
