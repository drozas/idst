//********************************************************************
//  Voluntario.java       Author: Lewis/Loftus
//                        Adaptado por: GSyC
//
//  Representa un miembro de una organización que trabaja como voluntario
//********************************************************************

public class Voluntario extends Miembro
{
   //-----------------------------------------------------------------
   //  Construye un Voluntario con la información que se le proporciona
   //-----------------------------------------------------------------
   public Voluntario (String nombre, String dirección, String teléfono)
   {
      super (nombre, dirección, teléfono);
   }

   //-----------------------------------------------------------------
   //  Devuelve cero como pago a este voluntario
   //-----------------------------------------------------------------
   public double pagar()
   {
      return 0.0;
   }
}
