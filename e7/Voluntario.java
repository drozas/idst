//********************************************************************
//  Voluntario.java       Author: Lewis/Loftus
//                        Adaptado por: GSyC
//
//  Representa un miembro de una organizaci�n que trabaja como voluntario
//********************************************************************

public class Voluntario extends Miembro
{
   //-----------------------------------------------------------------
   //  Construye un Voluntario con la informaci�n que se le proporciona
   //-----------------------------------------------------------------
   public Voluntario (String nombre, String direcci�n, String tel�fono)
   {
      super (nombre, direcci�n, tel�fono);
   }

   //-----------------------------------------------------------------
   //  Devuelve cero como pago a este voluntario
   //-----------------------------------------------------------------
   public double pagar()
   {
      return 0.0;
   }
}
