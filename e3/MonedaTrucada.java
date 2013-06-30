//********************************************************************
//  MonedaTrucada.java 
//  David Rozas
//
//  Representa una moneda trucada que puede ser lanzada al aire
//********************************************************************

import java.util.Random;

public class MonedaTrucada
{
    private final int CARA = 0;
    private final int CRUZ = 1;
    
    private int estado;
    private float trucaje;
    
   //-----------------------------------------------------------------
   //  Crea una moneda sin pasarle trucaje, lanzándola al aire inicialmente
   //-----------------------------------------------------------------
    public MonedaTrucada()
    {
	//Inicializamos trucaje a 0.5 en el constructor
	trucaje=(float)0.5;
	lanza();
    }

    //-------------------------------------------------------------------------
    // Crea una moneda, pasandole el trucaje. Despues la lanza al aire
    // ------------------------------------------------------------------------
    public MonedaTrucada(float trucajeInsertado)
    {
	//Si no está entre el rango permitido...le obligamos as que sea 0.5
	if ((trucajeInsertado>=0) && (trucajeInsertado<=1))
	{
	    trucaje=trucajeInsertado;
	}else{
	    trucaje=(float)0.5;
	}
	
	lanza();
    }
   //-----------------------------------------------------------------
   //  Lanza la moneda asignándole un valor aleatorio
   //-----------------------------------------------------------------
   public void lanza ()
   {
       float num_aleatorio;

       num_aleatorio = (float) (Math.random() * 2);
      
       if (num_aleatorio < (float)(trucaje*2))
       {
	   estado= CARA;
       }else{
	   estado= CRUZ;
       }
	  
   }

   //-----------------------------------------------------------------
   //  Devuelve true si el estado actual es CARA
   //-----------------------------------------------------------------
   public boolean esCara ()
   {
      return (estado == CARA);
   }

   //-----------------------------------------------------------------
   //  Devuelve el estado actual como un String
   //-----------------------------------------------------------------
   public String toString()
   {
      String nombreEstado;

      if (estado == CARA)
         nombreEstado = "Cara";
      else
         nombreEstado = "Cruz";

      return nombreEstado;
   }
}
