//********************************************************************#
//  Autor: David Rozas
//  tMensajeCS.java      
//
//  Clase abstracta, hija de Mensaje, que engloba a los
//  mensajes de Cliente->Servidor
//********************************************************************#


public abstract class tMensajeCS extends Mensaje
{
    //-----------------------------------------------------------------
    //  Construye un mensaje de tipo Cliente -> Servidor
    //-----------------------------------------------------------------
    public tMensajeCS(int tPrograma)
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
