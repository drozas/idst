//********************************************************************#
//  Autor: David Rozas
//  Me.java      
//
//  Clase hija de Mensaje, para mensajes de Servidor->Cliente para
//  informar de los billetes devueltos.
//  Añade el atributo billetes devueltos.Si el string está vacío
//es que la operación no fue posible
//********************************************************************#


public class tMSCCajero extends tMensajeSC
{
    protected String infoCambio;
    //protected Boolean operacionValida;
    
    //-----------------------------------------------------------------
    //  Construye un mensaje de tipo Sondeo de respuesta al cliente
    //-----------------------------------------------------------------
    public tMSCCajero(int tPrograma, String infoCambio)
    {
	super(tPrograma);
	this.infoCambio= infoCambio;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el valor del atributo infoCambio
    //-----------------------------------------------------------------    
    public String getInfoCambio()
    {
	String res= this.infoCambio;
	
	return res;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el valor del atributo operacionValida
    //-----------------------------------------------------------------    
//    public String getOperacionValida()
  //  {
	//Boolean res= this.operacionValida;
	
	//return res;
    //}

    //-----------------------------------------------------------------
    //  Asigna un valor al atributo operacionValida
    //-----------------------------------------------------------------    
    //public Boolean setOperacionValida(Boolean operacionValida)
    //{
	//this.operacionValida= operacionValida;
	
	//return res;
    //}
    
    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString()
    {
	String res= super.toString();
	res+= "\t InfoCambio : " + this.infoCambio +"\n" ;
	//res+= "\t operacionValida : " + this.operacionValida +"\n";
	return res;
    }
}
