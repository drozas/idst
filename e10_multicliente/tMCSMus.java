//********************************************************************#
//  Autor: David Rozas
//  tMCSMus.java      
//
//  Extiende la clase Cliente->Servidor. Este es el tipo de mensajes
//  que solicita la ejecuci�n del programa.
//  (En realidad no aporta atributos nuevos, pero asi lo veo m�s claro)
//
//********************************************************************#

public class tMCSMus extends tMensajeCS
{
    //-----------------------------------------------------------------
    //  Construye un mensaje
    //-----------------------------------------------------------------
    public tMCSMus (int tPrograma)
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
