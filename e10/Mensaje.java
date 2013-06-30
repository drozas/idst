//********************************************************************#
//  Autor: David Rozas
//  Mensaje.java      
//
//  Clase padre de Mensajes Cliente - Servidor
//********************************************************************#

import java.io.*;

public abstract class Mensaje implements Serializable {

    protected int tPrograma;

    //-----------------------------------------------------------------
    //  Construye un mensaje
    //-----------------------------------------------------------------
    public Mensaje (int tPrograma) 
    {
	this.tPrograma = tPrograma;
    }
    
  
    //-----------------------------------------------------------------
    //  Devuelve el atributo tPrograma del mensaje
    //-----------------------------------------------------------------
    public int getTPrograma()
    {
	int res= this.tPrograma;
	
	return res;
    }

   

    //-----------------------------------------------------------------
    //  Asigna un valor al atributo tPrograma del mensaje
    //-----------------------------------------------------------------
    public void setTPrograma(int tPrograma)
    {
	this.tPrograma=tPrograma;
    }
    

    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString() 
    {
	return "\t t. de programa : " + this.tPrograma + "\n";
    
    }
}
