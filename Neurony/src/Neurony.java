/**
 * Created by Krzysztof on 2016-05-22.
 */
public class Neurony {
    public static void main(String args[]) {

        double[] zapalarka = {1, 1.5, 3, 2, 9, 2.1};

        WczytajZPliku test = new WczytajZPliku();
        SiecNeuronowa nowaSiec = new SiecNeuronowa(test.tablica);
        nowaSiec.rozpal(zapalarka);
    }
}
