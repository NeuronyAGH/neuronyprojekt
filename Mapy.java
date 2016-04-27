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
    List<Integer> listaArgumentow = Arrays.asList(5,3,8,9,1,0,4);
    Map<Integer, List<Integer>> t_mapa = new HashMap<Integer, List<Integer>>();

    System.out.println("Oto nasza lista: " + listaArgumentow);


    for(Integer el : listaArgumentow){
      List<Integer> listaPolaczen = new ArrayList<Integer>();
      t_mapa.put(el, listaPolaczen);
    }

    System.out.println("Oto nasza mapa: " + t_mapa);

  }
}
