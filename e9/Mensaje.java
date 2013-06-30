//********************************************************************#
//  Autor: David Rozas
//  Mensaje.java      
//
//  Clase que envian Receptor y Emisor, con campos nombre y edad
//********************************************************************#

import java.io.*;



public class Mensaje implements Serializable {

    private String nombre;
    private int edad;

    //-----------------------------------------------------------------
    //  Construye un mensaje
    //-----------------------------------------------------------------
    public Mensaje (String nombre, int edad) {
	this.nombre = nombre;
	this.edad = edad;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el atributo nombre del mensaje
    //-----------------------------------------------------------------
    public String get_Nombre()
    {
	String res= this.nombre;
	return res;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el atributo edad del mensaje
    //-----------------------------------------------------------------
    public int get_Edad()
    {
	int res= this.edad;
	return res;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString() {
	return "Nombre: " + nombre + "    Edad: " + edad;
    }
}
