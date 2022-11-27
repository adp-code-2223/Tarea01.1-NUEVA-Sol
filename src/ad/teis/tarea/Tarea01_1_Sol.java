/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad.teis.tarea;

import ad.teis.model.Persona;
import ad.teis.persistencia.DataIOPersistencia;
import ad.teis.persistencia.IPersistencia;
import static ad.teis.tarea.Tarea01_1_Sol.PERSONAS_ORIGEN_PATH;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 *
 * @author mfernandez
 */
public class Tarea01_1_Sol {

    public static final Path PERSONAS_ORIGEN_PATH = Paths.get("src", "docs", "origen.dat");
    private static final Path PERSONAS_ORIGEN_COPIA_PATH = Paths.get("src", "docs", "borrados", "origen.dat.bk");
    private static final Path PERSONAS_DESTINO_PATH = Paths.get("src", "docs",
            "destino.dat.");

    private static ArrayList<Persona> filtrarPersonas(ArrayList<Persona> personas) {
        ArrayList<Persona> personasSinBorrar = new ArrayList<>();
        for (Persona p : personas) {
            if (!p.isBorrado()) {
                personasSinBorrar.add(p);

            }
        }
        return personasSinBorrar;
    }

    private static void cribarBorrados() {
        ArrayList<Persona> personas = new ArrayList<>();
        ArrayList<Persona> personasSinBorrar = new ArrayList<>();
        //implementa el método

        try {
            IPersistencia diop = new DataIOPersistencia();

            personas = diop.leerTodo(PERSONAS_ORIGEN_PATH.toString());

            personasSinBorrar = filtrarPersonas(personas);

            if (Files.exists(PERSONAS_DESTINO_PATH)) {
                System.out.println("Deleting destino...");
                Files.delete(PERSONAS_DESTINO_PATH);

            }
            diop.escribirPersonas(personasSinBorrar, PERSONAS_DESTINO_PATH.toString());

            Files.move(PERSONAS_ORIGEN_PATH, PERSONAS_ORIGEN_COPIA_PATH, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Se ha producido una exception: " + ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Persona> personasRecuperadas = new ArrayList<>();
        IPersistencia daop = new DataIOPersistencia();
        if (Files.exists(PERSONAS_ORIGEN_PATH)) {

            cribarBorrados();
            personasRecuperadas = daop.leerTodo(PERSONAS_DESTINO_PATH.toString());
            int contador = 1;
            for (Persona p : personasRecuperadas) {
                System.out.println("Persona recuperada " + contador + ": " + p);
                contador++;
            }

        } else {
            System.out.println("No existe el fichero en la ruta: " + PERSONAS_ORIGEN_PATH);
        }
    }

}
