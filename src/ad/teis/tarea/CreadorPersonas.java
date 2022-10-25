/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.teis.tarea;

import ad.teis.model.Persona;
import ad.teis.persistencia.RandomAccessPersistencia;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class CreadorPersonas {

    private static ArrayList<Persona>  crearPersonas() {
        
        
        ArrayList<Persona> personas = new ArrayList<>();
        
        Persona personaA = new Persona(1, "12345678A",  20, 20000.65f, "Ana");
        Persona personaB = new Persona(2, "12345678B", 30, 30000.65f, "Bea");
        Persona personaC = new Persona(3, "12345678C", 40, 40000.65f , "Casilda");
        Persona personaD = new Persona(4, "12345678D", 50, 20000.65f, "Dionisio");
        Persona personaE = new Persona(5, "12345678E", 60, 30000.65f,  "Eva");
        Persona personaF = new Persona(6, "12345678F",  70, 40000.65f, "Flor");
        
        personaA.setBorrado(true);
        
        personaD.setBorrado(true);

        personas.add(personaA);
        personas.add(personaB);
        personas.add(personaC);
        personas.add(personaD);
        personas.add(personaE);
        personas.add(personaF);
        
        return personas;

    }

    public static void main(String[] args) {
          ArrayList<Persona> personas = crearPersonas();
          
          RandomAccessPersistencia raf = new RandomAccessPersistencia();
          raf.escribirPersonas(personas, Tarea01_1.PERSONAS_FILE);
    }
}
