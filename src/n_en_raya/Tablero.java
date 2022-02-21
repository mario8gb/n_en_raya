package n_en_raya;

import java.util.Scanner;

public class Tablero {

    private char tablero[][];
    private int tam;

    Scanner sc = new Scanner(System.in);

    public Tablero(int tam) {
        this.tam = tam;
        tablero = new char[tam][tam];
    }

    public int getTam() {
        return tam;
    }

    public void mostrar_linea() {

        for (int i = 0; i < tam * 4 + 3; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void iniciar_tablero() {

        tablero = new char[tam][tam];

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    public void mostrar_tablero() {

        mostrar_linea();
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("\033[34m" + (i + 1) + "\033[0m" + " | ");

            for (int j = 0; j < tablero.length; j++) {

                switch (tablero[i][j]) {
                    case 'X':
                        System.out.print("\033[31m" + tablero[i][j] + "\033[0m" + " | ");
                        break;
                    case 'O':
                        System.out.print("\033[32m" + tablero[i][j] + "\033[0m" + " | ");
                        break;
                    default:
                        System.out.print(tablero[i][j] + " | ");
                }
            }
            System.out.println();
        }

        System.out.print("  ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("  " + "\033[34m" + (i + 1) + "\033[0m" + " ");
        }
        System.out.println();
        mostrar_linea();
    }

    public boolean comprobar(int jugador) {

        char id = Character.forDigit(jugador, 10);

        int cont_filas[] = new int[tam];
        int cont_cols[] = new int[tam];
        int cont_diags[] = new int[2];

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {

                if (tablero[i][j] == conversor(jugador)) {

                    if (i == j) {
                        cont_diags[0]++;
                    }

                    if (i + j == tam - 1) {
                        cont_diags[1]++;
                    }

                    cont_filas[i]++;
                    cont_cols[j]++;

                }
            }
        }

        for (int i = 0; i < tam; i++) {
            if (cont_filas[i] == tam) {
                return true;
            } else if (cont_cols[i] == tam) {
                return true;
            }
        }

        for (int i = 0; i < 2; i++) {
            if (cont_diags[i] == tam) {
                return true;
            }
        }

        return false;
    }

    public char conversor(int jugador) {

        switch (jugador) {
            case 0:
                return 'X';
            case 1:
                return 'O';
            default:
                System.out.println("No definido");

        }
        return '-';
    }

    public void poner_ficha(int jugador, Jugador[] jugadores) {

        int fila = leer_coor(jugador, "fila", jugadores);
        int columna = leer_coor(jugador, "columna", jugadores);

        movimiento(fila, columna, jugador, jugadores);

    }

    private int leer_coor(int jugador, String posicion, Jugador[] jugadores) {

        int coor;
        do {
            System.out.print("Jugador " + jugadores[jugador].getNombre() + " " + jugadores[jugador].getApellido() + " introduce la " + posicion + ": ");
            coor = sc.nextInt();

            if (coor > tam || coor <= 0) {
                System.out.println("Posicion invalida [ 1 - " + tam + " ] ");
            }

        } while (coor > tam || coor <= 0);

        return coor - 1;
    }

    private void movimiento(int fila, int columna, int jugador, Jugador[] jugadores) {

        if (tablero[fila][columna] != '-') {
            System.out.println("Esta posicion ya esta ocupada");
            poner_ficha(jugador, jugadores);
        } else {
            tablero[fila][columna] = conversor(jugador);
        }

    }
}
