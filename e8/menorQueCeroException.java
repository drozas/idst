//********************************************************************#
// Autor: David Rozas
// menorQueCeroException.java
//
//  Defifinimos una nueva excepcion, que saltar� si el n� es <=0
//********************************************************************#

public class menorQueCeroException extends Exception
{
   //-----------------------------------------------------------------
   //  Levanta la excepcion con un mensaje propio
   //-----------------------------------------------------------------
   menorQueCeroException (String message)
   {
      super (message);

   }
}
