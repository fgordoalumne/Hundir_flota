/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirflota;
//Hola Eugenio
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Francisco José Gordo Salguero Primero de Desarrollo de Aplicaciones
 * Web Programación Práctica: Práctica 1: Hundir la flota
 */
public class Hundirflota {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        inicializarJuego();
    }

    public static void inicializarJuego() {

        System.out.println("Jugador1");
        Jugador Jugador1 = new Jugador();
        System.out.println("Jugador2");
        Jugador Jugador2 = new Jugador();
        // Contruimos el tablero del jugador 1
        Tablero tablero_jugador11 = new Tablero();
        tablero_jugador11 = ubicarBarcos(tablero_jugador11);
        // Construimos el tablero del jugador 2 
        // con el mismo tamaño que el del jugador 1
        Tablero tablero_jugador21 = new Tablero(tablero_jugador11.getTamano());
        System.out.println("Jugador: " + Jugador2.getNombre());
        tablero_jugador21 = ubicarBarcos(tablero_jugador21);

        // Contruimos los tableros auxiliares con constructores copia
        // para así utilizarlos para las jugadas
        Tablero tablero_jugador12 = new Tablero(tablero_jugador11.getTamano());
        Tablero tablero_jugador22 = new Tablero(tablero_jugador21.getTamano());

        /*System.out.println("Tablero de " + Jugador1.getNombre());
         tablero_jugador11.dibujar_tablero();// Dibujamos los dos tableros
         System.out.println("Tablero de " + Jugador2.getNombre());
         tablero_jugador21.dibujar_tablero();*/
        jugarFlota(Jugador1, tablero_jugador11, tablero_jugador12, Jugador2,
                tablero_jugador21, tablero_jugador22);
        
    }

    public static Tablero ubicarBarcos(Tablero tablero) {
        int tamano = tablero.getTamano();
        tablero.mostrar_Tablero();
        for (int i = tablero.getTamano() - 1; i > 1; i--) {

            int[] coordenada = validarPosicion(tablero, i);
            int fila = coordenada[0];
            int columna = coordenada[1];
            int orientacion = coordenada[2];

            for (int j = 0; j < i; j++) {
                if (orientacion == 0) {
                    tablero.setintroducirbarco(fila + j, columna,
                            Integer.toString(i));
                } else {
                    tablero.setintroducirbarco(fila, columna + j,
                            Integer.toString(i));
                }
            }
            tablero.mostrar_Tablero();
        }

        return tablero;
    }

    public static int[] validarPosicion(Tablero tablero, int tamaño_barco) {
        Scanner reader = new Scanner(System.in);
        int fila;
        int columna;
        boolean salir;
        int contador;
        char orientacion = 0;

        do {
            contador = 0;
            salir = false;
            System.out.println("Donde quiere ubicar el barco de tamaño "
                    + tamaño_barco);
            fila = fila();
            columna = columna();
            orientacion = peticion_orientacion(fila, columna, tamaño_barco,
                    tablero);
            salir = comprobacion(fila, columna, orientacion, tamaño_barco,
                    tablero);

        } while (!salir);

        if (orientacion == 'V' || orientacion == 'v') {
            orientacion = 0;
        } else {
            orientacion = 1;
        }
        int[] coordenada = {fila, columna, orientacion};
        return coordenada;
    }

    public static int fila() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Indique la fila");
        return reader.nextInt();
    }

    public static int columna() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Indique la columna");
        return reader.nextInt();
    }

    public static char peticion_orientacion(int fila, int columna,
            int tamaño_barco, Tablero tablero) {
        Scanner reader = new Scanner(System.in);
        char peticion_orientacion;
        if (tamaño_barco + fila > tablero.getTamano()) {
            peticion_orientacion = 'H';
        } else if (tamaño_barco + columna > tablero.getTamano()) {
            peticion_orientacion = 'V';
        } else {
            System.out.println("Que orientación desea? (V,H)");
            peticion_orientacion = reader.next().charAt(0);
        };
        return peticion_orientacion;
    }

    public static boolean comprobacion(int fila, int columna, char orientacion,
            int tamaño_barco, Tablero tablero) {
        ArrayList<String> recorrido = new ArrayList<String>();
        ArrayList<String> comprobacion = new ArrayList<String>();
        boolean cabe = false;
        for (int i = 0; i < tamaño_barco; i++) {
            comprobacion.add(" ");
        };
        if (comprobar_si_cabe(fila, columna, tamaño_barco, tablero)
                && comprobar_si_cabe_HV(fila, columna, tamaño_barco, tablero,
                        orientacion)) {
            for (int i = 0; i < tamaño_barco; i++) {
                if (orientacion == 'V') {
                    recorrido.add(tablero.getCasilla(fila + i, columna));
                } else {
                    recorrido.add(tablero.getCasilla(fila, columna + i));
                }
            }
            if (recorrido.equals(comprobacion)) {
                cabe = true;
            } else {
                System.out.println("Esa posición ya esta ocupada");
                System.out.println("Vuelva a introducir otras coordenadas");
                tablero.mostrar_Tablero();
                cabe = false;
            };
        } else {
            System.out.println("Esa posición no es válida");
            System.out.println("Vuelva a introducir otras coordenadas");
            cabe = false;
        };
        return cabe;
    }

    public static boolean comprobar_si_cabe(int fila, int columna,
            int tamaño_barco, Tablero tablero) {
        Scanner reader = new Scanner(System.in);
        while (fila >= tablero.getTamano() || fila < 0
                || columna >= tablero.getTamano() || columna < 0) {
            System.out.println("Esa posición no es válida");
            tablero.getTablero();
            System.out.println("Indique otra vez la fila");
            fila = reader.nextInt();
            System.out.println("Indique otra vez la columna");
            columna = reader.nextInt();
        };
        return true;
    }

    public static boolean comprobar_si_cabe_HV(int fila, int columna,
            int tamaño_barco, Tablero tablero, char orientacion) {
        boolean salir = false;
        if (orientacion == 'V' || orientacion == 'v' && tamaño_barco + fila < tablero.getTamano()) {
            salir = true;
        } else if (orientacion == 'H' || orientacion == 'h' && tamaño_barco + columna
                < tablero.getTamano()) {
            salir = true;
        };
        return salir;
    }

    /* El método jugarFlota se encarga de otorgar el control cada vez a un 
     jugador y a partir de ahí mostrar un menú de juego. */
    public static void jugarFlota(Jugador Jugador1, Tablero tablero_jugador11,
            Tablero tablero_jugador12, Jugador Jugador2,
            Tablero tablero_jugador21, Tablero tablero_jugador22) {
        int ganador;
        Jugador[] lista_jugadores = {Jugador1, Jugador2};
        Tablero[][] lista_tableros = {{tablero_jugador11, tablero_jugador12},
        {tablero_jugador21, tablero_jugador22}};

        Jugador jugador_activo;
        boolean ganarpartida = false;
        int i = 0;

        do {
            boolean hundido = false;
            System.out.println("Juega el jugador: "
                    + lista_jugadores[i % 2].getNombre());
            jugador_activo = lista_jugadores[i % 2];
            hundido = mostrarMenu(jugador_activo, lista_tableros, i);
            i++;
            if (hundido) {
                ganarpartida = ganarpartida(lista_tableros, i);
            }

        } while (!ganarpartida);
        if(i%2==0){
            ganador=1;
        }
        else{
            ganador=0;
        }
        System.out.println("Ha ganado " + lista_jugadores[ganador].getNombre());
        System.out.println("¡Felicidades!");
    }

    public static boolean mostrarMenu(Jugador jugador_activo,
            Tablero[][] lista_tableros, int i) {
        Scanner reader = new Scanner(System.in);
        int opcion;
        boolean tirada = false;
        boolean hundido;
        hundido = false;

        do {
            Menu();
            opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    lista_tableros[i % 2][1].mostrarTableroContrincante();
                    break;
                case 2:
                    lista_tableros[i % 2][0].mostrar_Tablero();
                    break;
                case 3:
                    if (!tirada) {
                        hundido = introducirTirada(jugador_activo,
                                lista_tableros, i);
                        tirada = true;
                    } else {
                        System.out.println("Ya ha hecho una tirada,"
                                + "no puede volver a tirar");
                    }
                    break;
            }

        } while (opcion != 4);
        return hundido;
    }

    public static void Menu() {
        System.out.println("1) Mostrar Tablero Contrincante");
        System.out.println("2) Mostrar Tablero");
        System.out.println("3) Introducir Tirada");
        System.out.println("4) Salir");
        System.out.print("Elija la acción que desea hacer: ");
    }

    public static boolean introducirTirada(Jugador jugador_activo,
            Tablero[][] lista_tableros, int i) {
        Tablero tablero_contrario;
        boolean hundido = false;
        lista_tableros[i % 2][1].mostrarTableroContrincante();
        int fila = fila();
        int columna = columna();
        if (i % 2 == 0) {
            tablero_contrario = lista_tableros[1][0];
        } else {
            tablero_contrario = lista_tableros[0][0];
        };
        if (tablero_contrario.getCasilla(fila, columna) != " ") {
            if (tablero_contrario.getCasilla(fila, columna) == "X"
                    || tablero_contrario.getCasilla(fila, columna) == "0") {
                System.out.println("Ya ha introducido una tirada "
                        + "anteriormente en esa casilla");
            } else {

                lista_tableros[i % 2][1].setintroducirbarco(fila, columna, "X");
                String casilla = tablero_contrario.getCasilla(fila, columna);
                tablero_contrario.setintroducirbarco(fila, columna, "X");
                if (hundido(casilla, tablero_contrario)) {
                    System.out.println("Tocado");
                } else {
                    System.out.println();
                    System.out.println("¡Hundido!");
                    System.out.println();
                    hundido = true;
                };

            }
        } else {
            System.out.println("Agua");
            lista_tableros[i % 2][1].setintroducirbarco(fila, columna, "0");
            tablero_contrario.setintroducirbarco(fila, columna, "0");
        };
        return hundido;
    }

    public static boolean hundido(String casilla, Tablero tablero_contrario) {

        int i = 0;
        boolean encontrado = false;
        while (i < tablero_contrario.getTamano() && !encontrado) {
            int j = 0;
            while (j < tablero_contrario.getTamano() && !encontrado) {
                if (casilla != "X" && casilla != "0" && casilla != " "
                        && tablero_contrario.getCasilla(i, j) != "X"
                        && tablero_contrario.getCasilla(i, j) != "0"
                        && tablero_contrario.getCasilla(i, j) != " ") {
                    if (Integer.parseInt(casilla)
                            == Integer.parseInt(tablero_contrario.getCasilla(i, j))) {
                        encontrado = true;
                    }
                }
                j++;

            }
            i++;
        }

        return encontrado;
    }

    public static boolean ganarpartida(Tablero[][] tableros, int turno) {
        boolean ganar = false;
        boolean barco = false;
        int i = 0;
        int j;

        Tablero tablero_contrario;
        if (i % 2 == 0) {
            tablero_contrario = tableros[1][0];
        } else {
            tablero_contrario = tableros[0][0];
        };
        while (i < tablero_contrario.getTamano() && !barco) {
            j=0;
            while (j < tablero_contrario.getTamano() && !barco) {
                if (tablero_contrario.getCasilla(i, j) != " "
                        && tablero_contrario.getCasilla(i, j) != "X"
                        && tablero_contrario.getCasilla(i, j) != "0") {
                    barco = true;
                }
                j++;
            }
            i++;
        }
        if (!barco) {
            ganar = true;
        }
        return ganar;
    }
}
