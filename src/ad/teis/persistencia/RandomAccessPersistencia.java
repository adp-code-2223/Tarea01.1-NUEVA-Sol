/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.teis.persistencia;

import ad.teis.model.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
            //raf.writeUTF(sb.toString());

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

        long id = 0;
        String dni = "";
        int edad = 0;
        float salario = 0;
        StringBuilder sb = new StringBuilder();
        Persona persona = null;
        try (
                 RandomAccessFile raf = new RandomAccessFile(ruta, "r");) {

            id = raf.readLong();
            for (int i = 0; i <= 8; i++) {
                sb.append(raf.readChar());
            }

            dni = sb.toString();

            edad = raf.readInt();
            salario = raf.readFloat();

            persona = new Persona(id, dni, edad, salario);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
        return persona;

    }

}
