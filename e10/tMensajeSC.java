//********************************************************************#
//  Autor: David Rozas
//  tMensajeSC.java      
//
//  Clase abstracta hija de Mensaje, que engloba los 
//  mensajes de Servidor->Cliente
//********************************************************************#


public abstract class tMensajeSC extends Mensaje
{
    //-----------------------------------------------------------------
    //  Construye un mensaje de tipo Servidor->Cliente
    //-----------------------------------------------------------------
    public tMensajeSC(int tPrograma)
    {
	super(tPrograma);
    }

    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString()
    {
	String res= super.toString();
	return res;
    }

}
