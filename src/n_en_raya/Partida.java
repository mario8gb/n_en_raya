package n_en_raya;

import java.util.Scanner;

public class Partida {

    private Jugador jugadores[];
    private Tablero t1;
    private boolean fin;
    private int id, tam;
    private int turno, fichas_puestas;
    private String resultado;

    Scanner sc = new Scanner(System.in);

    public Partida(int id, int tam) {
        this.fin = false;
        this.id = id;
        this.tam = tam;
        this.turno = 0;
        this.fichas_puestas = 0;
        crear_partida();
    }

    public boolean isFin() {
        return fin;
    }

    public int getId() {
        return id;
    }

    public String getResultado() {
        return resultado;
    }

    private String leer_nombre(int id, String opcion) {

        String nombre;
        if ("nombre".equals(opcion)) {
            System.out.println("Hola jugador " + id + " ¿Como te llamas?");
        } else {
            System.out.println("¿Como te apellidas?");
        }
        nombre = sc.next();
        return nombre;
    }

    private void crear_partida() {
        System.out.println("Rellena datos de la partida: " + id);

        String nombre;
        String apellido;
        // Tablero
        t1 = new Tablero(tam);
        t1.iniciar_tablero();
        //Jugadores datos
        jugadores = new Jugador[2];
        for (int i = 0; i < jugadores.length; i++) {
            nombre = leer_nombre(i + 1, "nombre");
            apellido = leer_nombre(i + 1, "apellido");
            jugadores[i] = new Jugador(nombre, apellido);
        }
        System.out.println("¿Que jugador va ha empezar?");
        turno = (sc.nextInt() - 2);

    }

    public void jugar() {

        turno = (turno + 1) % jugadores.length;
        t1.mostrar_tablero();
        t1.poner_ficha(turno, jugadores);
        fichas_puestas++;

        if (!(!t1.comprobar(turno) && fichas_puestas != Math.pow(t1.getTam(), 2))) {
            fin = true;
            t1.mostrar_tablero();
            if (t1.comprobar(turno)) {  
                System.out.println(jugadores[turno].getNombre() + " " + jugadores[turno].getApellido() + " ha ganado");
                resultado = (jugadores[turno].getNombre() + " " + jugadores[turno].getApellido());
            } else {
                System.out.println("Empate");
                resultado = "Empate";
            }
            System.out.println("FIN");
        }
    }
}
