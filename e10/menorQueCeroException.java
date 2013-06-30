//********************************************************************#
// Autor: David Rozas
// menorQueCeroException.java
//
//  Defifinimos una nueva excepcion, que saltará si el nº es <=0
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
