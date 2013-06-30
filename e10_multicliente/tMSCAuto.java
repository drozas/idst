//********************************************************************#
//  Autor: David Rozas
//  tMSCAuto.java      
//
//  Clase hija de  mensajes de Servidor->Cliente para
//  informar del resultado de una partida de Autos Locos
//  
//********************************************************************#


public class tMSCAuto extends tMensajeSC
{
    protected String resultadoAutos;
    
    //-----------------------------------------------------------------
    //  Construye un mensaje de tipo Sondeo de respuesta al cliente
    //-----------------------------------------------------------------
    public tMSCAuto(int tPrograma,String resultadoAutos)
    {
	super(tPrograma);
	this.resultadoAutos= resultadoAutos;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el valor del atributo resultadoAutos
    //-----------------------------------------------------------------    
    public String getResultadoAutos()
    {
	String res= this.resultadoAutos;
	
	return res;
    }
    
    
    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString()
    {
	String res= super.toString();
	res+= "\t Resultado de la ejecucion de autos Locos : " + this.resultadoAutos +"\n";
	return res;
    }
}
