//********************************************************************#
//  Autor: David Rozas
//  tMSCMus.java      
//
//  Clase hija de Mensaje, para mensajes de Servidor->Cliente para
//  informar del resultado de la partida de mus.
//********************************************************************#


public class tMSCMus extends tMensajeSC
{
    protected String resultadoMus;
    
    //-----------------------------------------------------------------
    //  Construye un mensaje de tipo Mus de respuesta al cliente
    //-----------------------------------------------------------------
    public tMSCMus(int tPrograma,String resultadoMus)
    {
	super(tPrograma);
	this.resultadoMus= resultadoMus;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el valor del atributo resultadoMus
    //-----------------------------------------------------------------    
    public String getResultadoMus()
    {
	String res= this.resultadoMus;
	
	return res;
    }
    
    
    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString()
    {
	String res= super.toString();
	res+= "\t Resultado de ejecutar el mus : " + this.resultadoMus +"\n";
	return res;
    }
}
