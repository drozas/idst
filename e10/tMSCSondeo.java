//********************************************************************#
//  Autor: David Rozas
//  tMSCSondeo.java      
//
//  Clase hija de  mensajes de Servidor->Cliente para
//  informar del precio del juego. Añade el atributo precio. 
//  
//********************************************************************#


public class tMSCSondeo extends tMensajeSC
{
    protected int precio;
    
    //-----------------------------------------------------------------
    //  Construye un mensaje de tipo Sondeo de respuesta al cliente
    //-----------------------------------------------------------------
    public tMSCSondeo(int tPrograma, int precio)
    {
	super(tPrograma);
	this.precio=precio;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el valor del atributo precio
    //-----------------------------------------------------------------    
    public int getPrecio()
    {
	int res= this.precio;
	
	return res;
    }
    
    
    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString()
    {
	String res= super.toString();
	res+= "\t Precio : " + this.precio +"\n";
	return res;
    }
}
