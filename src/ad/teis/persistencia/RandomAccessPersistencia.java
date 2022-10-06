/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.teis.persistencia;

import ad.teis.model.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfernandez
 */
public class RandomAccessPersistencia implements IPersistencia {

    @Override
    public void escribirPersona(Persona persona, String ruta) {

        try (
                 RandomAccessFile raf = new RandomAccessFile(ruta, "rw");) {
            raf.writeLong(persona.getId());
            StringBuilder sb = new StringBuilder(persona.getDni());
            sb.setLength(9);            
            raf.writeChars(sb.toString());
            
            raf.writeInt(persona.getEdad());
            raf.writeFloat(persona.getSalario());
            
            

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
    }

    @Override
    public Persona leerDatos(String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
