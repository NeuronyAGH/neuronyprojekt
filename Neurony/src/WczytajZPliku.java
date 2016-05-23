import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Krzysztof on 2016-05-23.
 */
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
