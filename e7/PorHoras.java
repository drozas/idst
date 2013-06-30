//********************************************************************
//  PorHoras.java       Author: Lewis/Loftus
//                      Adaptado por: GSyC
//
//  Representa un empleado al que se le paga por horas
//********************************************************************

public class PorHoras extends Empleado
{
   private int horasTrabajadas;

   //-----------------------------------------------------------------
   //  Construye un empleado por horas con la información que se le proporciona
   //-----------------------------------------------------------------
   public PorHoras (String nombre, String dirección, String teléfono,
		    String númeroSeguridadSocial, double salario)
   {
      super (nombre, dirección, teléfono, númeroSeguridadSocial, salario);

      horasTrabajadas = 0;
   }

   //-----------------------------------------------------------------
   //  Añade la cantidad de horas especificadas a las horas acumuladas
   //  de un empleado
   //-----------------------------------------------------------------
   public void añadirHoras (int másHoras)
   {
      horasTrabajadas += másHoras;
   }

   //-----------------------------------------------------------------
   //  Calcula y devuelve la paga de un empleado por horas
   //-----------------------------------------------------------------
   public double pagar()
   {
      double pago = salario * horasTrabajadas;

      horasTrabajadas = 0;

      return pago;
   }

   //-----------------------------------------------------------------
   //  Devuelve un string con la inforamación de este empleado por horas
   //-----------------------------------------------------------------
   public String toString()
   {
      String resultado = super.toString();

      resultado += "Horas acumuladas: " + horasTrabajadas + "\n";

      return resultado;
   }
}
