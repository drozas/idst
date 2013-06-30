//*******************************************************************#
//  MontonDeBilletes.java   
//  
//  David Rozas
//  Representa un taco de billetes, con su valor y cantidad disponible
//*******************************************************************#

public class MontonDeBilletes implements Comparable
{
    private int valor;
    private int nBilletes;

   //-----------------------------------------------------------------
   //  Constructor del tipo MontonDeBilletes 
   //-----------------------------------------------------------------

    public MontonDeBilletes (int valor, int nBilletes)
    {
	this.valor=valor;
	this.nBilletes=nBilletes;
    }

   //-----------------------------------------------------------------
   //  Devuelve el valor de ese billete
   //-----------------------------------------------------------------

    public int getValor ()
    {
	int valorDevuelto=0;
	
	valorDevuelto= this.valor;
	return valorDevuelto;
    }

   //-----------------------------------------------------------------
   //  Devuelve el nº de billetes que quedan
   //-----------------------------------------------------------------

    public int getnBilletes ()
    {
	int valorDevuelto=0;
	
	valorDevuelto= this.nBilletes;
	return valorDevuelto;
    }

    //-----------------------------------------------------------------
   //  Asigna  un valor a ese billete
   //-----------------------------------------------------------------

   public void setValor (int valor)
   {
	  this.valor=valor;
	  
   }

   //-----------------------------------------------------------------
   //  Asigna el nº de billetes
   //-----------------------------------------------------------------

   public void setnBilletes (int nBilletes)
   {
	  this.nBilletes=nBilletes;
   }


   //-----------------------------------------------------------------
   //  Booleano que dice si nos quedan billetes de este tipo
   //-----------------------------------------------------------------
    
    public boolean quedanBilletes()
    {
	boolean valorDevuelto=true;
	
	valorDevuelto= !(this.nBilletes==0);

	return valorDevuelto;

    }


   //-----------------------------------------------------------------
   //  Compara en funcion del valor del billete
   //-----------------------------------------------------------------
   public int compareTo (Object otroBillete)
   {
      int valorDevuelto=0;

      //funcion compareTo, necesaria para cumplir la implementacion Comparable
      if (this.nBilletes==((MontonDeBilletes)otroBillete).nBilletes )
      {
	  valorDevuelto=0;
      }else if (this.nBilletes>((MontonDeBilletes)otroBillete).nBilletes )
      {
	  valorDevuelto=-1;
      }else{
	  valorDevuelto=1;
      }

      
      return valorDevuelto;
   }


    //------------------------------------------------------------------------
    //  Devuelve un string, con info de los billetes
    //-------------------------------------------------------------------------
    
    public String toString ()
    {
	String cadenaDevuelta="";


	cadenaDevuelta= this.nBilletes + " de " + this.valor + " euros.";
		
	return cadenaDevuelta;
    }

}