//********************************************************************
//  Jugador.java
//  David Rozas
//
//  Representa un jugador de mus, con 4 cartas. Usa la clase Carta
//********************************************************************


public class Jugador
{
    private Carta carta1;
    private Carta carta2;
    private Carta carta3;
    private Carta carta4;
    
   //-----------------------------------------------------------------
   //  Crea un jugador, con sus cuatro cartas
   //-----------------------------------------------------------------
    public Jugador()
    {
        //Creamos las cartas.
        carta1= new Carta();
	carta2= new Carta();
	carta3= new Carta();
	carta4= new Carta();
    }

   //-----------------------------------------------------------------
   //  Devuelve el valor del juego total del jugador
   //-----------------------------------------------------------------
    public int GetJuego()
    {
	int n1,n2,n3,n4,ntotal=1;
	
	//Cogemos los valores de juego de cada carta
	n1= carta1.GetValorJuego();
	n2= carta2.GetValorJuego();
	n3= carta3.GetValorJuego();
	n4= carta4.GetValorJuego();
    
	//Y los sumamos, para devolverlos
	ntotal= n1+n2+n3+n4;
	return ntotal;

    }

   //-----------------------------------------------------------------
   //  Devuelve la pos de juego, dado el valor de juego "normal"
   //-----------------------------------------------------------------
    
    public int GetPosicionJuego(int juego_pasado)
    {
	int posDevuelta=0;

	switch(juego_pasado){
	case 31: posDevuelta=0;
	    break;
	case 32: posDevuelta=1;
	    break;
	case 40: posDevuelta=2;
	    break;
	case 37: posDevuelta=3;
	    break;
	case 36: posDevuelta=4;
	    break;
	case 35: posDevuelta=5;
	    break;
	case 34: posDevuelta=6;
	    break;
	case 33: posDevuelta=7;
	    break;
	default: posDevuelta=8;
	    break;
	}
	return posDevuelta;
    }

   //-----------------------------------------------------------------
   //  Devuelve las cartas del jugador como un String
   //-----------------------------------------------------------------
   public String toString()
   {
       String cartasJugador;
       
       cartasJugador= carta1 + ", " + carta2 + ", "
	   + carta3+ ", " +carta4 + ", ";
       
       return cartasJugador;
   }
}
