/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirflota;

import java.util.Scanner;

/**
 * @author Francisco José Gordo Salguero Primero de Desarrollo de Aplicaciones
 * Web Programación Práctica: Práctica 1: Hundir la flota
 */
public class Jugador {

    private String nombre;

    public Jugador() {
        this.nombre = this.setNombre();
    }

    public Jugador(Jugador c) {
        this.nombre = c.nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String setNombre() {
        Scanner read = new Scanner(System.in);
        do {
            System.out.println("Dígame el nombre del jugador");
            this.nombre = read.next();
            if (this.nombre.length() < 3) {
                System.out.println("El nombre debe tener al menos 3 carácteres");
            };
        } while (this.nombre.length() < 3);
        return this.nombre;
    }
}
