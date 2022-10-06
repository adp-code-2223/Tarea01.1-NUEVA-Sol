/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad.teis;

import ad.teis.model.Persona;
import ad.teis.persistencia.DataIOPersistencia;
import ad.teis.persistencia.IPersistencia;

/**
 *
 * @author mfernandez
 */
public class UD1_Actividad13 {

    private static final String PERSONA_FILE = "persona.dat"; 
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        IPersistencia dataIOPersistencia = new DataIOPersistencia();
//        Persona persona = new Persona(1, "12345678A", 18, 20000.65f);
//        
//        dataIOPersistencia.escribirPersona(persona, PERSONA_FILE);
//        
      Persona personaRecuperada=  dataIOPersistencia.leerDatos(PERSONA_FILE);
      System.out.println("Se ha recuperado: " + personaRecuperada);
        
    }
    
}
