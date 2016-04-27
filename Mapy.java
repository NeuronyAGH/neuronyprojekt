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


    for(Integer el : listaArgumentow){
      if(t_mapa.get(el)==null){
        List<Integer> listaPolaczen = new ArrayList<Integer>();
        t_mapa.put(el, listaPolaczen);
      }
      else {
        System.out.println("W naszej mapie jest juz klucz " + el);
      }
    }

    System.out.println("Oto nasza pusta mapa z miejscem na dodawanie polaczen: " + t_mapa);

  }
}
