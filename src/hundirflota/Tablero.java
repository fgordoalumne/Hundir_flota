/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirflota;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Francisco José Gordo Salguero Primero de Desarrollo de Aplicaciones
 * Web Programación Práctica: Práctica 1: Hundir la flota
 */
public class Tablero {

    private int tamano;
    private String[][] tablero;

    public Tablero() {
        this.tamano = setTamano();
        this.tablero = setTablero();
    }
// Construimos el tablero contrincante del mismo tamaño que el del jugador
    // 
    public Tablero(int tamano) {
        this.tamano = tamano;
        this.tablero = setTablero();
    }
// Constructor copia

    public Tablero(Tablero tablero) {
        this.tamano = tablero.tamano;
        this.tablero = tablero.setTablero();
    }

    public String[][] getTablero() {
        return this.tablero;
    }

    public String getCasilla(int fila, int columna) {
        return tablero[fila][columna];
    }

    // Dibuja el tablero
    public void mostrar_Tablero() {
        // Dibuja la cabecera de coordenadas de las columnas
        System.out.print("  ");
        for (int i = 0; i < this.tamano; i++) {
            System.out.print(" " + i);
        }
        // Dibuja el resto del tablero
        System.out.println();
        int contador = 0;
        for (int i = 0; i < this.tamano; i++) {
            System.out.print(contador + " ");
            for (int j = 0; j < this.tamano; j++) {
                if (this.tablero[i][j] != " ") {
                    if(this.tablero[i][j] == "X"){
                        System.out.print("|"+"X");
                    }
                    else if(this.tablero[i][j] == "0"){
                        System.out.print("|"+"0");
                    }
                    else{
                    System.out.print("|" + "#");}
                } else {
                    System.out.print("|" + this.tablero[i][j]);
                };
            }
            System.out.println("|");
            contador++;
        }
    }

    public void mostrarTableroContrincante() {
        // Dibuja la cabecera de coordenadas de las columnas
        System.out.print("  ");
        for (int i = 0; i < this.tamano; i++) {
            System.out.print(" " + i);
        }
        // Dibuja el resto del tablero
        System.out.println();
        int contador = 0;
        for (int i = 0; i < this.tamano; i++) {
            System.out.print(contador + " ");
            for (int j = 0; j < this.tamano; j++) {
                System.out.print("|" + this.tablero[i][j]);
            }
            System.out.println("|");
            contador++;
        }
    }

// Crea un tablero vacío
    public String[][] setTablero() {
        this.tamano = getTamano();
        this.tablero = new String[tamano][tamano];
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                this.tablero[i][j] = " ";
            }
        }
        return this.tablero;
    }

    // Cambia el valor de la casilla del tablero

    public void setintroducirbarco(int fila, int columna, String barco) {
        this.tablero[fila][columna] = barco;
    }

    public int getTamano() {
        return tamano;
    }

    public int setTamano() {
        Scanner read = new Scanner(System.in);
        int tamano = 5;
        do {
            System.out.println("Dígame el tamaño del tablero");
            this.tamano = read.nextInt();
            if (this.tamano < tamano) {
                System.out.println("El tamaño del tablero debe ser como mínimo"
                        + " " + tamano);
            }
        } while (this.tamano < tamano);
        return this.tamano;

    }

}
