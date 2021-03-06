//********************************************************************
//  Carta.java
//  David Rozas
//
//  Representa una clase carta, de la baraja espa�ola
//********************************************************************

/*Convenio:
numero: 1,2,3,4,5,6,7,8=sota,9=caballo,10=rey
palo: 1=oros,2=copas,3=espadas,4=bastos*/

import java.util.Random;

public class Carta
{
    
    private int numero;
    private int palo;
    private static Random generador= new Random();
    
   //-----------------------------------------------------------------
   //  Crea una carta aleatoria
   //-----------------------------------------------------------------
    public Carta()
    {
	//Inicializamos carta por defecto: uno de oros
	//Random generador = new Random();
	this.numero=generador.nextInt(10)+1 ;
	this.palo=generador.nextInt(4)+1;
    }
    

    //-------------------------------------------------------------------------
    // Devuelve el n�mero de la carta
    // ------------------------------------------------------------------------
    public int GetNumero()
    {
	int numeroDevuelto;
	
	numeroDevuelto=this.numero;
	return numeroDevuelto;
     
    }


    //-------------------------------------------------------------------------
    // Devuelve el n�mero de la carta
    // ------------------------------------------------------------------------
    public int GetPalo()
    {
	int paloDevuelto;
	
	paloDevuelto=this.palo;
	return paloDevuelto;
     
    }

    //-------------------------------------------------------------------------
    // Devuelve el valor de juego de la carta
    // ------------------------------------------------------------------------
    public int GetValorJuego()
    {
	int valorDevuelto=0;
	
	switch (this.numero){
	case 1: valorDevuelto=1;
	    break;
	case 2: valorDevuelto=1;
	    break;
	case 3: valorDevuelto=10;
	    break;
	case 4: valorDevuelto=4;
	    break;
	case 5: valorDevuelto=5;
	    break;
	case 6: valorDevuelto=6;
	    break;
	case 7: valorDevuelto=7;
	    break;
	case 8: valorDevuelto=10;
	    break;
	case 9: valorDevuelto=10;
	    break;
	case 10: valorDevuelto=10;
	    break;
	}
	
	return valorDevuelto;
    }
    
    //-----------------------------------------------------------------
    //  Devuelve el valor de la carta como un String
   //-----------------------------------------------------------------
    public String toString()
   {
       String valorCarta="";
       
      //Primero, nos encargamos del n�mero
       switch (this.numero){
       case 1: valorCarta="1 de ";
	   break;
       case 2: valorCarta="2 de ";
	   break;
       case 3: valorCarta="3 de ";
	   break;
       case 4: valorCarta="4 de ";
	   break;
       case 5: valorCarta="5 de ";
	   break;
       case 6: valorCarta="6 de ";
	  break;
       case 7: valorCarta="7 de ";
	   break;
       case 8: valorCarta="sota de ";
	   break;
       case 9: valorCarta="caballo de ";
	   break;
       case 10: valorCarta="rey de ";
	   break;
       }
       
      //Y ahora, le concatenamos el palo

       switch (this.palo){
       case 1: valorCarta= valorCarta + "oros";
	   break;
       case 2: valorCarta= valorCarta + "copas";
	   break;
       case 3: valorCarta= valorCarta + "espadas";
	   break;
       case 4: valorCarta= valorCarta + "bastos";
	   break;
       }

       return valorCarta;
   }
}
