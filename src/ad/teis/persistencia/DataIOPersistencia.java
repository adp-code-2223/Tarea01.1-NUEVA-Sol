/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.teis.persistencia;

import ad.teis.model.Persona;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mfernandez
 */
public class DataIOPersistencia implements IPersistencia {


    public void escribirPersonas(ArrayList<Persona> personas, String ruta) {
        if (personas != null) {

            try ( FileOutputStream fos = new FileOutputStream(ruta, true);  DataOutputStream dos = new DataOutputStream(fos);) {

                for (Persona persona : personas) {

                    dos.writeLong(persona.getId());
                    dos.writeChars(persona.getDni());
                    StringBuilder sb = new StringBuilder(persona.getNombre());
                    sb.setLength(Persona.MAX_LENGTH_NOMBRE);
                    dos.writeChars(sb.toString());
                    dos.writeInt(persona.getEdad());
                    dos.writeFloat(persona.getSalario());
                    dos.writeBoolean(persona.isBorrado());
                }

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Ha ocurrido una excepción: " + ex.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Ha ocurrido una excepción: " + ex.getMessage());
            }
        }

    }

    @Override
    public ArrayList<Persona> leerTodo(String ruta) {
        long id = 0;
        char caracter;
        String dni = "";
        StringBuilder sb = new StringBuilder();
        String nombre = "";
        int edad = 0;
        float salario = 0;
        boolean borrado = false;
        ArrayList<Persona> personas = new ArrayList<>();
        Persona persona = null;

        try ( FileInputStream fis = new FileInputStream(ruta);  DataInputStream dis = new DataInputStream(fis);) {

            while (true) {

                try {
                    id = dis.readLong();

                    sb.delete(0, sb.length());
                    for (int i = 0; i < Persona.MAX_LENGTH_DNI; i++) {
                        caracter = dis.readChar();
                        sb.append(caracter);
                    }
                    dni = sb.toString();

                    sb.delete(0, sb.length());

                    for (int i = 0; i < Persona.MAX_LENGTH_NOMBRE; i++) {
                        caracter = dis.readChar();
                        sb.append(caracter);
                    }
                    nombre = sb.toString();

                    edad = dis.readInt();
                    salario = dis.readFloat();
                    borrado = dis.readBoolean();

                    persona = new Persona(id, dni, edad, salario, nombre);
                    persona.setBorrado(borrado);

                    personas.add(persona);
                } catch (EOFException eofex) {
                  
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Ha ocurrido una excepción: " + ex.getMessage());
        }

        return personas;
    }
}
