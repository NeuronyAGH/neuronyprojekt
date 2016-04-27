import java.util.List;
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
    Map<Integer, String> t_mapa = new HashMap<Integer, String>();

    System.out.println("Oto nasza lista: " + listaArgumentow);


    for(Integer el : listaArgumentow){
      t_mapa.put(el,"TestowyStringnumer"+el);
    }

    System.out.println("Oto nasza mapa: " + t_mapa);

  }
}
