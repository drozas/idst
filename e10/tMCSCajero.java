//********************************************************************#
//  Autor: David Rozas
//  tMCSCajero.java      
//
//  Extiende la clase Cliente->Servidor, agregrando un atributo 
//  con el valor del billete insertado.
//
//********************************************************************#

public class tMCSCajero extends tMensajeCS
{

    protected int valorBillete;

    //-----------------------------------------------------------------
    //  Construye un mensaje
    //-----------------------------------------------------------------
    public tMCSCajero(int tPrograma, int valorBillete)
    {
	super(tPrograma);
	this.valorBillete= valorBillete;
    }

    //-----------------------------------------------------------------
    //  Devuelve el atributo valorBillete del mensaje
    //-----------------------------------------------------------------
    public int getValorBillete()
    {
	int res= this.valorBillete;
	
	return res;
    }

    //-----------------------------------------------------------------
    //  Asigna un valor al valor del Billete
    //-----------------------------------------------------------------
    public void setValorBillete(int valorBillete)
    {
	this.valorBillete= valorBillete;
    }

    //-----------------------------------------------------------------
    //  Devuelve un String con la info del mensaje
    //-----------------------------------------------------------------
    public String toString()
    {
	String res= super.toString();
	
	res+= "\t ValorBillete: " + this.valorBillete +"\n";
	
	return res;
    }
}
