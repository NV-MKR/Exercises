import java.util.*;

class Main {

    public static void main(String[] args) {

        int[] lottoZahlen = new int[7];
        int stelle = 0;
        Random zufall = new Random();
        int min = 1;
        int max = 49;

        while (true) {
            if (stelle == lottoZahlen.length)
                break;

            int zufallsZahl = zufall.nextInt(max + min) + min;
            for (int i = 0; i <= stelle; i++) {
                if (lottoZahlen[i] == zufallsZahl)
                    break;
            }

            lottoZahlen[stelle] = zufallsZahl;
            stelle++;
        }

        System.out.println(Arrays.toString(lottoZahlen));
    }
}