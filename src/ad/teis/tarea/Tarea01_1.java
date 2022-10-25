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
        if (Files.exists(Paths.get(PERSONAS_FILE))) {

            try {
                Files.copy(Paths.get(PERSONAS_FILE), Paths.get(PERSONAS_FILE_BK), StandardCopyOption.REPLACE_EXISTING);

                RandomAccessPersistencia random = new RandomAccessPersistencia();

                ArrayList<Persona> personas = random.leerTodo(PERSONAS_FILE);

                ArrayList<Persona> personasNoBorradas = (ArrayList<Persona>) personas.stream().filter(p -> !p.isBorrado()).collect(Collectors.toList());
                System.out.println("total: " + personas.size() + " no borradas: " + personasNoBorradas.size());

                int pos = 1;
                for (Persona personaNoBorrada : personasNoBorradas) {
                    random.add(pos, PERSONAS_FILE_DESTINO, personaNoBorrada);
                    pos++;
                }

                Files.delete(Paths.get(PERSONAS_FILE));

            } catch (IOException ex) {
               // Logger.getLogger(Tarea01_1.class.getName()).log(Level.SEVERE, null, ex);
               ex.printStackTrace();
                System.out.println("SE ha producido una exception: " + ex.getMessage());
            }
        }
      
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
