//********************************************************************
//  Ejecutivo.java       Author: Lewis/Loftus
//                       Adaptado por: GSyC
//
//  Representa a un trabajador ejecutivo, que puede ganar un bonus extra
//********************************************************************

public class Ejecutivo extends Empleado
{
   private double bonus;

   //-----------------------------------------------------------------
   //  Construye un Ejecutivo con la información que se le proporciona
   //-----------------------------------------------------------------
   public Ejecutivo (String nombre, String dirección, String teléfono,
                     String númeroSeguridadSocial, double salario)
   {
      super (nombre, dirección, teléfono, númeroSeguridadSocial, salario);

      bonus = 0;  // el bonus tiene que conseguirse
   }

   //-----------------------------------------------------------------
   //  Otorga el bonus especificado a un ejecutivo
   //-----------------------------------------------------------------
   public void otorgarBonus (double bonus)
   {
      this.bonus = bonus;
   }

   //-----------------------------------------------------------------
   // Calcula y devuelve el pago de un ejecutivo, que es el pago normal de
   // empleado más el bonus, que se paga una sola vez
   //-----------------------------------------------------------------
   public double pagar()
   {
      double pago = super.pagar() + bonus;

      bonus = 0;

      return pago;
   }
}
