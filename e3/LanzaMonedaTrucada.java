//********************************************************************
//  LanzaMonedaTrucada.java      
//  David Rozas
//
//  Muestra el uso de una clase definida por el usuario
//********************************************************************

public class LanzaMonedaTrucada
{
   //--------------------------------------------------------------------
   //  Lanza una moneda varias veces y cuenta el número de caras y cruces
   //--------------------------------------------------------------------
   public static void main (String[] args)
   {
      final int NUM_VECES = 100;
      int caras1=0, caras2=0;
      int carasSeguidas1=0, carasSeguidas2=0;
      int carasSeguidas1Aux=0, carasSeguidas2Aux=0;

      MonedaTrucada moneda_1 = new MonedaTrucada();  // instancia un objeto Moneda
      MonedaTrucada moneda_2 = new MonedaTrucada((float)0.7); 


      for (int contador=1; contador <= NUM_VECES; contador++)
      {
         moneda_1.lanza();
	 moneda_2.lanza();

	 //Recoge el nº maximo de caras seguidas

	 //Para la moneda 1....
         if (moneda_1.esCara()) 
	 {
	     carasSeguidas1Aux++;

	     if (carasSeguidas1Aux>carasSeguidas1)
		 carasSeguidas1=carasSeguidas1Aux;

         }else{
	     //Si es cruz, inicializamos a 0 la aux
	     carasSeguidas1Aux=0;
	 }

	 //Y para la moneda 2...
         if (moneda_2.esCara()) 
	 {
	     caras2++;
	     carasSeguidas2Aux++;

	     if (carasSeguidas2Aux>carasSeguidas2)
		 carasSeguidas2=carasSeguidas2Aux;

         }else{
	     //Si es cruz, inicializamos a 0 la aux
	     carasSeguidas2Aux=0;
	 }

      }

      //Mostramos datos por pantalla...
      System.out.println ("Tras " + NUM_VECES +  " lanzamientos, la secuencia más larga de caras consecutivas de moneda_1 fue " + carasSeguidas1);
      System.out.println ("Tras " + NUM_VECES +  " lanzamientos, la secuencia más larga de caras consecutivas de moneda_2 fue " + carasSeguidas2);


   }
}
