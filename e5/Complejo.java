//********************************************************************
//  Complejo.java   
//  
//  David Rozas
//  Implementa la clase numero complejo
//********************************************************************

public class Complejo
{
    
    private int pReal;
    private int pImaginaria;


    //-----------------------------------------------------------------
   //  Constructor del tipo complejo
   //-----------------------------------------------------------------

    public Complejo (int pReal, int pImaginaria)
    {
	this.pReal=pReal;
	this.pImaginaria=pImaginaria;
    }

   //-----------------------------------------------------------------
   //  Devuelve la parte real
   //-----------------------------------------------------------------

    public int parteReal ()
    {
	int realDevuelto=0;
	
	realDevuelto= this.pReal;
	return realDevuelto;
    }

   //-----------------------------------------------------------------
   //  Devuelve la parte imaginaria
   //-----------------------------------------------------------------

    public int parteImaginaria ()
    {
	int imagDevuelto=0;
	
	imagDevuelto= this.pImaginaria;
	return imagDevuelto;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve un booleano, que nos dice si es Real puro
    //-----------------------------------------------------------------
    
    public boolean esRealPuro ()
    {
	boolean esPuro=false;
	    
	esPuro= pImaginaria==0;
	return esPuro;
    }


    //-----------------------------------------------------------------
    //  Devuelve un booleano, que nos dice si es Imaginario puro
    //-----------------------------------------------------------------
    
    public boolean esImaginarioPuro ()
    {
	boolean esPuro=false;
	    
	esPuro= pReal==0;
	return esPuro;
	
    }

    //-----------------------------------------------------------------
    //  Devuelve un booleano, si ambos numeros complejos son iguales
    //-----------------------------------------------------------------

    public boolean equals(Complejo nComplejo)
    {
	boolean sonIguales=false;

	//Son iguales, solo si los son sus dos partes
	sonIguales= ((this.pReal==nComplejo.parteReal()) && (this.pImaginaria==nComplejo.parteImaginaria()));


	
	return sonIguales;
	
    }

    //------------------------------------------------------------------------
    //  Realiza la suma de dos numeros complejos, devolviendo en otro complejo
    //-------------------------------------------------------------------------

    public Complejo suma(Complejo nComplejo)
    {
	//Realizamos la resta de ambas partes, y la metemos en el complejo devuelto
	Complejo complejoRes= new Complejo(this.pReal + nComplejo.parteReal(),
					   this.pImaginaria + nComplejo.parteImaginaria()) ;
	return complejoRes;
    }



    //------------------------------------------------------------------------
    //  Realiza la resta de dos numeros complejos, devolviendo en otro complejo
    //-------------------------------------------------------------------------

    public Complejo resta(Complejo nComplejo)
    {
	//Realizamos la resta de ambas partes, y la metemos en el complejo devuelto
	Complejo complejoRes= new Complejo(this.pReal - nComplejo.parteReal(),
					   this.pImaginaria - nComplejo.parteImaginaria()) ;
	
	return complejoRes;
    }

    //------------------------------------------------------------------------
    //  Devuelve un string, con el complejo
    //-------------------------------------------------------------------------
    
    public String toString ()
    {
	String cadenaDevuelta="";
	
	if  (this.pImaginaria>=0) 
	{
	    cadenaDevuelta= this.pReal + " + " + this.pImaginaria;
	}else{
	    cadenaDevuelta= this.pReal + " - " + this.pImaginaria;
	}
	
	return cadenaDevuelta;
    }
    
}