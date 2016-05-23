import java.util.*;

/**
 * Created by Krzysztof on 2016-05-23.
 */
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
    }
    void wypiszNeuron(String objectNeuron){
        wypisaneObjectNeurons.add(objectNeuron);
        System.out.println(objectNeuron);
        List<PolaczenieWartosc> polaczenia = neurony.get(objectNeuron);
        if(polaczenia.size()>0){
            for(PolaczenieWartosc polaczenie : polaczenia){

                // System.out.println(containsPolaczenie(siec.wypisaneValueNeurons, polaczenie));

                if(!containsPolaczenie(wypisaneValueNeurons, polaczenie)){
                    wypiszNeuron(polaczenie);
                }
            }
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
        for(double key : sortowaneOdleglosci){
            for(PolaczenieWartosc el : odleglosci.get(key)){
                if(!containsPolaczenie(wypisaneValueNeurons, el)){
                    wypiszNeuron(el);
                }
            }
        }
    }

}
