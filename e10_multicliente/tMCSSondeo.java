//********************************************************************#
//  Autor: David Rozas
//  tMCSSondeo.java      
//
//  Este tipo de mensajes son los que envia el Cliente al Servidor
//  para decirle que tipo de juego va a querer.
//  (En realidad no aporta atributos nuevos, pero asi lo veo más claro) 
//
//********************************************************************#

public class tMCSSondeo extends tMensajeCS
{

    //-----------------------------------------------------------------
    //  Construye un mensaje de sondeo  tipo Cliente -> Servidor
    //-----------------------------------------------------------------
    public tMCSSondeo(int tPrograma)
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
