/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad.teis.tarea;

import ad.teis.model.Persona;
import ad.teis.persistencia.RandomAccessPersistencia;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author mfernandez
 */
public class Tarea01_1 {

    public static final String PERSONAS_FILE = Paths.get("src", "docs", "personasConBorrados.dat").toString();
    private static final String PERSONAS_FILE_BK = Paths.get("src", "docs", "personasConBorrados.dat.bk").toString();
    private static final String PERSONAS_FILE_DESTINO = Paths.get("src", "docs",
            "destinoRandom.dat.").toString();

    private static void cribarBorrados() {
       //implementa el método
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Persona> personasRecuperadas = new ArrayList<>();
        RandomAccessPersistencia random = new RandomAccessPersistencia();

        cribarBorrados();
        personasRecuperadas = random.leerTodo(PERSONAS_FILE_DESTINO);
        int contador = 1;
        for (Persona p : personasRecuperadas) {
            System.out.println("Persona recuperada " + contador + ": " + p);
            contador++;
        }

    }

}
