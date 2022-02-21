package n_en_raya;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Cuantas partidas van a jugar: ");

        Partida[] partidas = new Partida[sc.nextInt()];

        System.out.println("¿Que tamaño quieres?");
        int tam = sc.nextInt();
        //Crear partidas y rellenar sus datos
        for (int i = 0; i < partidas.length; i++) {
            partidas[i] = new Partida(i + 1, tam);
        }

        int partida_actual = 0;
        do {
            if (!partidas[partida_actual].isFin()) {
                System.out.println("\033[34m" + "Partida " + partidas[partida_actual].getId() + "\033[0m");
                partidas[partida_actual].jugar();
            }
            partida_actual = (partida_actual + 1) % partidas.length;
        } while (!comprobarPartidas(partidas));
        //Mostrar marcador
        for (int i = 0; i < partidas.length; i++) {
            System.out.print("Resultado partida " + (i + 1) + ": " + partidas[i].getResultado());
            if (!partidas[i].getResultado().equals("Empate")) {
                System.out.println(" ha ganado");
            }else{
                System.out.println();
            }
        }
    }

    public static boolean comprobarPartidas(Partida[] partidas) {
        boolean prueba = true;
        for (int i = 0; i < partidas.length; i++) {
            if (!partidas[i].isFin()) {
                prueba = false;
            }
        }
        return prueba;
    }

}
